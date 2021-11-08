package springcloudopenlabbilling.springcloudopenlabbilling.mappers;

import org.mapstruct.Mapper;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceRequestDTO;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceResponseDTO;
import springcloudopenlabbilling.springcloudopenlabbilling.entities.Invoice;

@Mapper(componentModel = "Spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoiceResponseDTO(Invoice invoice);
//MapStruct sert à faire le mapping entre les entités et les DTOs
}
