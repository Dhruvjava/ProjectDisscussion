package org.cb.users.helper;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.rs.ErrorRs;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.rq.UsersRq;
import org.cb.util.Utils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UsersHelper {

    private UsersHelper() {
    }

    public static List<ErrorRs> validateSaveUsers(UsersRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateSaveUsers(rq, messages)");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (Utils.isEmpty(rq.getFirstName())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isEmpty(rq.getLastName())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isEmpty(rq.getEmail())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isNotEmpty(rq.getEmail())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isEmpty(rq.getUsername())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isEmpty(rq.getMobile())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isNotEmpty(rq.getMobile())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isEmpty(rq.getAddress())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isEmpty(rq.getRoles())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            if (Utils.isNotEmpty(rq.getRoles())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_FIRST_NAME, messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateSaveUsers(rq, messages)");
            throw e;
        }
    }
}
