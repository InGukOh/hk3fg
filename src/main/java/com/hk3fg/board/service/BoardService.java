package com.hk3fg.board.service;

import com.hk3fg.board.domain.entity.CommentEntity;
import com.hk3fg.board.domain.repository.CommentRepository;
import com.hk3fg.board.dto.BoardDto;
import com.hk3fg.board.domain.entity.BoardEntity;
import com.hk3fg.board.domain.repository.BoardRepository;

import com.hk3fg.board.dto.CommentDto;
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
    private CommentRepository commentRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 10;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 20;       // 한 페이지에 존재하는 게시글 수
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /* !!!!!!!!!!! 게시판 기능 관련 !!!!!!!!!!! */
    @Transactional
    public List<BoardDto> getBoardlist(Integer pageNum) {
        logger.info("BoardService : getBoardlist / Action : getting Entity | start");
        Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "id")));

        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }
        logger.info("BoardService : getBoardlist / Action : getting Entity | end\n");
        return boardDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        logger.info("BoardService : getBoardCount / Action : getting Entity | Activate");
        return boardRepository.count();

    }

    @Transactional
    public BoardDto getPost(Long id) {
        logger.info("BoardService : getPost / Action : get Data(게시글) | start");

        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        logger.info("BoardService : getPost / Action : get Data(게시글) | end\n");
        return this.convertEntityToDto(boardEntity);
    }

    @Transactional
    public Long savePost_Write(BoardDto boardDto) {
        logger.info("BoardService : savePost / Action : save Data(게시글) | Activate\n");
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        logger.info("BoardService : deletePost / Action : delete Data(게시글) | Activate\n");
        boardRepository.deleteById(id);
    }

    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        logger.info("BoardService : searchPosts / Action : search Data(게시글) | start");

        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtoList;

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }
        logger.info("BoardService : searchPosts / Action : search Data(게시글) | end\n");
        return boardDtoList;
    }

    public Integer[] getPageList(Integer curPageNum,String Method_Name,String keyword) {
        logger.info("BoardService : getPageList / Action : get Data(전체 게시글 갯수) & creating PageBlocks | start");
        //입력된 페이지 기록

        logger.info("CurPageNum : " + Integer.toString(curPageNum));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum =
                (totalLastPageNum(Method_Name,keyword) > curPageNum + BLOCK_PAGE_NUM_COUNT)?
                        curPageNum + BLOCK_PAGE_NUM_COUNT : totalLastPageNum(Method_Name,keyword);
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

        logger.info("BoardService : getPageList / Action : get Data & creating PageBlocks | end\n");
        return pageList;
    }

    public Integer totalLastPageNum(String Method_Name,String keyword){

        logger.info("BoardService : totalLastPageNum / Action : get Data(전체 게시글 갯수) & check LastPageNum | start");
        //총 게시글 갯수

        Double postsTotalCount = 0.0;
        if(Method_Name.equals("list")){
           postsTotalCount = Double.valueOf(this.getBoardCount());
        } else if(Method_Name.equals("search")){
           postsTotalCount = Double.valueOf(this.boardRepository.findByTitleContaining(keyword).stream().count());
        }

        logger.info("PostTotalCount : "+ postsTotalCount);


        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
        logger.info("TotalLastPageNum : "+ totalLastPageNum);

        logger.info("BoardService : totalLastPageNum / Action : get Data(전체 게시글 갯수) & check LastPageNum | end\n");
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

    /* !!!!!!!!!!! 댓글 기능 관련 !!!!!!!!!!! */
    @Transactional
    public List<CommentDto> getPost_Comment(Long id) {
        logger.info("BoardService : getPost_Comment / Action : get Data(게시글의 댓글) | start");

        List<CommentEntity> commentEntities = commentRepository.findByContentNum(id);
        List<CommentDto> commentDtoList = new ArrayList<>();

        if (commentEntities.isEmpty()) return commentDtoList;

        for(CommentEntity commentEntity : commentEntities){
            commentDtoList.add(this.convertEntityToDto(commentEntity));
        }

        logger.info("=======================================\n"+commentEntities.size());
        logger.info("BoardService : getPost / Action : get Data(게시글) | end\n");
        return commentDtoList;
    }

    @Transactional
    public Long savePost_Comment(CommentDto commentDto) {
        logger.info("BoardService : savePost_Comment / Action : save Data(댓글) | Activate\n");
        return commentRepository.save(commentDto.toEntity()).getComment_id();
    }

    private CommentDto convertEntityToDto(CommentEntity commentEntity){
        return CommentDto.builder()
                .comment_id(commentEntity.getComment_id())
                .contentNum(commentEntity.getContentNum())
                .writer(commentEntity.getWriter())
                .comment(commentEntity.getComment())
                .createdDate(commentEntity.getCreatedDate())
                .modifiedDate(commentEntity.getModifiedDate())
                .build();
    }
}
