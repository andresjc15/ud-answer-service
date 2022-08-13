package com.ajcp.ud.exam.service;

import com.ajcp.ud.exam.entity.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    public List<Exam> findAll();

    public Optional<Exam> findById(Long id);

    public Exam save(Exam exam);

    public Exam update(Exam exam);

    public Optional<Exam> delete(Long id);

}
