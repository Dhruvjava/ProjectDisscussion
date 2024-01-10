package org.cb.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freemakerTemplate;

    @Autowired
    private Messages messages;

    @Value("${mail.from.email}")
    private String FROM_EMAIL;

    @Value("${mail.from.name}")
    private String FROM_NAME;

    @Override
    public void sendHtmlEmail(Map<String, Object> model) throws MessagingException, TemplateException, IOException {
        if (log.isDebugEnabled()) {
            log.debug("Executing sendHtmlEmail(model) ->");
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(FROM_EMAIL);
            helper.setTo("dhruv.rbs.java@gmail.com");
            helper.setSubject("Verify User Notification");
            String filepath = messages.getEmailMessage("email.filepath.verifyemail");
            Template template = freemakerTemplate.getTemplate(filepath);
            String htmlFile = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(htmlFile, true);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("Exception in sendHtmlEmail(model) -> {}", e);
            throw e;
        }

    }
}
