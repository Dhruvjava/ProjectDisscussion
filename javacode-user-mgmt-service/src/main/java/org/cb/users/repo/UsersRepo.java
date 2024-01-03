package org.cb.users.repo;

import org.cb.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Integer> {

    public Optional<Users> findByEmail(String email);

}
