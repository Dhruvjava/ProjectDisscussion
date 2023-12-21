package org.cb.users.mapper;

import lombok.extern.slf4j.Slf4j;
import org.cb.users.entity.Permissions;
import org.cb.users.rq.PermissionsRq;
import org.cb.users.rs.PermissionsRs;
import org.cb.util.LocalDateTimeUtils;
import org.cb.util.Utils;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Slf4j
public class PermissionsMapper {

    private PermissionsMapper() {
    }

    public static Permissions mapToPermissions(PermissionsRq rq, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToPermissions(rq, mapper)");
        }
        try {
            Permissions permission = mapper.map(rq, Permissions.class);
            permission.setCreatedBy("ADMIN");
            permission.setCreatedOn(LocalDateTime.now());
            permission.setUpdatedBy("ADMIN");
            permission.setUpdatedOn(LocalDateTime.now());
            return permission;
        } catch (Exception e) {
            log.error("Exception in mapToPermissions(rq, mapper) -> {}", e);
            throw e;
        }
    }

    public static PermissionsRs mapToPermissionsRs(Permissions permissions, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToPermissions(permissions, mapper) -> ");
        }
        try {
            PermissionsRs rs = mapper.map(permissions, PermissionsRs.class);
            String createdOn = LocalDateTimeUtils.convertLdtToString(permissions.getCreatedOn());
            if (Utils.isNotEmpty(createdOn)) {
                rs.setCreatedOn(createdOn);
            }
            String updatedOn = LocalDateTimeUtils.convertLdtToString(permissions.getUpdatedOn());
            if (Utils.isNotEmpty(updatedOn)) {
                rs.setUpdatedOn(updatedOn);
            }
            return rs;
        } catch (Exception e) {
            log.error("Exception in mapToPermissions(permissions, mapper) -> {}", e);
            throw e;
        }
    }

}

