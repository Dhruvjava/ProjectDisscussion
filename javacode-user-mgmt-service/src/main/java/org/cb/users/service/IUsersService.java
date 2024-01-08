package org.cb.users.service;

import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.entity.Users;
import org.cb.users.rq.UsersRq;

public interface IUsersService {

    public BaseDataRs saveUsers(UsersRq rq);

    public BaseDataRs updateUsers(UsersRq rq);

    public BaseDataRs findOneUsers(Integer id);

    public BaseDataRs findAllUsers();

    public BaseDataRs deleteUsers(Integer id);

    public BaseDataRs findByEmailUsers(String email);

    public BaseDataRs findByUserName(String username);

    public void provisioning(Users rq);

    public void deprovisioning(Users rq);

    public BaseDataRs enableUser(Integer id);

    public BaseDataRs unlockUser(Integer id);

    public BaseDataRs verifyUser(Integer id);

}
