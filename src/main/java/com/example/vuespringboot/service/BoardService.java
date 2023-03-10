package com.example.vuespringboot.service;

import com.example.vuespringboot.domain.Board;
import com.example.vuespringboot.domain.Reply;
import com.example.vuespringboot.repository.BoardRepository;
import com.example.vuespringboot.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public List<Board> findBoardList() {
        return boardRepository.findAll();
    }

    public Board findBoardDetail(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Reply> findReplyList(Long id) {
        return replyRepository.findAll(id);
    }

    public void saveReply(Reply reply) {
        replyRepository.save(reply);
    }

    public void deleteReply(Reply reply) {
        replyRepository.delete(reply);
    }

}
