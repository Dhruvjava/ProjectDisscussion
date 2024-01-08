package org.cb.users.mapper;

import lombok.extern.slf4j.Slf4j;
import org.cb.users.entity.Users;
import org.cb.users.rq.UsersRq;
import org.cb.users.rs.PermissionsRs;
import org.cb.users.rs.RolesRs;
import org.cb.users.rs.UsersRs;
import org.modelmapper.ModelMapper;

import java.util.List;

@Slf4j
public class UsersMapper {
    public static Users mapToUsers(UsersRq rq, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToUsers(UsersRq rq)");
        }
        try {
            return mapper.map(rq, Users.class);
        } catch (Exception e) {
            log.error("Exception in mapToUsers(UsersRq rq) -> {0}", e);
            throw e;
        }
    }

    public static UsersRs maptoUsers(Users users, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing maptoUsers(Users users, ModelMapper mapper) ->");
        }
        try {
            UsersRs usersRs = mapper.map(users, UsersRs.class);
            RolesRs rolesrs = null;
            if (users.getRoles() != null) {
                rolesrs = new RolesRs();
                List<PermissionsRs> permissions = users.getRoles().getPermissions().stream().map(role -> PermissionsMapper.mapToPermissionsRs(role.getPermissions(), mapper)).toList();
                rolesrs.setPermissions(permissions);
            }
            usersRs.setRole(rolesrs);
            return usersRs;
        } catch (Exception e) {
            log.error("Exception in maptoUsers(Users users, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }
}
