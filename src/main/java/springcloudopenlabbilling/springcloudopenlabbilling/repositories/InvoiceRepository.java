package springcloudopenlabbilling.springcloudopenlabbilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springcloudopenlabbilling.springcloudopenlabbilling.entities.Invoice;

import java.util.List;


public interface InvoiceRepository extends JpaRepository<Invoice,String> {
    List<Invoice> findByCustomerId(String customerId);
    Invoice findInvoiceByCustomerId(String id);
}
