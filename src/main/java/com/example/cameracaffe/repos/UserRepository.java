package com.example.cameracaffe.repos;

import com.example.cameracaffe.DTO.Roles;
import com.example.cameracaffe.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    List<UserEntity> findByRole(Roles role);
}
