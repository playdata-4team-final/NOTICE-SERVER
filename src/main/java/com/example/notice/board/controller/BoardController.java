package com.example.notice.board.controller;


import com.example.notice.board.domain.request.*;
import com.example.notice.board.domain.response.*;
import com.example.notice.board.service.BoardService;
import com.example.notice.global.domain.response.LmsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //공지사항 전체 보기
    @GetMapping("/getAllNotices")
    public LmsResponse<List<NoticeRes>> getAllNotices(){
        List<NoticeRes> allNotices = boardService.getAllNotices();
        return new LmsResponse<>(HttpStatus.OK, allNotices, "서비스 성공", "", LocalDateTime.now());
    }

    //공지사항 보기
    @GetMapping("/getNotice/{id}")
    public LmsResponse<NoticeRes> getNotice(@PathVariable("id")  Long id){
        NoticeRes noticeRes = boardService.getNotice(id);
        return new LmsResponse<>(HttpStatus.OK, noticeRes, "서비스 성공", "", LocalDateTime.now());

    }

    //공지사항 생성
    @PostMapping("/createNotice")
    public LmsResponse<NoticeRes> createNotice(@RequestBody NoticeCreateRequest noticeCreateRequest){
        NoticeRes noticeRes = boardService.writeNotice(noticeCreateRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeRes, "서비스 성공", "", LocalDateTime.now());
    }

    //공지사항 삭제
    @PostMapping("/deleteNotice")
    public LmsResponse<String> deleteNotice(@RequestBody NoticeDeleteRequest noticedeleteRequest){
        String s = boardService.deleteNotice(noticedeleteRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }


    //공지사항 파일 업로드
    @PostMapping("/uploadNoticeFile")
    public LmsResponse<String> uploadNoticeFile(@RequestBody NoticeFileRequest noticeFileRequest) {
        String s = boardService.uploadNoticeFile(noticeFileRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }


    //공지사항 파일 다운로드
    @GetMapping("/downloadNoticeFile")
    public LmsResponse<FileSystemResource> downloadNoticeFile(@RequestBody NoticeFileRequest noticeFileRequest) {
        FileSystemResource noticeFile = boardService.downloadNoticeFile(noticeFileRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeFile, "서비스 성공", "", LocalDateTime.now());
    }

    //공지사항 파일 목록 가져오기
    @GetMapping("/getNoticeFile")
    public LmsResponse<List<String>> getNoticeFile(@RequestBody NoticeFileRequest noticeFileRequest) {
        List<String> noticeFile = boardService.getNoticeFile(noticeFileRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeFile, "서비스 성공", "", LocalDateTime.now());
    }



    //공지사항 파일 삭제
    @PostMapping("/deleteNoticeFile")
    public  LmsResponse<Boolean> deleteNoticeFile(@RequestBody NoticeFileRequest noticeFileRequest){
        boolean b = boardService.deleteNoticeFile(noticeFileRequest);
        return new LmsResponse<>(HttpStatus.OK, b, "서비스 성공", "", LocalDateTime.now());
    }



    //공지사항 댓글 보기
    @GetMapping("/getNoticeComments/{id}")
    public LmsResponse<List<NoticeCommentRes>> getNoticeComments(@PathVariable("id") Long id){
        List<NoticeCommentRes> noticeComments = boardService.getNoticeComments(id);
        return new LmsResponse<>(HttpStatus.OK, noticeComments, "서비스 성공", "", LocalDateTime.now());

    }

    //공지사항 댓글 작성
    @PostMapping("/writeNoticeComments")
    public LmsResponse<NoticeCommentRes> writeNoticeComments(@RequestBody NoticeCommentRequest noticeCommentRequest){
        NoticeCommentRes noticeCommentRes = boardService.writeNoticeComments(noticeCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeCommentRes, "서비스 성공", "", LocalDateTime.now());
    }


    //공지사항 댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @PostMapping("/deleteNoticeComments")
    public LmsResponse<String> deleteNoticeComments(@RequestBody NoticeDeleteCommentRequest noticeDeleteCommentRequest){
        String s = boardService.deleteNoticeComments(noticeDeleteCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //공지사항 댓글 수정
    @PostMapping("/uploadNoticeComments")
    public LmsResponse<String> uploadNoticeComments(@RequestBody NoticeUpdateCommentRequest noticeUpdateCommentRequest){
        String s = boardService.uploadNoticeComments(noticeUpdateCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }
    
    //공지사항 대댓글 보기
    @GetMapping("/getNoticeMiniComments")
    public LmsResponse<NoticeMiniCommentRes> getNoticeMiniComments(@RequestBody NoticeGetMiniCommentRequest noticeGetMiniCommentRequest){
        NoticeMiniCommentRes noticeMiniComments = boardService.getNoticeMiniComments(noticeGetMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeMiniComments, "서비스 성공", "", LocalDateTime.now());

    }
    
    //공지 사항 대댓글 작성
    @PostMapping("/writeNoticeMiniComments")
    public LmsResponse<NoticeMiniCommentRes> writeNoticeMiniComments(@RequestBody NoticeMiniCommentRequest noticeMiniCommentRequest){
        NoticeMiniCommentRes noticeMiniCommentRes = boardService.writeNoticeMiniComments(noticeMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, noticeMiniCommentRes, "서비스 성공", "", LocalDateTime.now());
    }

    //공지사항 대댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @PostMapping("/deleteNoticeMiniComments")
    public LmsResponse<String> deleteNoticeMiniComments(@RequestBody NoticeDeleteMiniCommentRequest noticeDeleteMiniCommentRequest){
        String s = boardService.deleteNoticeMiniComments(noticeDeleteMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //공지사항 대댓글 수정
    @PostMapping("/uploadNoticeMiniComments")
    public LmsResponse<String> uploadNoticeMiniComments(@RequestBody NoticeUpdateMiniCommentRequest noticeUpdateMiniCommentRequest){
        String s = boardService.uploadNoticeMiniComments(noticeUpdateMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 게시판 보기
    @PostMapping("/getClass")
    public LmsResponse<ClassBoardRes> getClass(@RequestBody ClassGetRequest classgetRequest){
        ClassBoardRes aClass = boardService.getClass(classgetRequest);
        return new LmsResponse<>(HttpStatus.OK, aClass, "서비스 성공", "", LocalDateTime.now());

    }

    //강의게시판 생성
    @PostMapping("/createClass")
    public LmsResponse<ClassBoardRes> createClass(@RequestBody ClassCreateRequest classCreateRequest){
        ClassBoardRes classBoardRes = boardService.writeClass(classCreateRequest);
        return new LmsResponse<>(HttpStatus.OK, classBoardRes, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 게시판 삭제
    @PostMapping("/deleteClass")
    public LmsResponse<String> deleteClass(@RequestBody ClassDeleteRequest classdeleteRequest){
        String s = boardService.deleteClass(classdeleteRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 파일 보기
    @GetMapping("/getClassFile")
    public LmsResponse<List<String>> getClassFile(@RequestBody ClassFileRequest request){
        List<String> classFile = boardService.getClassFile(request);
        return new LmsResponse<>(HttpStatus.OK, classFile, "서비스 성공", "", LocalDateTime.now());

    }
    //강의 파일 업로드
    @PostMapping("/uploadClassFile")
    public LmsResponse<String> uploadClassFile(@RequestBody ClassFileRequest classFileRequest){
        String s = boardService.uploadClassFile(classFileRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 파일 다운로드
    @PostMapping("/downloadNoticeFile")
    public LmsResponse<FileSystemResource> downloadClassFile(@RequestBody ClassFileRequest request) {
        FileSystemResource noticeFile = boardService.downloadClassFile(request);
        return new LmsResponse<>(HttpStatus.OK, noticeFile, "서비스 성공", "", LocalDateTime.now());
    }


    //강의 파일 삭제
    @PostMapping("/deleteClassFile")
    public LmsResponse<Boolean> deleteClassFile(@RequestBody ClassFileRequest classFileRequest){
        boolean b = boardService.deleteClassFile(classFileRequest);
        return new LmsResponse<>(HttpStatus.OK, b, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 게시판 댓글 보기
    @GetMapping("/getClassComments")
    public LmsResponse<ClassCommentRes> getClassComments(@RequestBody ClassGetRequest classCommentRequest){
        ClassCommentRes classComments = boardService.getClassComments(classCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, classComments, "서비스 성공", "", LocalDateTime.now());
    }


    //강의 게시판 댓글 작성
    @PostMapping("/writeClassComments")
    public LmsResponse<ClassCommentRes> writeClassComments(@RequestBody ClassCommentRequest classCommentRequest){
        ClassCommentRes classCommentRes = boardService.writeClassComments(classCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, classCommentRes, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 게시판 댓글 수정
    @PostMapping("/updateClassComments")
    public LmsResponse<String> updateClassComments(@RequestBody ClassUpdateCommentRequest classUpdateCommentRequest){
        String s = boardService.updateClassComments(classUpdateCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }
    
    

    //강의 게시판 댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @PostMapping("/deleteClassComments")
    public LmsResponse<String> deleteClassComments(@RequestBody ClassDeleteCommentRequest classDeleteCommentRequest){
        String s = boardService.deleteClassComments(classDeleteCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 대댓글 보기
    @GetMapping("/getClassMiniComments")
    public LmsResponse<ClassMiniCommentRes> getClassMiniComments(@RequestBody ClassGetMiniCommentRequest classMiniCommentRequest){
        ClassMiniCommentRes classMiniComments = boardService.getClassMiniComments(classMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, classMiniComments, "서비스 성공", "에러 없음", LocalDateTime.now());

    }
    //강의 게시판 대댓글 작성
    @PostMapping("/writeClassMiniComments")
    public LmsResponse<ClassMiniCommentRes> writeClassMiniComments(@RequestBody ClassMiniCommentRequest classMiniCommentRequest){
        ClassMiniCommentRes classMiniCommentRes = boardService.writeClassMiniComments(classMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, classMiniCommentRes, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 대댓글 수정
    @PostMapping("/updateClassMiniComment")
    public LmsResponse<String> updateClassMiniComments(@RequestBody ClassUpdateMiniCommentRequest classUpdateMiniCommentRequest){
        String s = boardService.updateClassMiniComments(classUpdateMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }

    //강의 게시판 대댓글 삭제
    @PostMapping("/deleteClassMiniComments")
    public LmsResponse<String> deleteClassMiniComments(@RequestBody ClassDeleteMiniCommentRequest classDeleteMiniCommentRequest){
        String s = boardService.deleteClassMiniComments(classDeleteMiniCommentRequest);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "에러 없음", LocalDateTime.now());
    }


}
