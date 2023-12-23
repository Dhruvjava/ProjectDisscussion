package org.cb.users.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.exception.InvalidPermissionsRqException;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.constants.MessageCodes;
import org.cb.users.datars.PermissionsDataRSs;
import org.cb.users.datars.PermissionsDataRs;
import org.cb.users.entity.Permissions;
import org.cb.users.helper.PermissionsHelper;
import org.cb.users.mapper.PermissionsMapper;
import org.cb.users.repo.PermissionsRepo;
import org.cb.users.rq.PermissionsRq;
import org.cb.users.rs.PermissionsRs;
import org.cb.users.service.IPermissionsService;
import org.cb.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.cb.users.mapper.PermissionsMapper.*;

@Service
@Slf4j
public class PermissionsServiceImpl implements IPermissionsService {

    @Autowired
    private PermissionsRepo repo;

    @Autowired
    private Messages messages;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BaseDataRs createPermissions(PermissionsRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing createPermissions(Permissions rq) -> ");
        }

        try {
            List<ErrorRs> errors = PermissionsHelper.validateCreatePermissions(rq, messages);
            Optional.of(errors).filter(Utils::isNotEmpty).ifPresent(error -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                throw new InvalidPermissionsRqException(ErrorCodes.EC_INVALID_INPUT, errorMessage, errors);
            });
            Permissions permissions = PermissionsMapper.mapToPermissions(rq, mapper);
            boolean isExists = repo.existsByCode(permissions.getCode());
            Optional.of(isExists).filter(exist -> isExists).ifPresent(exists -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_PERMISSION_ALREADY_EXISTS);
                throw new InvalidPermissionsRqException(ErrorCodes.EC_PERMISSION_ALREADY_EXISTS, errorMessage);
            });
            repo.save(permissions);
            String message = messages.getMessageProperty(MessageCodes.MC_CREATED_SUCCESSFULLY);
            PermissionsRs rs = PermissionsMapper.mapToPermissionsRs(permissions, mapper);
            return new PermissionsDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in createPermissions(Permissions permissions) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs updatePermissions(PermissionsRq permissions) {
        if (log.isDebugEnabled()) {
            log.debug("Executing updatePermissions(PermissionsRq permissions) -> ");
        }
        try {

            List<ErrorRs> errors = PermissionsHelper.validateUpdatePermissions(permissions, messages);
            Optional.ofNullable(errors).filter(Utils::isNotEmpty).ifPresent(error -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                throw new InvalidPermissionsRqException(ErrorCodes.EC_INVALID_INPUT, errorMessage, error);
            });
            boolean isExists = repo.existsById(permissions.getId());
            Optional.of(isExists).filter(exisst -> !isExists).ifPresent(exist -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_PERMISSION_NOT_FOUND);
                throw new InvalidPermissionsRqException(ErrorCodes.EC_PERMISSION_NOT_FOUND, errorMessage);
            });
            Permissions permission = PermissionsMapper.mapToUpdatePermissions(permissions, mapper);
            repo.save(permission);
            PermissionsRs rs = PermissionsMapper.mapToPermissionsRs(permission, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_UPDATED_SUCCESSFULLY);
            return new PermissionsDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in updatePermissions(PermissionsRq permissions) ->{}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findOnePermissions(Integer id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing findOnePermissions(id) ->");
        }
        try {
            Permissions permissions = repo.findById(id).orElse(null);
            return Optional.ofNullable(permissions)
                    .map(p -> {
                        String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFULLY);
                        PermissionsRs rs = PermissionsMapper.mapToPermissionsRs(p, mapper);
                        return new PermissionsDataRs(message, rs);
                    }).orElse(new PermissionsDataRs(messages.getMessageProperty(MessageCodes.MC_NO_RECORD_FOUND), null));
        } catch (Exception e) {
            log.error("Exception in findOnePermissions(id) ->{}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findAllPermissions() {
        if (log.isDebugEnabled()) {
            log.debug("Executing findAllPermissions() -> ");
        }
        try {
            List<Permissions> permissions = repo.findAll();
            return Optional.of(permissions).filter(Utils::isNotEmpty)
                    .map(p -> {
                        String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFULLY);
                        List<PermissionsRs> rs = permissions.stream().map(permission -> PermissionsMapper.mapToPermissionsRs(permission, mapper)).toList();
                        return new PermissionsDataRSs(message, rs);
                    }).orElse(new PermissionsDataRSs(messages.getMessageProperty(MessageCodes.MC_NO_RECORD_FOUND), Collections.emptyList()));
        } catch (Exception e) {
            log.error("Exception in findAllPermissions() -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs deletePermissions(Integer id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing deletePermissions(id) ->");
        }
        try {
            PermissionsDataRs dataRs = (PermissionsDataRs) findOnePermissions(id);
            PermissionsRs rs = dataRs.getPermission();
            Permissions permissions = Optional.ofNullable(rs).map(response->PermissionsMapper.mapToPermissions(rs, mapper)).orElseThrow(()->{
               String errMessage = messages.getErrorProperty(ErrorCodes.EC_PERMISSION_NOT_FOUND);
               return new InvalidPermissionsRqException(ErrorCodes.EC_PERMISSION_NOT_FOUND, errMessage);
            });
            repo.delete(permissions);
            String message = messages.getMessageProperty(MessageCodes.MC_DELETED_SUCCESSFULLY);
            return new PermissionsDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in deletePermissions() -> {}", e);
            throw e;
        }
    }
}
