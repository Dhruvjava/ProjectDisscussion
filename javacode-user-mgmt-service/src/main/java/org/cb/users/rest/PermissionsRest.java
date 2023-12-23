package org.cb.users.rest;

import lombok.extern.slf4j.Slf4j;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.rq.PermissionsRq;
import org.cb.users.service.IPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<BaseDataRs> updatePermissions(@RequestBody PermissionsRq rq) {
        try {
            return new ResponseEntity<>(service.updatePermissions(rq), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseDataRs> findOnePermission(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.findOnePermissions(id), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping()
    public ResponseEntity<BaseDataRs> findAllPermission() {
        try {
            return new ResponseEntity<>(service.findAllPermissions(), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseDataRs> deletePermission(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.deletePermissions(id), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

}
