package com.rubypaper.domain;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Board {
    @Id @GeneratedValue
    private Long seq;
    private String title;
    private String writer;
    private String content;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createDate;
    private Long cnt;
}
