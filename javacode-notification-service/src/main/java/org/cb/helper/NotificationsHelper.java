package org.cb.helper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.rs.ErrorRs;
import org.cb.constants.ErrorCodes;
import org.cb.rq.NotificationsRq;
import org.cb.util.Utils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class NotificationsHelper {

    private NotificationsHelper() {
    }

    public static List<ErrorRs> validateSendNotifications(NotificationsRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateSendNotifications(NotificationsRq , Messages) ->");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() == null) {
                log.error(ErrorCodes.EC_REQUIRED_NOTIFICATION_USERS_ID);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_NOTIFICATION_USERS_ID, messages));
            }
            if (Utils.isEmpty(rq.getSubject())) {
                log.error(ErrorCodes.EC_REQUIRED_NOTIFICATION_SUBJECT);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_NOTIFICATION_SUBJECT, messages));
            }
            if (Utils.isEmpty(rq.getUsername())) {
                log.error(ErrorCodes.EC_REQUIRED_NOTIFICATION_USERNAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_NOTIFICATION_USERNAME, messages));
            }
            if (rq.getToEmail() == null) {
                log.error(ErrorCodes.EC_REQUIRED_NOTIFICATION_TOEMAIL);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_NOTIFICATION_TOEMAIL, messages));
            }
            if (rq.getToEmail() != null) {
                if (!Utils.isValidEmail(rq.getToEmail().getEmail())) {
                    log.error(ErrorCodes.EC_INVALID_NOTIFICATION_EMAIL);
                    errors.add(Utils.populateErrorRs(ErrorCodes.EC_INVALID_NOTIFICATION_EMAIL, messages));
                }
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateSendNotifications(NotificationsRq , Messages) -> {}", e);
            throw e;
        }
    }

    public static String prepareVerifyEmailTemplate(Map<String, Object> model, Configuration templateConfiguration, Messages messages) throws TemplateException, IOException {
        if (log.isDebugEnabled()) {
            log.debug("Executing prepareVerifyEmailTemplate(Map<String, Object> , Configuration) ->");
        }
        try {
            String verifyTemplate = messages.getEmailMessage("email.filepath.verifyemail");
            Template template = templateConfiguration.getTemplate(verifyTemplate);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            log.error("Exception in prepareVerifyEmailTemplate(Map<String, Object>, Configuration) -> {}", e);
            throw e;
        }
    }
}
