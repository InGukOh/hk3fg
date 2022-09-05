package com.hk3fg.board.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board_comment")

public class CommentEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long comment_id;

    @Column(length = 50, nullable = false)
    private String writer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(length = 9,nullable = false)
    private int content_num;



    @Builder
    public CommentEntity(Long comment_id,int content_num, String writer, String comment) {
        Logger logger = LoggerFactory.getLogger("com.hk3fg.board.domain.entity");
        logger.info("comment_id : " + comment_id
                +" content_num : " + content_num
                + " writer : " + writer
                + " comment : "+ comment);
        this.comment_id = comment_id;
        this.content_num = content_num;
        this.writer = writer;
        this.comment = comment;
    }
}