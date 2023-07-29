package by.potapenko.model.repository;

import by.potapenko.model.enam.UserRole;
import by.potapenko.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findAllByRole(UserRole role);
}
