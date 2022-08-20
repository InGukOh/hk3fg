package com.hk3fg.board.service;

import com.hk3fg.board.dto.BoardDto;
import com.hk3fg.board.domain.entity.BoardEntity;
import com.hk3fg.board.domain.repository.BoardRepository;
import jdk.internal.jline.internal.Log;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 10;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 20;       // 한 페이지에 존재하는 게시글 수

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Transactional
    public List<BoardDto> getBoardlist(Integer pageNum) {
        Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));

        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        return this.convertEntityToDto(boardEntity);
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtoList;

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }

    public Integer[] getPageList(Integer curPageNum) {
        //입력된 페이지 기록
        logger.info("CurPageNum : " + Integer.toString(curPageNum));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum =
                (totalLastPageNum() > curPageNum + BLOCK_PAGE_NUM_COUNT)?
                        curPageNum + BLOCK_PAGE_NUM_COUNT : totalLastPageNum();
        logger.info("BlockLastPageNum : " + blockLastPageNum);

        //입력된 페이지와 마지막 페이지 비교
        if(curPageNum > blockLastPageNum) curPageNum = blockLastPageNum;
        logger.info("curPageNum : " + curPageNum);

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum-10<=0)? 1 : curPageNum-5;

        // 페이지 번호 할당
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];
        int calc = (curPageNum + 9 < blockLastPageNum)? curPageNum + 9 : blockLastPageNum;
        for (int val = curPageNum, idx = 0; val <= calc; val++, idx++) {
           pageList[idx] = val;
        }
        logger.info("pageList " + Arrays.toString(pageList));

        return pageList;
    }

    public Integer totalLastPageNum(){

        //총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());
        logger.info("PostTotalCount : "+ postsTotalCount);


        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
        logger.info("TotalLastPageNum : "+ totalLastPageNum);

        return totalLastPageNum;
    }

    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }


}
