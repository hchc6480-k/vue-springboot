package com.example.vuespringboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Reply {

    @Id @GeneratedValue
    private Long replyNo;
    private Long docNo;
    private String writer;
    private String content;
    private String regDttm;
}
