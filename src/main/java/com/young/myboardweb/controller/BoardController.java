package com.young.myboardweb.controller;

import com.young.myboardweb.domain.Board;
import com.young.myboardweb.domain.Search;
import com.young.myboardweb.security.SecurityUser;
import com.young.myboardweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/getBoardList")
    public String getBoardList(Model model, @PageableDefault Pageable pageable, Search search) {
        if (search.getSearchCondition() == null) search.setSearchCondition("TITLE");
        if (search.getSearchKeyword() == null) search.setSearchKeyword("");
        Page<Board> boardPage = boardService.getBoardList(pageable, search);
        model.addAttribute("boardPage", boardPage);
        return "board/getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        model.addAttribute("board", boardService.readBoard(board));
        return "board/getBoard";
    }

    @GetMapping("/createBoard")
    public String createBoardView() {
        return "board/createBoard";
    }

    @PostMapping("/createBoard")
    public String createBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
        board.setUser(principal.getUser());
        boardService.createBoard(board);
        return "redirect:getBoardList";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(Board board) {
        boardService.updateBoard(board);
        return "forward:getBoardList";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        boardService.deleteBoard(board);
        return "forward:getBoardList";
    }
}
