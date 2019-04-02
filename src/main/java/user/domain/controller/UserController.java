package user.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import user.domain.exception.ResourceNotFoundException;
import user.domain.model.User;
import user.domain.model.UserRole;
import user.domain.repository.RolePermissionRepository;
import user.domain.repository.UserRepository;
import user.domain.repository.UserRoleRepository;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Users")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private RolePermissionRepository rolePermissionRepository;

	/**
	 * Get all users list.
	 *
	 * @return the list
	 */
	@ApiOperation(value = "View a list of available users", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping()
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Gets users by id.
	 *
	 * @param userId the user id
	 * @return the users by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@ApiOperation(value = "Get an user by Id")
	@GetMapping("/{id}")
	public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	/**
	 * Create user user.
	 *
	 * @param user the user
	 * @return the user
	 */
	@PostMapping("/")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	/**
	 * Update user response entity.
	 *
	 * @param userId      the user id
	 * @param userDetails the user details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(
			@ApiParam(value = "User Id from which users objects from database table", required = true) @PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

		user.setEmail(userDetails.getEmail());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		user.setUpdatedAt(new Date());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Delete user map.
	 *
	 * @param userId the user id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	// Methods user by role
	@PostMapping("/userRole")
	public UserRole createUserRole(@Valid @RequestBody UserRole userRole) {
		return userRoleRepository.save(userRole);
	}

	@GetMapping("/{user_id}/roles/{role_id}")
	public ResponseEntity<User> getUserByRole(@PathVariable(value = "user_id") Long userId,
			@PathVariable(value = "role_id") Long roleId) throws Exception {
		UserRole userRole = userRoleRepository.findByUserAndRole(userId, roleId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

		User user = userRepository.findById(userRole.getUser().getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

		return ResponseEntity.ok().body(user);
	}

}
