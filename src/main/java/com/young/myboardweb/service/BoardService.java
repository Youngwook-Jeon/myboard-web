package com.young.myboardweb.service;

import com.young.myboardweb.domain.Board;
import com.young.myboardweb.domain.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    void createBoard(Board board);
    Board readBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(Board board);

    Page<Board> getBoardList(Pageable pageable, Search search);
}
