package org.cb.users.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.rq.RoleRq;
import org.cb.users.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@Slf4j
@Tag(name = "ROLES-REST", description = "Roles Related operation we can perform here")
public class RolesRest {

    @Autowired
    private IRoleService service;

    @PostMapping
    public ResponseEntity<BaseDataRs> createRole(@RequestBody RoleRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing Restfull Services - [POST: api.v1.roles] -> ");
        }
        try {
            return ResponseEntity.ok(service.createRole(rq));
        } catch (Exception e) {
            log.error("Exception in Restfull Services - [POST: api.v1.roles] -> {}", e);
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity<BaseDataRs> updateRole(@RequestBody RoleRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing Restfull Services - [PATCH: api.v1.roles] -> ");
        }
        try {
            return ResponseEntity.ok(service.updateRole(rq));
        } catch (Exception e) {
            log.error("Exception in Restfull Services - [POST: api.v1.roles] -> {}", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "FIND ROLES", description = "Find Roles Using role id")
//    @CustomApiResponses
    public ResponseEntity<BaseDataRs> findRoles(
            @PathVariable Integer id
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFullServices [POST: /api/roles] ->");
        }
        try {
            return ResponseEntity.ok(service.findOneRole(id));
        } catch (Exception e) {
            log.error("Exception in RESTFullServices [POST: /api/roles] -> {0}", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE ROLES", description = "Delete Roles using role id")
//    @CustomApiResponses
    public ResponseEntity<BaseDataRs> deleteRoles(
            @PathVariable Integer id
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFullServices [POST: /api/roles] ->");
        }
        try {
            return ResponseEntity.ok(service.deleteRole(id));
        } catch (Exception e) {
            log.error("Exception in RESTFullServices [POST: /api/roles] -> {0}", e);
            throw e;
        }
    }

    @GetMapping
    @Operation(summary = "RETRIEVE ROLES", description = "Retrieve Roles")
//    @CustomApiResponses
    public ResponseEntity<BaseDataRs> retrieveRoles(
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFullServices [POST: /api/roles] ->");
        }
        try {
            return ResponseEntity.ok(service.findAllRoles());
        } catch (Exception e) {
            log.error("Exception in RESTFullServices [POST: /api/roles] -> {0}", e);
            throw e;
        }
    }

}
