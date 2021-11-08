package springcloudopenlabbilling.springcloudopenlabbilling.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceRequestDTO;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceResponseDTO;
import springcloudopenlabbilling.springcloudopenlabbilling.entities.Invoice;
import springcloudopenlabbilling.springcloudopenlabbilling.repositories.InvoiceRepository;
import springcloudopenlabbilling.springcloudopenlabbilling.services.InvoiceService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestControllerApi {
    private InvoiceService invoiceService;
    private InvoiceRepository invoiceRepository;

    public InvoiceRestControllerApi(InvoiceService invoiceService, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.invoiceRepository = invoiceRepository;
    }


    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name = "id") String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }
    @GetMapping(path = "/invoicesByCustomer/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesByCustomer(@PathVariable String customerId){
        return invoiceService.invoicesByCustomerId(customerId);
    }
    @PostMapping(path = "/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.save(invoiceRequestDTO);
    }
    @GetMapping(path = "/invoices")
    public List<InvoiceResponseDTO> AllInvoices(){
        return invoiceService.AllInvoices();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exeptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
