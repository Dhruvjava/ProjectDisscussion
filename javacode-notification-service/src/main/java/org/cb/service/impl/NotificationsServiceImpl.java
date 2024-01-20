package org.cb.service.impl;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.constants.ErrorCodes;
import org.cb.constants.MessageCodes;
import org.cb.exception.NotificationsExceptions;
import org.cb.helper.NotificationsHelper;
import org.cb.rq.EmailRq;
import org.cb.rq.NotificationsRq;
import org.cb.service.IEmailService;
import org.cb.service.INotificationsService;
import org.cb.util.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationsServiceImpl implements INotificationsService {

    private final IEmailService emailService;

    private final Configuration templateConfiguration;

    private final Messages messages;

    @Value("${users.email.verify}")
    private String USERS_VERIFY_URI;

    @Value("${users.login.url}")
    private String USERS_LOGIN_URI;

    private String USERS_ID = "USER_ID";

    @Override
    public BaseDataRs sendNotifications(NotificationsRq rq) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Executing sendNotifications(NotificationsRq) -> ");
        }
        try {
            List<ErrorRs> errors = NotificationsHelper.validateSendNotifications(rq, messages);
            Optional.ofNullable(errors).filter(Utils::isNotEmpty).ifPresent(err -> {
                String errorMessage = messages.getErrorMessage(ErrorCodes.EC_INVALID_INPUT);
                throw new NotificationsExceptions(ErrorCodes.EC_INVALID_INPUT, errorMessage, errors);
            });
            Map<String, Object> model = new HashMap<>();
            model.put("username", rq.getUsername());
            Optional.ofNullable(USERS_VERIFY_URI).ifPresent(verify -> {
                USERS_VERIFY_URI = USERS_VERIFY_URI.replace(USERS_ID, String.valueOf(rq.getId()));
                model.put("action_url", USERS_VERIFY_URI);
            });
            Optional.ofNullable(USERS_LOGIN_URI).ifPresent(login->{
                USERS_LOGIN_URI = USERS_LOGIN_URI.replace("'","");
                model.put("login_url", USERS_LOGIN_URI);
            });
            String html = NotificationsHelper.prepareVerifyEmailTemplate(model, templateConfiguration, messages);
            EmailRq emailRq = new EmailRq();
            emailRq.setToEmailAddr(rq.getToEmail());
            emailRq.setSubject(rq.getSubject());
            emailRq.setMessageText(html);
            emailService.sendHtmlEmail(emailRq);
            String message = messages.getMessageMessage(MessageCodes.MC_NOTIFICATIONS_SENT_SUCCESSFULLY);
            return new BaseDataRs(message);
        } catch (Exception e) {
            log.error("Exception in sendNotifications(NotificationsRq) -> {}", e);
            throw e;
        }
    }
}
