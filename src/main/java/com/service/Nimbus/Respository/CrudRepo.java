package com.service.Nimbus.Respository;

import com.service.Nimbus.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudRepo extends CrudRepository<User, Long> {
    boolean existsByUsername(String userName);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT id FROM users WHERE username = :username", nativeQuery = true)
    Long findIdFromUsername(@Param("username") String username);
}
