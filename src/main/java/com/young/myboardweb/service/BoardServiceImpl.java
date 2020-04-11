package com.young.myboardweb.service;

import com.young.myboardweb.domain.Board;
import com.young.myboardweb.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board readBoard(Board board) {
        return boardRepository.findById(board.getId()).get();
    }

    @Override
    public void updateBoard(Board board) {
        Board boardToBeUpdated = boardRepository.findById(board.getId()).get();

        boardToBeUpdated.setTitle(board.getTitle());
        boardToBeUpdated.setContent(board.getContent());
        boardRepository.save(boardToBeUpdated);
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getId());
    }

    @Override
    public Page<Board> getBoardList(Board board) {
        Pageable pageable = PageRequest.of(0, 15, Sort.Direction.DESC, "id");
        return boardRepository.getBoardList(pageable);
    }
}
