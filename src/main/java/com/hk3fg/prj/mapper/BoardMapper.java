package com.hk3fg.prj.mapper;

import com.hk3fg.prj.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {

    int boardCount(); // 곧 생성할 BoardMapper.xml 첫번째 sql 문의 id 와 같음.
                        // BoardMapper.xml에서 메소드 id와 같도록 함

    List<Board> getList();

    Board getBoard(Long num); // 게시글 목록 가져오기

    void uploadBoard(Board board); // 게시글 등록

    Object updateBoard(Board board); // 게시글 수정

    void deleteBoard(Long num); // 게시글 삭제

    void viewCount(Long num); //게시글 조회수 +1 상승
}
