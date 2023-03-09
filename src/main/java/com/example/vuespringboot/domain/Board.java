package com.example.vuespringboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Getter @Setter
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "doc_no")
    private Long docNo;
    private String title;
    private String writer;
    private String content;
    private String regDttm;
}
