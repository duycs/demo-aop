package user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import user.domain.model.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {}
