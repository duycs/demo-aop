package com.duycs.template.controller;

import com.duycs.template.entity.Template;
import com.duycs.template.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/templates")
@Slf4j
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping("/")
    public Optional<Template> addTemplate(@RequestBody AddTemplateVM addTemplateVM) {
        return templateService.addTemplate(addTemplateVM);
    }

}
