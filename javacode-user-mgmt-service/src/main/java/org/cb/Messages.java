package org.cb;

import jakarta.annotation.PostConstruct;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessageSource errorSource;

    MessageSourceAccessor messageSourceAccessor = null;

    MessageSourceAccessor errorSourceAccessor = null;

    @PostConstruct
    void init(){
        messageSourceAccessor = new MessageSourceAccessor(messageSource);
        errorSourceAccessor = new MessageSourceAccessor(errorSource);
    }

    public String getMessageProperty(String code){
        return messageSourceAccessor.getMessage(code);
    }

    public String getErrorProperty(String code){
        return errorSourceAccessor.getMessage(code);
    }

}
