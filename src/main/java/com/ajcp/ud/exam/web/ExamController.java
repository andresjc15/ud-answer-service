package com.ajcp.ud.exam.web;

import com.ajcp.ud.exam.entity.Exam;
import com.ajcp.ud.exam.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public ResponseEntity<?> getExams() {
        return ResponseEntity.ok(examService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExam(@PathVariable Long id) {
        return examService.findById(id)
                .map(obj -> ResponseEntity.status(HttpStatus.OK).body(obj))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Exam exam) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.save(exam));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Exam exam) {
        return ResponseEntity.status(HttpStatus.OK).body(examService.update(exam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return examService.delete(id)
                .map(obj -> ResponseEntity.ok(obj))
                .orElse(ResponseEntity.notFound().build());
    }

}
