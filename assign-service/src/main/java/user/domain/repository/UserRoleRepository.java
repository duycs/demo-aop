package user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import user.domain.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Async
	// @Query("SELECT * FROM UserRole t WHERE t.user_id = :userId AND t.role_id =
	// :roleId")
	public Optional<UserRole> findByUserAndRole(@Param("user_id") Long userId, @Param("role_id") Long roleId);

}
