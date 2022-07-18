package com.example.nace.job.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NACE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @Column(name="ORDERNO")
    private Long orderno;

    private String level;
    private String code;
    private String parent;

    private String description;

    @Column(length = 5000)
    private String thisItemIncludes;
    @Column(length = 10000)
    private String thisItemAlsoIncludes;

    private String rulings;
    @Column(length = 5000)
    private String thisItemExcludes;
    private String referenceToISIC4;

}
