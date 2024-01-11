package org.cb.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.cb.rq.EmailRq;

import java.io.IOException;
import java.util.Map;

public interface IEmailService {

    public void sendHtmlEmail(EmailRq rq) throws Exception;

}
