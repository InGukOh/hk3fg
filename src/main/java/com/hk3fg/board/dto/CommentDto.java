package com.hk3fg.board.dto;

import com.hk3fg.board.domain.entity.CommentEntity;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long comment_id;
    private String writer;
    private String comment;
    private Long contentNum;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CommentEntity toEntity(){

        logger.info("CommentDto : toEntity / Action : saving Data(댓글) | start");

        CommentEntity commentEntity = CommentEntity.builder()
                .comment_id(comment_id)
                .writer(writer)
                .comment(comment)
                .contentNum(contentNum)
                .build();
        logger.info("comment_id : " + comment_id + " write : " + writer +  " comment : "+ comment);
        logger.info("CommentDto : toEntity / Action : saving Data(댓글) | end\n");

        return commentEntity;
    }

    @Builder
    public CommentDto(Long comment_id,
                      String writer,
                      String comment,
                      Long contentNum,
                      LocalDateTime createdDate,
                      LocalDateTime modifiedDate) {
        logger.info("CommentEntity : CommentDto / Action : loading comment_id : "+ comment_id+" |");
        this.comment_id = comment_id;
        this.writer = writer;
        this.comment = comment;
        this.contentNum = contentNum;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


}
