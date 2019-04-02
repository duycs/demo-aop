package user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import user.domain.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {}
