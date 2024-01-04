package org.cb.users.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.exception.InvalidUsersRqException;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.entity.Users;
import org.cb.users.helper.UsersHelper;
import org.cb.users.repo.RoleRepo;
import org.cb.users.repo.UsersRepo;
import org.cb.users.rq.UsersRq;
import org.cb.users.service.IUsersService;
import org.cb.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private Messages messages;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BaseDataRs saveUsers(UsersRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing saveUsers(rq) -> ");
        }
        try {
            List<ErrorRs> errors = UsersHelper.validateSaveUsers(rq, messages);
            Optional.ofNullable(errors).filter(Utils::isNotEmpty).ifPresent(error -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                throw new InvalidUsersRqException(ErrorCodes.EC_INVALID_INPUT, errorMessage, errors);
            });

        } catch (Exception e) {
            log.error("Exception in saveUsers(rq) -> {}", e);
            throw e;
        }
        return null;
    }

    @Override
    public BaseDataRs updateUsers(UsersRq rq) {

        return null;
    }

    @Override
    public BaseDataRs findOneUsers(UsersRq rq) {
        return null;
    }

    @Override
    public BaseDataRs findAllUsers(UsersRq rq) {
        return null;
    }

    @Override
    public BaseDataRs deleteUsers(UsersRq rq) {
        return null;
    }

    @Override
    public BaseDataRs findByEmailUsers(UsersRq rq) {
        return null;
    }

    @Override
    public BaseDataRs findByUserName(UsersRq rq) {
        return null;
    }

    @Override
    public BaseDataRs provisioning(Users rq) {
        return null;
    }

    @Override
    public BaseDataRs deprovisioning(Users rq) {
        return null;
    }

    @Override
    public BaseDataRs enableUser(Integer id) {
        return null;
    }

    @Override
    public BaseDataRs unlockUser(Integer id) {
        return null;
    }

    @Override
    public BaseDataRs verifyUser(Integer id) {
        return null;
    }
}
