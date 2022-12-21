package com.duycs.template.service;

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
public class TemplateService {

    @Autowired
    private UserRepository templateRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    public User addTemplate(AddTemplateVM addTemplateVM) {
        Template template = modelMapper.map(addTemplateVM, User.class);
        return templateRepository.save(template);
    }

    public void deleteTemplate(int templateId) {
        var templateExisting = templateRepository.findByTemplateId(templateId)
                .orElseThrow(() - > new ResourceNotFoundException("Template not found for this id :: " + userId));

        templateRepository.delete(templateExisting);
    }

    public BuildTemplate getBuildTemplateA(){
        TemplateA templateA = new TemplateA();
        templateA.build();
    }


}
