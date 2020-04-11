package com.young.myboardweb.service;

import com.young.myboardweb.domain.Board;
import org.springframework.data.domain.Page;

public interface BoardService {

    void createBoard(Board board);
    Board readBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Board board);

    Page<Board> getBoardList(Board board);
}
