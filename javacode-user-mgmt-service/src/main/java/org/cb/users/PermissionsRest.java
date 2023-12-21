package org.cb.users;

import lombok.extern.slf4j.Slf4j;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.rq.PermissionsRq;
import org.cb.users.service.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/permissions")
@Slf4j
public class PermissionsRest {

    @Autowired
    private IPermissionsService service;

    @PostMapping
    public ResponseEntity<BaseDataRs> createPermissions(@RequestBody PermissionsRq rq) {
        try {
            return new ResponseEntity<>(service.createPermissions(rq), HttpStatus.CREATED);
        } catch (Exception e) {
            throw e;
        }
    }

}
