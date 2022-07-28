package com.hk3fg.prj.domain;

import lombok.AllArgsConstructor; // 아래 AllArgsConstructor 어노테이션을 하면 자동 생성됨
import lombok.Getter;            // 아래 Getter 어노테이션을 하면 자동 생성됨
import lombok.NoArgsConstructor; // 아래 NoArgsConstructor 어노테이션을 하면 자동 생성됨
import lombok.Setter;            // 아래 Setter 어노테이션을 하면 자동 생성됨

import java.time.LocalDateTime; // 아래 변수 선언하면 자동 생성됨

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private Long num;
    private String title;
    private String content;
    private String name;
    private LocalDateTime createDate;
    private int read;
    private Long memberId;
}