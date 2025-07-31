package com.service.Nimbus.Respository;

import com.service.Nimbus.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudRepo extends CrudRepository<User, Long> {
    boolean existsByUsername(String userName);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
