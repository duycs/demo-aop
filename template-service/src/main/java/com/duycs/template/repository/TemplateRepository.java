package com.duycs.user.repository;

import com.duycs.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository  extends JpaRepository<Template,Long> {
    User findByTemplateId(Long templateId);
}
