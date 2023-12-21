package org.cb.users.repo;

import org.cb.users.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepo extends JpaRepository<Permissions, Integer> {

    public boolean existsByCode(String code);

}
