package org.cb.users.repo;

import org.cb.users.entity.RoleToPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleToPermissionRepo extends JpaRepository<RoleToPermission, Integer> {
}
