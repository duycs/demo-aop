package com.duycs.user.service;

import com.duycs.user.VO.Department;
import com.duycs.user.VO.ResponseTemplateVO;
import com.duycs.user.entity.User;
import com.duycs.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    public User addUser(AddUserVM addUserVM) {
        User user = modelMapper.map(addUserVM, User.class);
        return userRepository.save(user);
    }

    public User updateUser(UpdateUserVM updateUserVM) {

        var userExisting = userRepository.findByUserId(updateUserVM.userId)
                .orElseThrow(() - > new ResourceNotFoundException("User not found for this id :: " + updateUserVM.userId));

        User user = modelMapper.map(updateUserVM, User.class);
        return userRepository.save(userExisting);
    }

    public void deleteUser(int userId) {
        var userExisting = userRepository.findByUserId(userId)
                .orElseThrow(() - > new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(userExisting);
    }

    public Optional<ResponseTemplateVO> getUserWithDepartment(Long userId) throws ResourceNotFoundException{
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                        ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
}
