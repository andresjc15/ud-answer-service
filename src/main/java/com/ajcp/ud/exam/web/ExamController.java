package com.ajcp.ud.exam.web;

import com.ajcp.ud.exam.entity.Exam;
import com.ajcp.ud.exam.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public ResponseEntity<?> getExams() {
        return ResponseEntity.ok(examService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<?> getExams(Pageable pageable) {
        return ResponseEntity.ok(examService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExam(@PathVariable Long id) {
        return examService.findById(id)
                .map(obj -> ResponseEntity.status(HttpStatus.OK).body(obj))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody Exam exam, BindingResult result) {
        if (result.hasErrors()) {
            return this.validate(result);
        }
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

    @GetMapping("/filter/{term}")
    public ResponseEntity<?> filterByName(@PathVariable String term) {
        return ResponseEntity.ok(examService.findByName(term));
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> listSubject() {
        return ResponseEntity.ok(examService.findAllSubjects());
    }

    protected ResponseEntity<?> validate(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
