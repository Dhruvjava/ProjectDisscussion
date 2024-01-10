package org.cb.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.Map;

public interface IEmailService {

    public void sendHtmlEmail(Map<String, Object> model) throws MessagingException, TemplateException, IOException;

}
