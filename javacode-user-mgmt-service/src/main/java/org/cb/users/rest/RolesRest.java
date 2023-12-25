package org.cb.users.rest;

import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.rq.RoleRq;
import org.cb.users.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
public class RolesRest {

    @Autowired
    private IRoleService service;

    @PostMapping
    public ResponseEntity<BaseDataRs> createRole(@RequestBody RoleRq rq){
        return ResponseEntity.ok(service.createRole(rq));
    }

}
