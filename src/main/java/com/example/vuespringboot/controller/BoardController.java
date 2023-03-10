package com.example.vuespringboot.controller;

import com.example.vuespringboot.domain.Board;
import com.example.vuespringboot.domain.Reply;
import com.example.vuespringboot.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ModelMapper modelMapper;

    @GetMapping("/list")
    public ResultDto getBoardList() {
        List<Board> boardList = boardService.findBoardList();
        List<BoardDto> collect = boardList.stream().map(BoardDto::new).collect(Collectors.toList());

        return new ResultDto(collect);
    }

    @GetMapping("/detail")
    public ResultDto getBoardDetail(@RequestParam("docNo") Long docNo) throws IllegalAccessException {
        Board boardDetail = boardService.findBoardDetail(docNo);
        BoardDto collect = null;

        if (boardDetail == null) {
            throw new IllegalArgumentException("존재하지 않는 게시물입니다.");
        } else {
             collect = modelMapper.map(boardDetail, BoardDto.class);
        }

        return new ResultDto(collect);
    }

    @GetMapping("/reply/list")
    public ResultDto getReplyList(@RequestParam("docNo") Long id) {
        List<Reply> replyList = boardService.findReplyList(id);
        List<ReplyDto> collect = replyList.stream().map(ReplyDto::new).collect(Collectors.toList());

        return new ResultDto(collect);
    }

    @PostMapping("/reply/insert")
    public ResultDto saveReply(HttpServletRequest req) {
        if (!req.getParameter("docNo").isBlank() && !req.getParameter("comment").isBlank()) {
            Reply reply = new Reply();
            reply.setDocNo(Long.parseLong(req.getParameter("docNo")));
            reply.setContent(req.getParameter("comment"));
            reply.setWriter("tester");
            reply.setRegDttm(LocalDateTime.now());
            boardService.saveReply(reply);
        }

        return new ResultDto("200");
    }

    @PostMapping("/reply/update")
    public ResultDto updateReply(HttpServletRequest req) {


        return new ResultDto("200");
    }

    @PostMapping("/reply/delete")
    public ResultDto deleteReply(HttpServletRequest req) {
        if (!req.getParameter("replyNo").isBlank() && !req.getParameter("docNo").isBlank()) {
            Reply reply = new Reply();
            reply.setReplyNo(Long.parseLong(req.getParameter("replyNo")));
            reply.setDocNo(Long.parseLong(req.getParameter("docNo")));
            boardService.deleteReply(reply);
        }

        return new ResultDto("200");
    }

    @Data
    @AllArgsConstructor
    private static class ResultDto<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만들어줌.
    static class BoardDto {
        private Long docNo;
        private String title;
        private String writer;
        private String content;
        private String regDttm;

        // ModelMapper는 기본 생성자를 이용 매핑한다 -> 기본생성자가 필요하다.
        public BoardDto() {

        }

        // lambda Board::new 사용하기 위해 생성자 생성
        public BoardDto(Board board) {
            this.docNo = board.getDocNo();
            this.title = board.getTitle();
            this.writer = board.getWriter();
            this.content = board.getContent();
            this.regDttm = board.getRegDttm();
        }

    }

    @Data
    @AllArgsConstructor
    static class ReplyDto {
        private Long replyNo;
        private Long docNo;
        private String writer;
        private String content;
        private String regDttm;

        public ReplyDto(Reply reply) {
            this.replyNo = reply.getReplyNo();
            this.docNo = reply.getDocNo();
            this.writer = reply.getWriter();
            this.content = reply.getContent();
            this.regDttm = reply.getRegDttm() != null ? reply.getRegDttm().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : null;
        }
    }

}
