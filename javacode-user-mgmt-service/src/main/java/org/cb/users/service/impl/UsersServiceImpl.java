package org.cb.users.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.base.rs.ErrorRs;
import org.cb.exception.InvalidUsersRqException;
import org.cb.exception.RolesNotFoundException;
import org.cb.users.constants.ErrorCodes;
import org.cb.users.constants.MessageCodes;
import org.cb.users.datars.UsersDataRs;
import org.cb.users.entity.Roles;
import org.cb.users.entity.Users;
import org.cb.users.helper.UsersHelper;
import org.cb.users.mapper.UsersMapper;
import org.cb.users.repo.RoleRepo;
import org.cb.users.repo.UsersRepo;
import org.cb.users.rq.UsersRq;
import org.cb.users.rs.UsersRs;
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
            Users users = UsersMapper.mapToUsers(rq, mapper);
            provisioning(users);
            users = repo.save(users);
            UsersRs userRs = UsersMapper.maptoUsers(users, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_CREATED_SUCCESSFULLY);
            return new UsersDataRs(message, userRs);
        } catch (Exception e) {
            log.error("Exception in saveUsers(rq) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs updateUsers(UsersRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing updateUsers(UsersRq rq) ->");
        }
        try {
            List<ErrorRs> errors = UsersHelper.validateUpdateUsers(rq, messages);
            if (Utils.isNotEmpty(errors)) {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_INVALID_INPUT);
                log.error(errorMessage);
                throw new InvalidUsersRqException(ErrorCodes.EC_INVALID_INPUT, errorMessage, errors);
            }
            Users users = UsersMapper.mapToUsers(rq, mapper);
            boolean operation = rq.isProvision();
            if (operation) {
                deprovisioning(users);
            } else {
                provisioning(users);
            }
            if (users.getId() != 0 && repo.existsById(users.getId())) {
                users = repo.save(users);
            }
            UsersRs rs = UsersMapper.maptoUsers(users, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_UPDATED_SUCCESSFULLY);
            return new UsersDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in updateUsers(UsersRq rq) -> " + e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findOneUsers(Integer id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing findUser(int id) ->");
        }
        try {
            Users users = repo.findById(id).orElseThrow(() -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_USER_NOT_FOUND);
                log.info(errorMessage);
                return new InvalidUsersRqException(ErrorCodes.EC_USER_NOT_FOUND, errorMessage);
            });
            UsersRs rs = UsersMapper.maptoUsers(users, mapper);
            String message = messages.getMessageProperty(MessageCodes.MC_RETRIEVED_SUCCESSFULLY);
            return new UsersDataRs(message, rs);
        } catch (Exception e) {
            log.error("Exception in findUser(int id) -> {}", e);
            throw e;
        }
    }

    @Override
    public BaseDataRs findAllUsers() {
        return null;
    }

    @Override
    public BaseDataRs deleteUsers(Integer id) {
        return null;
    }

    @Override
    public BaseDataRs findByEmailUsers(String email) {
        return null;
    }

    @Override
    public BaseDataRs findByUserName(String username) {
        return null;
    }

    @Override
    public void provisioning(Users rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing provisioning(Users rq) -> ");
        }
        try {
            if (rq.getRoles() == null) {
                return;
            }
            Roles role = roleRepo.findById(rq.getRoles().getId()).orElseThrow(() -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                return new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
            });
            rq.setRoles(role);
        } catch (Exception e) {
            log.error("Exception in provisioning(Users rq) -> {}", e);
            throw e;
        }
    }

    @Override
    public void deprovisioning(Users rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing deprovisioning(Users rq) -> ");
        }
        try {
            Optional.of(rq.getRoles()).map(role -> roleRepo.findById(role.getId())).orElseThrow(() -> {
                String errorMessage = messages.getErrorProperty(ErrorCodes.EC_ROLE_NOT_FOUND);
                log.warn(errorMessage);
                return new RolesNotFoundException(ErrorCodes.EC_ROLE_NOT_FOUND, errorMessage);
            });
            rq.setRoles(null);
        } catch (Exception e) {
            log.error("Exception in deprovisioning(Users rq) -> {}", e);
            throw e;
        }
    } /// 197.68.0.1:8087/api/users  {....} PUT / PATCH

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
