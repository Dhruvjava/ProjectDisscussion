package org.cb.users.helper;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.rs.ErrorRs;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.rq.RoleRq;
import org.cb.util.Utils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoleHelper {

    private RoleHelper() {
    }

    public static List<ErrorRs> validateCreateRole(RoleRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateCreatePermissions(rq, messages) -> ");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (Utils.isEmpty(rq.getCode())) {
                log.error(ErrorCodes.EC_REQUIRED_ROLE_CODE);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_ROLE_CODE, messages));
            }
            if (Utils.isEmpty(rq.getName())) {
                log.error(ErrorCodes.EC_REQUIRED_ROLE_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_ROLE_NAME, messages));
            }
            if (Utils.isEmpty(rq.getPermissions())) {
                log.error(ErrorCodes.EC_ROLES_PERMISSION_REQUIRED);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_ROLES_PERMISSION_REQUIRED, messages));
            }
            if (Utils.isNotEmpty(rq.getPermissions())) {
                List<ErrorRs> error = rq.getPermissions().stream().map(permission -> PermissionsHelper.validateUpdatePermissions(permission, messages)).flatMap(List::stream).toList();
                if (Utils.isNotEmpty(error)) {
                    log.error(ErrorCodes.EC_INVALID_ROLES_PERMISSION);
                    errors.addAll(error);
                }
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateCreatePermissions(rq, messages) -> {}", e);
            throw e;
        }
    }

    public static List<ErrorRs> validateUpdateRole(RoleRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateUpdateRole(rq, messages)");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getId() == null || rq.getId() == 0) {
                log.error(ErrorCodes.EC_REQUIRED_ROLE_ID);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_ROLE_ID, messages));
            }
            errors.addAll(validateCreateRole(rq, messages));
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateUpdateRole(rq, messages) -> {}", e);
            throw e;
        }
    }
}
