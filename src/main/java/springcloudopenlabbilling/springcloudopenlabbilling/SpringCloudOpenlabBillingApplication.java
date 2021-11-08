package springcloudopenlabbilling.springcloudopenlabbilling;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springcloudopenlabbilling.springcloudopenlabbilling.DTO.InvoiceRequestDTO;
import springcloudopenlabbilling.springcloudopenlabbilling.services.InvoiceService;

import java.math.BigDecimal;

@EnableFeignClients
@SpringBootApplication
public class SpringCloudOpenlabBillingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOpenlabBillingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(100000),"C02"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(90000),"C02"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(95000),"C01"));
        };
    }
}
