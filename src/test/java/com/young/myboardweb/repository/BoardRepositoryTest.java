package com.young.myboardweb.repository;

import com.young.myboardweb.MyboardWebApplicationTests;
import com.young.myboardweb.domain.Board;
import com.young.myboardweb.domain.Enabled;
import com.young.myboardweb.domain.Role;
import com.young.myboardweb.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class BoardRepositoryTest extends MyboardWebApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PasswordEncoder encoder;

//    @Test
//    public void insertTest() {
//        User user1 = new User();
//        user1.setId("ela87");
//        user1.setPassword("abc123123");
//        user1.setName("전영욱");
//        user1.setRole(Role.ROLE_ADMIN);
//        user1.setEnabled(Enabled.YES);
//        userRepository.save(user1);
//
//        User user2 = new User();
//        user2.setId("mayer");
//        user2.setPassword("abc123123");
//        user2.setName("존");
//        user2.setRole(Role.ROLE_NORMAL);
//        user2.setEnabled(Enabled.YES);
//        userRepository.save(user2);
//
//        for (int i = 1; i <= 15; i++) {
//            Board board = new Board();
//            board.setCreatedAt(LocalDateTime.now());
//            board.setUser(user1);
//            board.setTitle(user1.getName() + " " + i);
//            board.setContent(user1.getName() + "님의 " + i + "번째 글입니다.");
//            boardRepository.save(board);
//        }
//
//        for (int i = 1; i <= 7; i++) {
//            Board board = new Board();
//            board.setCreatedAt(LocalDateTime.now());
//            board.setUser(user2);
//            board.setTitle(user2.getName() + " " + i);
//            board.setContent(user2.getName() + "님의 " + i + "번째 글입니다.");
//            boardRepository.save(board);
//        }
//    }
    @Test
    public void insertManyBoardsTest() {
        User user = new User();
        user.setId("tester");
        user.setPassword("123123");
        user.setName("영욱전");
        user.setRole(Role.ROLE_ADMIN);
        user.setEnabled(Enabled.YES);
        userRepository.save(user);

        for (int i = 0; i < 200; i++) {
            Board board = new Board();
            board.setCreatedAt(LocalDateTime.now());
            board.setUser(user);
            board.setTitle(user.getName() + " " + i + "번째 테스트");
            board.setContent(user.getName() + " " + i + "번째 테스트 본문글입니다.");
            boardRepository.save(board);
       }
    }

    @Test
    public void getBoardTest() {
        Board board = boardRepository.findById(1L)
                .get();

        System.out.println(board.getId() + "번 게시글 정보: ");
        System.out.println("제목: " + board.getTitle());
        System.out.println("작성자: " + board.getUser().getName());
        System.out.println("본문: " + board.getContent());
        System.out.println("작성일: " + board.getCreatedAt());
        System.out.println("조회수: " + board.getCnt());

    }

    @Test
    public void getBoardListTest() {
        User user = userRepository.findById("ela87").get();

        System.out.println(user.getName() + "님이 작성한 게시글들:");
        for (Board board: user.getBoardList()) {
            System.out.println(board.toString());
        }
    }

    @Test
    public void testPasswordEncoder() {
        User user = new User();
        user.setId("batman");
        user.setPassword(encoder.encode("waynecompany123"));
        user.setName("Bruce");
        user.setRole(Role.ROLE_NORMAL);
        user.setEnabled(Enabled.YES);
        userRepository.save(user);

        System.out.println(user.getPassword());
    }
}
