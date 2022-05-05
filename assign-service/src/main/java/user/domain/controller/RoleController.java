package user.domain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.domain.exception.ResourceNotFoundException;
import user.domain.model.Role;
import user.domain.model.RolePermission;
import user.domain.repository.RolePermissionRepository;
import user.domain.repository.RoleRepository;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;
	private RolePermissionRepository rolePermissionRepository;

	@GetMapping()
	public List<Role> getAllRoles() throws ResourceNotFoundException {
		return roleRepository.findAll();
	}

	@GetMapping("/{role_id}")
	public Role getRoleById(@PathVariable(value = "role_id") Long roleId) throws ResourceNotFoundException {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found on :: " + roleId));
		return role;
	}

	@PostMapping("/")
	public Role createRole(@Valid @RequestBody Role role) {
		return roleRepository.save(role);
	}
	
	@PostMapping("/rolePermission")
	public RolePermission createRolePermission(@Valid @RequestBody RolePermission rolePermission) {
		return rolePermissionRepository.save(rolePermission);
	}

	@DeleteMapping("/{role_id}")
	public Map<String, Boolean> deleteRole(@PathVariable(value = "role_id") Long roleId)
			throws ResourceNotFoundException {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found on :: " + roleId));

		roleRepository.delete(role);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
