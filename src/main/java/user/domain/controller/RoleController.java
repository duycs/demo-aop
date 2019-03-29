package user.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.domain.model.User;
import user.domain.model.UserRole;
import user.domain.repository.UserRepository;
import user.domain.repository.UserRoleRepository;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@GetMapping("/")
	public List<UserRole> getAllUsersRoles() {
		return userRoleRepository.findAll();
	}
}
