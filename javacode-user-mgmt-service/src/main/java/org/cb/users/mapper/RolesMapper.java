package org.cb.users.mapper;

import lombok.extern.slf4j.Slf4j;
import org.cb.users.entity.RoleToPermission;
import org.cb.users.entity.Roles;
import org.cb.users.rq.RoleRq;
import org.cb.users.rs.RolesRs;
import org.cb.util.LocalDateTimeUtils;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RolesMapper {

    private RolesMapper() {
    }

    public static Roles mapToRoles(RoleRq rq, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToRoles( rq, mapper) -> ");
        }
        try {
            Roles roles = mapper.map(rq, Roles.class);
            roles.setCreatedBy("ADMIN");
            roles.setUpdatedBy("ADMIN");
            roles.setCreatedOn(LocalDateTime.now());
            roles.setUpdatedOn(LocalDateTime.now());
            List<RoleToPermission> roleToPermissions = new ArrayList<>();
            rq.getPermissions().forEach(permission -> {
                RoleToPermission rtp = new RoleToPermission();
                rtp.setRoles(roles);
                rtp.setPermissions(PermissionsMapper.mapToPermissions(permission, mapper));
                roleToPermissions.add(rtp);
            });
            roles.setPermissions(roleToPermissions);
            return roles;
        } catch (Exception e) {
            log.error("Exception in mapToRoles( rq, mapper) -> {}", e);
            throw e;
        }

    }

    public static RolesRs mapToRolesRS(Roles roles, ModelMapper mapper) {

        if (log.isDebugEnabled()) {
            log.debug("Executing mapToRoles( rq, mapper) -> ");
        }
        try {
            RolesRs rolesRs = mapper.map(roles, RolesRs.class);
            rolesRs.setCreatedOn(LocalDateTimeUtils.convertLdtToString(roles.getCreatedOn()));
            rolesRs.setUpdatedOn(LocalDateTimeUtils.convertLdtToString(roles.getUpdatedOn()));
            rolesRs.setPermissions(roles.getPermissions().stream().map(rtp -> PermissionsMapper.mapToPermissionsRs(rtp.getPermissions(), mapper)).toList());
            return rolesRs;
        } catch (Exception e) {
            log.error("Exception in mapToRoles( rq, mapper) -> {}", e);
            throw e;
        }
    }
}
