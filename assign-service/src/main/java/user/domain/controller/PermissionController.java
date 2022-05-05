package user.domain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import user.domain.exception.ResourceNotFoundException;
import user.domain.model.Permission;
import user.domain.repository.PermissionRepository;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {
	@Autowired
	private PermissionRepository permissionRepository;

	@GetMapping()
	public List<Permission> getAllPermissions() {
		return permissionRepository.findAll();
	}

	@GetMapping("/{permission_id}")
	public ResponseEntity<Permission> getUsersById(@PathVariable(value = "permission_id") Long permissionId)
			throws ResourceNotFoundException {
		Permission permission = permissionRepository.findById(permissionId)
				.orElseThrow(() -> new ResourceNotFoundException("Permission not found on :: " + permissionId));
		return ResponseEntity.ok().body(permission);
	}

	@PostMapping("/")
	public Permission createPermission(@Valid @RequestBody Permission permission) {
		return permissionRepository.save(permission);
	}

	@DeleteMapping("/{permission_id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "permission_id") Long permissionId) throws Exception {
		Permission permission = permissionRepository.findById(permissionId)
				.orElseThrow(() -> new ResourceNotFoundException("Permission not found on :: " + permissionId));

		permissionRepository.delete(permission);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
