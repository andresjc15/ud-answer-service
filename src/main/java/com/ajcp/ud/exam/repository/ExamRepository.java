package com.ajcp.ud.exam.repository;

import com.ajcp.ud.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query("select e from Exam e where e.name like %?1%")
    public List<Exam> findByName(String term);

}
