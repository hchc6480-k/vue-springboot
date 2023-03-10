package com.example.vuespringboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Reply {

    @Id @GeneratedValue
    @Column(name = "reply_no")
    private Long replyNo;
    private Long docNo;
    private String writer;
    private String content;
    private LocalDateTime regDttm;
}
