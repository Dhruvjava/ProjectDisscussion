package org.cb.users.service;

import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.entity.Users;
import org.cb.users.rq.UsersRq;

public interface IUsersService {

    public BaseDataRs saveUsers(UsersRq rq);

    public BaseDataRs updateUsers(UsersRq rq);

    public BaseDataRs findOneUsers(UsersRq rq);

    public BaseDataRs findAllUsers(UsersRq rq);

    public BaseDataRs deleteUsers(UsersRq rq);

    public BaseDataRs findByEmailUsers(UsersRq rq);

    public BaseDataRs findByUserName(UsersRq rq);

    public BaseDataRs provisioning(Users rq);

    public BaseDataRs deprovisioning(Users rq);

    public BaseDataRs enableUser(Integer id);

    public BaseDataRs unlockUser(Integer id);

    public BaseDataRs verifyUser(Integer id);

}
