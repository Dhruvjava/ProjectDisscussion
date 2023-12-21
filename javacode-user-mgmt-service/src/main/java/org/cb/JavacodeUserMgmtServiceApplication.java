package org.cb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "JAVACODE-USER-MGMT-SVC",
        description = "It's User Management Service. Where we can manage all operatoins regarding user perspectives.",
version = "1.0",
contact = @Contact(email = "info@javacode.com",
name = "Java Code PVT. LTD.", url = "www.javacode.com"),
license = @License( name = "MIT License", url = "http://www.opensource.org/licenses/mit-license"),
termsOfService = "JAVA Code Terms & Conditions"
),
servers = {
        @Server(
                description = "JAVA CODE Local Environment",
                url = "http://localhost:7071/dev"
        )
}
)
public class JavacodeUserMgmtServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavacodeUserMgmtServiceApplication.class, args);
    }

    @Bean
    MessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:bundles/application_message");
        return source;
    }

    @Bean
    MessageSource errorSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:bundles/application_errors");
        return source;
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
