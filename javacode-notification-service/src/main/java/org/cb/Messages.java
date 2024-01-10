package org.cb;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages {

    @Autowired
    private MessageSource errorSource;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessageSource emailSource;

    private MessageSourceAccessor errorSourceAccessor;

    private MessageSourceAccessor messageSourceAccessor;

    private MessageSourceAccessor emailSourceAccessor;

    @PostConstruct
    public void init() {
        errorSourceAccessor = new MessageSourceAccessor(errorSource, Locale.ENGLISH);
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
        emailSourceAccessor = new MessageSourceAccessor(emailSource, Locale.ENGLISH);
    }

    public String getErrorMessage(String code) {
        return errorSourceAccessor.getMessage(code);
    }

    public String getMessageMessage(String code) {
        return messageSourceAccessor.getMessage(code);
    }

    public String getEmailMessage(String code) {
        return emailSourceAccessor.getMessage(code);
    }

}
