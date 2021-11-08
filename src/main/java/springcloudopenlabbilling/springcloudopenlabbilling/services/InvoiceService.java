package springcloudopenlabbilling.springcloudopenlabbilling.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceRequestDTO;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceResponseDTO;

import java.util.List;


public interface InvoiceService {
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    public InvoiceResponseDTO getInvoice(String InvoiceId);
    List<InvoiceResponseDTO> invoicesByCustomerId(String CustomerId);
    InvoiceResponseDTO update(InvoiceRequestDTO invoiceRequestDTO);
    void delete(String InvoiceId);
    List<InvoiceResponseDTO> AllInvoices();
}
