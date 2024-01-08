package org.cb.service;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface IEmailService {

    public void sendHtmlEmail(Map<String, Object> model) throws MessagingException;

}
