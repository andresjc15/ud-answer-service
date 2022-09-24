package com.ajcp.ud.exam.service;

import com.ajcp.ud.exam.entity.Exam;
import com.ajcp.ud.exam.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    public List<Exam> findAll();

    public Page<Exam> findAll(Pageable pageable);

    public Optional<Exam> findById(Long id);

    public Exam save(Exam exam);

    public Exam update(Exam exam);

    public Optional<Exam> delete(Long id);

    public List<Exam> findByName(String term);

    public List<Subject> findAllSubjects();

}
