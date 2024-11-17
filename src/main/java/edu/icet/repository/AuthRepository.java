package edu.icet.repository;

import edu.icet.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<LoginEntity, String> {
    LoginEntity findByEmail(String email);
}
