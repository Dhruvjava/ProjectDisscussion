package org.cb.users.repo;

import org.cb.users.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Roles, Integer> {
    boolean existsByCode(String code);
}
