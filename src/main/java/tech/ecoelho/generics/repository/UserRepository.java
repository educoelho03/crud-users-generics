package tech.ecoelho.generics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ecoelho.generics.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
