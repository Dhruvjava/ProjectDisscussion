package org.cb;

import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.datars.PermissionsDataRs;
import org.cb.users.rq.PermissionsRq;
import org.cb.users.rs.PermissionsRs;
import org.cb.users.service.IPermissionsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionServiceImplTest extends JavacodeUserMgmtServiceApplicationTests {

    @Autowired
    private IPermissionsService service;

    @Test
    @Order(1)
    @DisplayName("CREATE PERMISSION")
    public void createPermission() {

        PermissionsRq rq = new PermissionsRq();
        rq.setCode("VIEW");
        rq.setName("View");
        BaseDataRs baseDataRs = service.createPermissions(rq);
        PermissionsDataRs permissionsDataRs = (PermissionsDataRs) baseDataRs;
        PermissionsRs rs = permissionsDataRs.getPermission();
        Assertions.assertNotNull(baseDataRs);
        Assertions.assertNotNull(rs);
        Assertions.assertNotNull(rs.getCode());
        Assertions.assertNotNull(rs.getName());

    }

    @Test
    @Order(2)
    @DisplayName("UPDATE PERMISSION")
    public void updatePermission() {

        PermissionsRq rq = new PermissionsRq();
        rq.setId(1);
        rq.setCode("VIEW");
        rq.setName("View");
        BaseDataRs baseDataRs = service.updatePermissions(rq);
        PermissionsDataRs permissionsDataRs = (PermissionsDataRs) baseDataRs;
        PermissionsRs rs = permissionsDataRs.getPermission();
        Assertions.assertNotNull(baseDataRs);
        Assertions.assertNotNull(rs);
        Assertions.assertNotNull(rs.getCode());
        Assertions.assertNotNull(rs.getName());

    }

}
