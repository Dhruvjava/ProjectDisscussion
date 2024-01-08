package org.cb.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendHtmlEmail(Map<String, Object> model) throws MessagingException {
        if (log.isDebugEnabled()) {
            log.debug("Executing sendHtmlEmail(model) ->");
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
////            helper
//            mailSender.;
        } catch (Exception e) {
            log.error("Exception in sendHtmlEmail(model) -> {}", e);
            throw e;
        }

    }
}
