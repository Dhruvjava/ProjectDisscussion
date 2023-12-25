package org.cb.users.service;

import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.rq.RoleRq;

public interface IRoleService {

    public BaseDataRs createRole(RoleRq rq);

    public BaseDataRs updateRole(RoleRq rq);

    public BaseDataRs findOneRole(Integer id);

    public BaseDataRs deleteRole(Integer id);

    public BaseDataRs findAllRoles();

}
