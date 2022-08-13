package com.ajcp.ud.exam.service.impl;

import com.ajcp.ud.exam.entity.Exam;
import com.ajcp.ud.exam.entity.Question;
import com.ajcp.ud.exam.repository.ExamRepository;
import com.ajcp.ud.exam.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    public List<Exam> findAll() { return examRepository.findAll(); }

    @Override
    public Optional<Exam> findById(Long id) { return examRepository.findById(id); }

    @Override
    public Exam save(Exam exam) { return examRepository.save(exam); }

    @Override
    public Exam update(Exam exam) {
        return examRepository.findById(exam.getId()).map(e -> {
            e.setName(exam.getName());
            List<Question> deletedQuestions = new ArrayList<>();
            /*e.getQuestions().forEach(qob ->  {
                if (!exam.getQuestions().contains(qob)) {
                    deletedQuestions.add(qob);
                }
            });*/
            e.getQuestions()
                    .stream()
                    .filter(qob -> !exam.getQuestions().contains(qob))
                    .forEach(e::removeQuestion);

            e.setQuestions(exam.getQuestions());
            return examRepository.save(e);
        }).orElse(null);
    }

    @Override
    public Optional<Exam> delete(Long id) {
        return examRepository.findById(id).map(e -> {
            e.setEnabled(false);
            return examRepository.save(e);
        });
    }

}
