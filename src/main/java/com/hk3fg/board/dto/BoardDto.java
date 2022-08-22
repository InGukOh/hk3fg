package com.hk3fg.board.dto;

import com.hk3fg.board.domain.entity.BoardEntity;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BoardEntity toEntity(){

        logger.info("BoardDto : toEntity / Action : saving Data(게시글) | start");

        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        logger.info("BoardDto : toEntity / Action : saving Data(게시글) | end\n");

        return boardEntity;
    }

    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        logger.info("BoardDto : BoardDto / Action : loading id : "+ id+" |");
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


}
