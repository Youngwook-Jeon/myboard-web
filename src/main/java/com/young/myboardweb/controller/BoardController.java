package com.young.myboardweb.controller;

import com.young.myboardweb.domain.Board;
import com.young.myboardweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String getBoardList(Model model, Board board) {
        Page<Board> boardPage = boardService.getBoardList(board);
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
    public String createBoard(Board board) {
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
