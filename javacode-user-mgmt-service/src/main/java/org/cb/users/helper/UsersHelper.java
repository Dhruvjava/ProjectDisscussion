package org.cb.users.helper;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.rs.ErrorRs;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.rq.UsersRolesRq;
import org.cb.users.rq.UsersRq;
import org.cb.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                log.error(ErrorCodes.EC_REQUIRED_USERS_LAST_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_LAST_NAME, messages));
            }
            if (Utils.isEmpty(rq.getEmail())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_EMAIL);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_EMAIL, messages));
            }
            if (Utils.isNotEmpty(rq.getEmail())) {
                if (!Utils.isValidEmail(rq.getEmail())) {
                    log.error(ErrorCodes.EC_INVALID_USERS_EMAIL);
                    errors.add(Utils.populateErrorRs(ErrorCodes.EC_INVALID_USERS_EMAIL, messages));
                }
            }
            if (Utils.isEmpty(rq.getUsername())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_USERNAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_USERNAME, messages));
            }
            if (Utils.isEmpty(rq.getMobile())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_MOBILE);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_MOBILE, messages));
            }
            if (Utils.isNotEmpty(rq.getMobile())) {
                if (!Utils.isValidMobile(rq.getMobile())) {
                    log.error(ErrorCodes.EC_INVALID_USERS_MOBILE);
                    errors.add(Utils.populateErrorRs(ErrorCodes.EC_INVALID_USERS_MOBILE, messages));
                }
            }
            if (Utils.isEmpty(rq.getAddress())) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_ADDRESS);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_ADDRESS, messages));
            }
            if (rq.getRoles() == null) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_ROLES);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_ROLES, messages));
            }
            if (rq.getRoles() != null) {
                errors.addAll(validateUsersRolesRq(rq.getRoles(), messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateSaveUsers(rq, messages)");
            throw e;
        }
    }

    private static List<ErrorRs> validateUsersRolesRq(UsersRolesRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateUsersRolesRq(rq, messages)");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() != null || rq.getId() <= 0) {
                log.error(ErrorCodes.EC_REQUIRED_ROLE_ID);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_ROLE_ID, messages));
            }
            if (Utils.isNotEmpty(rq.getCode())) {
                log.error(ErrorCodes.EC_REQUIRED_ROLE_CODE);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_ROLE_CODE, messages));
            }
            if (Utils.isNotEmpty(rq.getCode())) {
                log.error(ErrorCodes.EC_REQUIRED_ROLE_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_ROLE_NAME, messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateUsersRolesRq(rq, messages)");
            throw e;
        }
    }

    public static List<ErrorRs> validateUpdateUsers(UsersRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateCreateUsers(UsersRq rq, Messages messages) ->");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() == null) {
                log.error(ErrorCodes.EC_REQUIRED_USERS_ROLES);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_USERS_ID, messages));
            }
            List<ErrorRs> validateErrors = validateSaveUsers(rq, messages);
            errors.addAll(validateErrors);
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateCreateUsers(UsersRq rq, Messages messages) -> {0}", e);
            return Collections.emptyList();
        }
    }
}
