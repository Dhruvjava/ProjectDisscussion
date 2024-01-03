package org.cb.users.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.exception.InvalidRoleRqException;
import org.cb.exception.RolesNotFoundException;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.constants.MessageCodes;
import org.cb.users.datars.RolesDataRSs;
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
        if (log.isDebugEnabled()) {
            log.debug("Executing updateRole(rq) -> ");
        }
        try {
            List<ErrorRs> errors = RoleHelper.validateUpdateRole(rq, messages);
            Optional.ofNullable(errors).filter(err -> Utils.isNotEmpty(errors)).ifPresent(error -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                throw new InvalidRoleRqException(ErrorCodes.EC_INVALID_INPUT, errorMessage, error);
            });
            Roles isExists = repo.findById(rq.getId()).orElse(null);
            Optional.ofNullable(isExists).filter(exists -> isExists == null).ifPresent(is -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                throw new InvalidRoleRqException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
            });
            Roles roles = RolesMapper.mapToUpdateRoles(rq, mapper);
            roles.setCreatedBy(isExists.getCreatedBy());
            roles.setCreatedOn(isExists.getCreatedOn());
            repo.save(roles);
            RolesRs rs = RolesMapper.mapToRolesRS(roles, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_UPDATED_SUCCESSFULLY);
            return new RolesDataRs(message, rs);

        } catch (Exception e) {
            log.error("Exception in updateRole(rq) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findOneRole(Integer id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing findRole(int id) ->");
        }
        try {
            Roles roles = repo.findById(id)
                    .orElseThrow(() -> {
                        String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                        log.info(errorMessage);
                        return new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
                    });
            RolesRs rs = RolesMapper.mapToRolesRS(roles, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFULLY);
            return new RolesDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findRole(int id) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs deleteRole(Integer id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing deleteRole(int id) ->");
        }
        try {
            Optional.of(repo.existsById(id))
                    .filter(exists -> exists)
                    .ifPresentOrElse(exist -> repo.deleteById(id), () -> {
                        String errorMessage = messages.getMessageProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                        log.warn(errorMessage);
                        throw new RolesNotFoundException(MessageCodes.MC_NO_RECORD_FOUND, errorMessage);
                    });
            String message = messages.getMessageProperty(MessageCodes.MC_DELETED_SUCCESSFULLY);
            return new RolesDataRs(message);
        } catch (Exception e) {
            log.error("Exception in deleteRole(int id) -> {0}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findAllRoles() {
        if (log.isDebugEnabled()) {
            log.debug("Executing findAllRole() ->");
        }
        try {
            List<Roles> roles = Optional.of(repo.findAll()).filter(Utils::isNotEmpty).orElseThrow(() -> {
                String errorMessage = messages.getMessageProperty(MessageCodes.MC_NO_RECORD_FOUND);
                log.info(errorMessage);
                return new RolesNotFoundException(MessageCodes.MC_NO_RECORD_FOUND, errorMessage);
            });
            List<RolesRs> rs = roles.stream().map(role -> RolesMapper.mapToRolesRS(role, mapper)).toList();
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFULLY);
            return new RolesDataRSs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findAllRole() -> {0}", e);
            throw e;
        }
    }
}
