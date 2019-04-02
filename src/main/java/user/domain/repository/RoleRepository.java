package user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import user.domain.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
