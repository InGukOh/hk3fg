package com.hk3fg.prj.mapper;

import com.hk3fg.prj.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {

    int boardCount(); // 곧 생성할 BoardMapper.xml 첫번째 sql 문의 id 와 같음.
                        // BoardMapper.xml에서 메소드 id와 같도록 함

    List<Board> getList();
}
