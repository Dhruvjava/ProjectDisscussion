package org.cb.users.service;

import org.cb.base.data.rs.BaseDataRs;
import org.cb.base.entity.BaseBO;
import org.cb.users.entity.Permissions;
import org.cb.users.rq.PermissionsRq;

public interface IPermissionsService {

    public BaseDataRs createPermissions(PermissionsRq permissions);

    public BaseDataRs updatePermissions(PermissionsRq permissions);

    public BaseDataRs findOnePermissions(Integer id);

    public BaseDataRs findAllPermissions();

    public BaseDataRs deletePermissions(Integer id);

}
