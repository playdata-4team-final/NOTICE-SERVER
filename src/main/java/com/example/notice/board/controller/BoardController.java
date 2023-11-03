package com.example.notice.board.controller;

import com.example.notice.admin.dto.ClassMiniCommentDto;
import com.example.notice.admin.dto.NoticeCommentDto;
import com.example.notice.admin.dto.NoticeMiniCommentDto;
import com.example.notice.board.domain.entity.ClassBoard;
import com.example.notice.board.domain.entity.ClassComment;
import com.example.notice.board.domain.request.*;
import com.example.notice.board.domain.response.*;
import com.example.notice.board.service.BoardService;
import com.example.notice.global.domain.response.LmsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    //공지사항 보기
    @GetMapping("/getNotice")
    public LmsResponse<NoticeRes> getNotice(NoticeGetRequest noticeGetRequest){
        NoticeRes noticeRes = boardService.getNotice(noticeGetRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeRes, "서비스 성공", "에러 없음", LocalDateTime.now());

    }

    //공지사항 생성
    @PostMapping("/createNotice")
    public LmsResponse<NoticeRes> createNotice(NoticeCreateRequest noticeCreateRequest){
        NoticeRes noticeRes = boardService.writeNotice(noticeCreateRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeRes, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //공지사항 삭제
    @PostMapping("/delteNotice")
    public LmsResponse<String> deleteNotice(NoticeDeleteRequest noticedeleteRequest){
        String s = boardService.deleteNotice(noticedeleteRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //공지사항 파일 보기
    @GetMapping("/getNoticeFile")
    public LmsResponse<NoticeRes> getNoticeFile(NoticeGetRequest noticeFileRequest){
        NoticeRes noticeFile = boardService.getNoticeFile(noticeFileRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeFile, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //공지사항 파일 업로드
    @PostMapping("uploadNoticeFile")
    public LmsResponse<String> uploadNoticeFile(NoticeFileRequest noticeFileRequest){
        String s = boardService.uploadNoticeFile(noticeFileRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());

    }

    //공지사항 파일 삭제
    @PostMapping("/deleteNoticeFile")
    public  LmsResponse<String> deleteNoticeFile(NoticeFileRequest noticeFileRequest){
        String s = boardService.uploadNoticeFile(noticeFileRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }
    
    //공지사항 댓글 보기
    @GetMapping("/getNoticeComments")
    public LmsResponse<NoticeCommentRes> getNoticeComments(NoticeGetRequest noticeCommentRequest){
        NoticeCommentRes noticeComments = boardService.getNoticeComments(noticeCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeComments, "서비스 성공", "에러 없음", LocalDateTime.now());

    }

    //공지사항 댓글 작성
    @PostMapping("/writeNoticeComments")
    public LmsResponse<NoticeCommentRes> writeNoticeComments(NoticeCommentRequest noticeCommentRequest){
        NoticeCommentRes noticeCommentRes = boardService.writeNoticeComments(noticeCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeCommentRes, "서비스 성공", "에러 없음", LocalDateTime.now());
    }


    //공지사항 댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @PostMapping("/deleteNoticeComments")
    public LmsResponse<String> deleteNoticeComments(NoticeDeleteCommentRequest noticeDeleteCommentRequest){
        String s = boardService.deleteNoticeComments(noticeDeleteCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //공지사항 댓글 수정
    @PostMapping("/uploadNoticeComments")
    public LmsResponse<String> uploadNoticeComments(NoticeUpdateCommentRequest noticeUpdateCommentRequest){
        String s = boardService.uploadNoticeComments(noticeUpdateCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }
    
    //공지사항 대댓글 보기
    @GetMapping
    public LmsResponse<NoticeMiniCommentRes> getNoticeMiniComments(NoticeGetMiniCommentRequest noticeGetMiniCommentRequest){
        NoticeMiniCommentRes noticeMiniComments = boardService.getNoticeMiniComments(noticeGetMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeMiniComments, "서비스 성공", "에러 없음", LocalDateTime.now());

    }
    
    //공지 사항 대댓글 작성
    @PostMapping("/writeNoticeMiniComments")
    public LmsResponse<NoticeMiniCommentRes> writeNoticeMiniComments(NoticeMiniCommentRequest noticeMiniCommentRequest){
        NoticeMiniCommentRes noticeMiniCommentRes = boardService.writeNoticeMiniComments(noticeMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeMiniCommentRes, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //공지사항 대댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @PostMapping("/deleteNoticeMiniComments")
    public LmsResponse<String> deleteNoticeMiniComments(NoticeDeleteMiniCommentRequest noticeDeleteMiniCommentRequest){
        String s = boardService.deleteNoticeMiniComments(noticeDeleteMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //공지사항 대댓글 수정
    @PostMapping("/uploadNoticeMiniComments")
    public LmsResponse<String> uploadNoticeMiniComments(NoticeUpdateMiniCommentRequest noticeUpdateMiniCommentRequest){
        String s = boardService.uploadNoticeMiniComments(noticeUpdateMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 보기
    @GetMapping("/getClass")
    public LmsResponse<ClassBoardRes> getClass(ClassGetRequest classgetRequest){
        ClassBoardRes aClass = boardService.getClass(classgetRequest);
        return new LmsResponse<>(HttpStatus.OK, aClass, "서비스 성공", "에러 없음", LocalDateTime.now());

    }

    //강의게시판 생성
    @PostMapping("/createClass")
    public LmsResponse<ClassBoardRes> createClass(ClassCreateRequest classCreateRequest){
        ClassBoardRes classBoardRes = boardService.writeClass(classCreateRequest);
        return new LmsResponse<>(HttpStatus.OK, classBoardRes, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 삭제
    @PostMapping("/deleteClass")
    public LmsResponse<String> deleteClass(ClassDeleteRequest classdeleteRequest){
        String s = boardService.deleteClass(classdeleteRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 파일 보기
    @GetMapping
    public LmsResponse<ClassBoardRes> getClassFile(ClassGetRequest classGetFileRequest){
        ClassBoardRes classFile = boardService.getClassFile(classGetFileRequest);
        return new LmsResponse<>(HttpStatus.OK,classFile, "서비스 성공", "에러 없음", LocalDateTime.now());

    }
    //강의 파일 업로드
    @PostMapping("/uploadClassFile")
    public LmsResponse<String> uploadClassFile(ClassFileRequest classFileRequest){
        String s = boardService.uploadClassFile(classFileRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }


    //강의 파일 삭제
    @PostMapping("/deleteClassFile")
    public LmsResponse<String> deleteClassFile(ClassFileRequest classFileRequest){
        String s = boardService.deleteClassFile(classFileRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 댓글 보기
    @GetMapping("/getClassComments")
    public LmsResponse<ClassCommentRes> getClassComments(ClassGetRequest classCommentRequest){
        ClassCommentRes classComments = boardService.getClassComments(classCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, classComments, "서비스 성공", "에러 없음", LocalDateTime.now());
    }


    //강의 게시판 댓글 작성
    @PostMapping("/writeClassComments")
    public LmsResponse<ClassCommentRes> writeClassComments(ClassCommentRequest classCommentRequest){
        ClassCommentRes classCommentRes = boardService.writeClassComments(classCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, classCommentRes, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 댓글 수정
    @PostMapping("/updateClassComments")
    public LmsResponse<String> updateClassComments(ClassUpdateCommentRequest classUpdateCommentRequest){
        String s = boardService.updateClassComments(classUpdateCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }
    
    

    //강의 게시판 댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @PostMapping("/deleteClassComments")
    public LmsResponse<String> deleteClassComments(ClassDeleteCommentRequest classDeleteCommentRequest){
        String s = boardService.deleteClassComments(classDeleteCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 대댓글 보기
    @GetMapping("/getClassMiniComments")
    public LmsResponse<ClassMiniCommentRes> getClassMiniComments(ClassGetMiniCommentRequest classMiniCommentRequest){
        ClassMiniCommentRes classMiniComments = boardService.getClassMiniComments(classMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, classMiniComments, "서비스 성공", "에러 없음", LocalDateTime.now());

    }
    //강의 게시판 대댓글 작성
    @PostMapping("/writeClassMiniComments")
    public LmsResponse<ClassMiniCommentRes> writeClassMiniComments(ClassMiniCommentRequest classMiniCommentRequest){
        ClassMiniCommentRes classMiniCommentRes = boardService.writeClassMiniComments(classMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, classMiniCommentRes, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 대댓글 수정
    @PostMapping
    public LmsResponse<String> updateClassMiniComments(ClassUpdateMiniCommentRequest classUpdateMiniCommentRequest){
        String s = boardService.updateClassMiniComments(classUpdateMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 대댓글 삭제
    @PostMapping
    public LmsResponse<String> deleteClassMiniComments(ClassDeleteMiniCommentRequest classDeleteMiniCommentRequest){
        String s = boardService.deleteClassMiniComments(classDeleteMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }


}
