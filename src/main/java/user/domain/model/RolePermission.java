package user.domain.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "roles_permissions")
@EntityListeners(AuditingEntityListener.class)
public class RolePermission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private User role_id;
	
	@ManyToOne
	@JoinColumn(name = "permission_id")
	private User permission_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getRole_id() {
		return role_id;
	}

	public void setRole_id(User role_id) {
		this.role_id = role_id;
	}

	public User getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(User permission_id) {
		this.permission_id = permission_id;
	}
	
	
}
