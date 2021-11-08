package springcloudopenlabbilling.springcloudopenlabbilling.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceRequestDTO;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceResponseDTO;
import springcloudopenlabbilling.springcloudopenlabbilling.Exceptions.CustomerNotFoundException;
import springcloudopenlabbilling.springcloudopenlabbilling.entities.Customer;
import springcloudopenlabbilling.springcloudopenlabbilling.entities.Invoice;
import springcloudopenlabbilling.springcloudopenlabbilling.mappers.InvoiceMapper;
import springcloudopenlabbilling.springcloudopenlabbilling.openfeign.CustomerRestClient;
import springcloudopenlabbilling.springcloudopenlabbilling.repositories.InvoiceRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
   private InvoiceRepository invoiceRepository;
   private InvoiceMapper invoiceMapper;
   private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        /*Vérification de l'intégrité référentielle*/
        Customer customer = null;
        try {
            customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer Not found With This ID : "+invoiceRequestDTO.getCustomerId());
        }
        Invoice invoice=invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        /*
        * Vérigication de l'intégrité référentielle Invoice / Customer
        * */
        Invoice saveInvoice=invoiceRepository.save(invoice);
        saveInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoiceResponseDTO(saveInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String InvoiceId) {
        Invoice invoice = invoiceRepository.findById(InvoiceId).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoiceResponseDTO(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String CustomerId) {
        List<Invoice> invoices = invoiceRepository.findByCustomerId(CustomerId);
        invoices.forEach(invo->{
            Customer customer=customerRestClient.getCustomer(invo.getCustomerId());
            invo.setCustomer(customer);
        });
        return invoices.stream().map(invoice->invoiceMapper.fromInvoiceResponseDTO(invoice))
                .collect(Collectors.toList());

    }

    @Override
    public InvoiceResponseDTO update(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        Invoice UpdatedInvoice = invoiceRepository.save(invoice);

        return invoiceMapper.fromInvoiceResponseDTO(UpdatedInvoice);
    }

    @Override
    public void delete(String InvoiceId) {
                invoiceRepository.deleteById(InvoiceId);
    }

    @Override
    public List<InvoiceResponseDTO> AllInvoices() {
        List<Invoice>invoices = invoiceRepository.findAll();
        /*invoices.forEach(invo->{
            Customer customer=customerRestClient.getCustomer(invo.getCustomerId());
            invo.setCustomer(customer);
        });*/
        for(Invoice invoice:invoices){
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }

        return invoices.stream().map(invo->invoiceMapper.fromInvoiceResponseDTO(invo))
                .collect(Collectors.toList());
    }
}
