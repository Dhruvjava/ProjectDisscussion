package org.cb.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.helper.EmailHelper;
import org.cb.rq.AttachmentRq;
import org.cb.rq.EmailRq;
import org.cb.service.IEmailService;
import org.cb.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.List;
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
    public void sendHtmlEmail(EmailRq rq) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Executing sendHtmlEmail(model) ->");
        }
        try {
            final EmailRq rqVM = EmailHelper.removeDuplicates(rq);
            if (null == rqVM) {
                log.error("EmailRq is NULL");
                throw new Exception("EmailRq is NULL");
            }
            MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    List<AttachmentRq> attachmentRqs = rqVM.getAttachments();
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    message.setFrom(new InternetAddress(FROM_EMAIL, FROM_NAME));
                    if (null != rqVM.getToEmailAddr()) {
                        message.setTo(new InternetAddress(rqVM.getToEmailAddr().getEmail(),
                                rqVM.getToEmailAddr().getName()));
                    }
                    InternetAddress[] toAddrs =
                            EmailHelper.prepareInternetAddress(rqVM.getToEmailAddrs());
                    if (Utils.isNotEmpty(toAddrs)) {
                        message.setTo(toAddrs);
                    }
                    InternetAddress[] ccAddrs =
                            EmailHelper.prepareInternetAddress(rqVM.getCcEmailAddrs());
                    if (Utils.isNotEmpty(ccAddrs)) {
                        message.setCc(ccAddrs);
                    }
                    InternetAddress[] bccAddrs =
                            EmailHelper.prepareInternetAddress(rqVM.getBccEmailAddrs());
                    if (Utils.isNotEmpty(bccAddrs)) {
                        message.setBcc(bccAddrs);
                    }
                    message.setSubject(rqVM.getSubject());
                    message.setText(rqVM.getMessageText(), true);
                    if (Utils.isNotEmpty(attachmentRqs)) {
                        for (AttachmentRq attachmentRq : attachmentRqs) {
                            String name = attachmentRq.getName();
                            if (Utils.isNotEmpty(attachmentRq.getType())) {
                                name = name + attachmentRq.getType();
                            }
                            if (Utils.isEmpty(attachmentRq.getContentType())) {
                                message.addAttachment(name, attachmentRq.getByteSource());
                            } else {
                                message.addAttachment(name, attachmentRq.getByteSource(),
                                        attachmentRq.getContentType());
                            }
                        }
                    }
                    message.getMimeMessage().saveChanges();
                }
            };
            mailSender.send(mimeMessagePreparator);
            EmailHelper.logSentMailDetails(rqVM);
        } catch (Exception e) {
            log.error("Exception in sendHtmlEmail(model) -> {}", e);
            throw e;
        }

    }
}
