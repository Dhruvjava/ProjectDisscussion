package org.cb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@EnableDiscoveryClient
public class JavacodeNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavacodeNotificationServiceApplication.class, args);
    }

    @Bean
    public MessageSource errorSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:/bundles/application_errors");
        return resource;
    }

    @Bean
    MessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:bundles/application_message");
        return source;
    }

    @Bean
    MessageSource emailSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:bundles/application_email");
        return source;
    }

}
