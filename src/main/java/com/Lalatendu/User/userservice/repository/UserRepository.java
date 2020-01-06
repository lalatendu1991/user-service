package com.Lalatendu.User.userservice.repository;

import com.Lalatendu.User.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndStatus(String email, String status);

    Optional<User> findByIdAndStatus(Long id, String status);

    @Override
    Optional<User> findById(Long id);
}
