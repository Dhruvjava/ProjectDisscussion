package org.cb.users.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.exception.InvalidPermissionsRqException;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.constants.MessageCodes;
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

import java.util.List;
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
            Optional.of(isExists).filter(exist->isExists).ifPresent(exists->{
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
        return null;
    }

    @Override
    public BaseDataRs findOnePermissions(Integer id) {
        return null;
    }

    @Override
    public BaseDataRs findAllPermissions() {
        return null;
    }

    @Override
    public BaseDataRs deletePermissions(Integer id) {
        return null;
    }
}
