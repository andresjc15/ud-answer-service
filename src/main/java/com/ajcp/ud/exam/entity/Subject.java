package com.ajcp.ud.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @JsonIgnoreProperties(value = {"children"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject father;

    @JsonIgnoreProperties(value = {"father"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "father", cascade = CascadeType.ALL)
    private List<Subject> children;

    public Subject() {
        this.children = new ArrayList<>();
    }

}
