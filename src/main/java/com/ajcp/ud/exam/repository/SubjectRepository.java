package com.ajcp.ud.exam.repository;

import com.ajcp.ud.exam.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
