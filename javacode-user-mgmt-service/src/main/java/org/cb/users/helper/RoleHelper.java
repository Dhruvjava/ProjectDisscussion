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
                log.error(ErrorCodes.EC_REQUIRED_PERMISSION_CODE);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_PERMISSION_CODE, messages));
            }
            if (Utils.isEmpty(rq.getName())) {
                log.error(ErrorCodes.EC_REQUIRED_PERMISSION_NAME);
                errors.add(Utils.populateErrorRs(ErrorCodes.EC_REQUIRED_PERMISSION_NAME, messages));
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
}
