package org.cb.users.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.exception.InvalidRoleRqException;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.constants.MessageCodes;
import org.cb.users.datars.RolesDataRs;
import org.cb.users.entity.Roles;
import org.cb.users.helper.RoleHelper;
import org.cb.users.mapper.RolesMapper;
import org.cb.users.repo.RoleRepo;
import org.cb.users.rq.RoleRq;
import org.cb.users.rs.RolesRs;
import org.cb.users.service.IRoleService;
import org.cb.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Messages messages;

    @Override
    public BaseDataRs createRole(RoleRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing createRole(rq) ->");
        }
        try {
            List<ErrorRs> errors = RoleHelper.validateCreateRole(rq, messages);
            Optional.ofNullable(errors).filter(err -> Utils.isNotEmpty(errors)).ifPresent(error -> {
                String errorMessages = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                throw new InvalidRoleRqException(ErrorCodes.EC_INVALID_INPUT, errorMessages, error);
            });
            boolean isExists = repo.existsByCode(rq.getCode());
            Optional.of(isExists).filter(exist -> isExists).ifPresent(exists -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_ALREADY_EXISTS);
                throw new InvalidRoleRqException(ErrorCodes.EC_ROLE_ALREADY_EXISTS, errorMessage);
            });
            Roles roles = RolesMapper.mapToRoles(rq, mapper);
            repo.save(roles);
            String message = messages.getMessageProperty(MessageCodes.MC_CREATED_SUCCESSFULLY);
            RolesRs rs = RolesMapper.mapToRolesRS(roles, mapper);
            return new RolesDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in createRole(rq) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs updateRole(RoleRq rq) {
        return null;
    }

    @Override
    public BaseDataRs findOneRole(Integer id) {
        return null;
    }

    @Override
    public BaseDataRs deleteRole(Integer id) {
        return null;
    }

    @Override
    public BaseDataRs findAllRoles() {
        return null;
    }
}
