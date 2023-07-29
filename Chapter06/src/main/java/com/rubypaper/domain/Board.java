package com.rubypaper.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Setter
@Getter
@ToString
@Entity
public class Board {
    @Id @GeneratedValue
    private Long seq;
    private String title;
    @Column(updatable = false)
    private String writer;
    private String content;
    @Column(insertable=false, updatable=false, columnDefinition="date default sysdate")
    private Date createDate;
    @Column(insertable=false, updatable=false, columnDefinition="number default 0")
    private Long cnt;
}
