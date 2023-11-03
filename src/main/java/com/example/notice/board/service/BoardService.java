package com.example.notice.board.service;

import com.example.notice.admin.dto.*;
import com.example.notice.board.domain.entity.*;
import com.example.notice.board.domain.request.*;
import com.example.notice.board.domain.response.*;
import com.example.notice.board.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final NoticeRepository adminBoardRepository;
    private final ClassBoardRepository classBoardRepository;
    private final NoticeMiniCommentRepository noticeMiniCommentRepository;
    private final ClassMiniCommentRepository classMiniCommentRepository;
    private final NoticeCommentRepository noticeCommentRepository;
    private final ClassCommentRepository classCommentRepository;

    //공지사항 보기
    @Transactional
    public NoticeRes getNotice(NoticeGetRequest noticeGetRequest){
        Notice notice = adminBoardRepository.findById(noticeGetRequest.getId()).get();
        NoticeRes noticeRes = new NoticeRes(notice);
        return  noticeRes;
    }

    //공지사항 생성
    @Transactional
    public NoticeRes writeNotice(NoticeCreateRequest noticeCreateRequest){
        Notice save = adminBoardRepository.save(noticeCreateRequest.toEntity());
        NoticeRes noticeRes = new NoticeRes(save);
        return noticeRes;
    }

    //공지사항 삭제
    @Transactional
    public String deleteNotice(NoticeDeleteRequest noticedeleteRequest){
        Notice notice = adminBoardRepository.findById(noticedeleteRequest.getId()).get();
        adminBoardRepository.deleteByNoticeId(notice.getId());
        return "Success Delete!";
    }

    //공지사항 파일 보기
    @Transactional
    public NoticeRes getNoticeFile(NoticeGetRequest noticeFileRequest){
        NoticeDto noticeDto = adminBoardRepository.findFileUrlByNoticeId(noticeFileRequest.getId()).get();
        NoticeRes noticeRes = new NoticeRes(noticeDto.toEntity());
        return noticeRes;
    }


    //공지사항 파일 업로드
    @Transactional
    public String uploadNoticeFile(NoticeFileRequest noticeFileRequest){
        changeNoticeFileUrl(noticeFileRequest);
        return "Success Upload!";
    }

    //공지사항 파일 삭제
    @Transactional
    public String deleteNoticeFile(NoticeFileRequest noticeFileRequest){
        deleteNoticeFileUrl(noticeFileRequest);
        return "Success Delete!";
    }

    //공지사항 댓글 보기
    @Transactional
    public NoticeCommentRes getNoticeComments(NoticeGetRequest noticeCommentRequest){
        NoticeCommentDto noticeCommentDto = noticeCommentRepository.findByNoticeId(noticeCommentRequest.getId()).get();
        NoticeCommentRes noticeCommentRes = new NoticeCommentRes(noticeCommentDto.toEntity());
        return noticeCommentRes;
    }

    //공지사항 댓글 작성
    @Transactional
    public NoticeCommentRes writeNoticeComments(NoticeCommentRequest noticeCommentRequest){
        NoticeComment save = noticeCommentRepository.save(noticeCommentRequest.toEntity());
        NoticeCommentRes noticeCommentRes = new NoticeCommentRes(save);
        return noticeCommentRes;
    }

    //공지사항 댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @Transactional
    public String deleteNoticeComments(NoticeDeleteCommentRequest noticeDeleteCommentRequest){
        changeNullNoticeComment(noticeDeleteCommentRequest);
        return "Success Delete!";
    }

    //공지사항 댓글 수정
    @Transactional
    public String uploadNoticeComments(NoticeUpdateCommentRequest noticeUpdateCommentRequest){
        changeNoticeComment(noticeUpdateCommentRequest);
        return "Success Update!";
    }

    //공지 사항 대댓글 보기
    @Transactional
    public NoticeMiniCommentRes getNoticeMiniComments(NoticeGetMiniCommentRequest noticeGetMiniCommentRequest){
        NoticeMiniCommentDto noticeMiniCommentDto = noticeMiniCommentRepository.findByNoticeIdandComAndNoticeCommentId(noticeGetMiniCommentRequest.getNoticeId(), noticeGetMiniCommentRequest.getCommentId()).get();
        NoticeMiniCommentRes noticeMiniCommentRes = new NoticeMiniCommentRes(noticeMiniCommentDto.toEntity());
        return noticeMiniCommentRes;
    }

    //공지 사항 대댓글 작성
    @Transactional
    public NoticeMiniCommentRes writeNoticeMiniComments(NoticeMiniCommentRequest noticeMiniCommentRequest){
        NoticeMiniComment save = noticeMiniCommentRepository.save(noticeMiniCommentRequest.toEntity());
        NoticeMiniCommentRes noticeMiniCommentRes = new NoticeMiniCommentRes(save);
        return noticeMiniCommentRes;
    }

    //공지사항 대댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @Transactional
    public String deleteNoticeMiniComments(NoticeDeleteMiniCommentRequest noticeDeleteMiniCommentRequest){
        changeNullNoticeMiniComment(noticeDeleteMiniCommentRequest);
        return "Success Delete!";
    }

    //공지사항 대댓글 수정
    @Transactional
    public String uploadNoticeMiniComments(NoticeUpdateMiniCommentRequest noticeUpdateMiniCommentRequest){
        changeNoticeMiniComment(noticeUpdateMiniCommentRequest);
        return "Success Update!";
    }

    //강의 게시판 보기
    @Transactional
    public ClassBoardRes getClass(ClassGetRequest classgetRequest){
        ClassBoard classBoard = classBoardRepository.findById(classgetRequest.getId()).get();
        ClassBoardRes classBoardRes = new ClassBoardRes(classBoard);
        return classBoardRes;
    }

    //강의게시판 생성
    @Transactional
    public ClassBoardRes writeClass(ClassCreateRequest classCreateRequest){
        ClassBoard save = classBoardRepository.save(classCreateRequest.toEntity());
        ClassBoardRes classBoardRes = new ClassBoardRes(save);
        return classBoardRes;
    }

    //강의 게시판 삭제
    @Transactional
    public String deleteClass(ClassDeleteRequest classdeleteRequest){
        CLassDto cLassDto = classBoardRepository.findByClassId(classdeleteRequest.getId()).get();
        adminBoardRepository.deleteByNoticeId(cLassDto.getClassId());
        return "Success Delete!";
    }

    //강의 파일 보기
    @Transactional
    public ClassBoardRes getClassFile(ClassGetRequest classGetFileRequest){
        ClassBoard classBoard = classBoardRepository.findById(classGetFileRequest.getId()).get();
        ClassBoardRes classBoardRes = new ClassBoardRes(classBoard);
        return classBoardRes;
    }

    //강의 파일 업로드
    @Transactional
    public String uploadClassFile(ClassFileRequest classFileRequest){
        changeClassFileUrl(classFileRequest);
        return "Success Upload!";
    }

    //강의 파일 삭제
    @Transactional
    public String deleteClassFile(ClassFileRequest classFileRequest){
        deleteClassFileUrl(classFileRequest);
        return "Success Delete!";
    }

    //강의 게시판 댓글 작성
    @Transactional
    public ClassCommentRes getClassComments(ClassGetRequest classCommentRequest){
        ClassComment classComment = classCommentRepository.findById(classCommentRequest.getId()).get();
        ClassCommentRes classCommentRes = new ClassCommentRes(classComment);
        return classCommentRes;
    }


    //강의 게시판 댓글 작성
    @Transactional
    public ClassCommentRes writeClassComments(ClassCommentRequest classCommentRequest){
        ClassComment save = classCommentRepository.save(classCommentRequest.toEntity());
        ClassCommentRes classCommentRes = new ClassCommentRes(save);
        return classCommentRes;
    }

    //강의 게시판 댓글 수정
    @Transactional
    public String updateClassComments(ClassUpdateCommentRequest classUpdateCommentRequest){
        changeClassComment(classUpdateCommentRequest);
        return "Success Update!";
    }

    //강의 게시판 댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    @Transactional
    public String deleteClassComments(ClassDeleteCommentRequest classDeleteCommentRequest){
        changeNullClassComment(classDeleteCommentRequest);
        return "Success Delete!";
    }

    //강의 게시판 대댓글 보기
    @Transactional
    public ClassMiniCommentRes getClassMiniComments(ClassGetMiniCommentRequest classMiniCommentRequest){
        ClassMiniCommentDto classMiniCommentDto = classMiniCommentRepository.findByClassIdandComAndClassCommentId(classMiniCommentRequest.getClassBoarId(), classMiniCommentRequest.getClassCommentId()).get();
        ClassMiniCommentRes classMiniCommentRes = new ClassMiniCommentRes(classMiniCommentDto.toEntity());
        return classMiniCommentRes;
    }


    //강의 게시판 대댓글 작성
    @Transactional
    public ClassMiniCommentRes writeClassMiniComments(ClassMiniCommentRequest classMiniCommentRequest){
        ClassMiniComment save = classMiniCommentRepository.save(classMiniCommentRequest.toEntity());
        ClassMiniCommentRes classMiniCommentRes = new ClassMiniCommentRes(save);
        return classMiniCommentRes;
    }

    //강의 게시판 대댓글 수정
    @Transactional
    public String updateClassMiniComments(ClassUpdateMiniCommentRequest classUpdateMiniCommentRequest){
        changeClassMiniComment(classUpdateMiniCommentRequest);
        return "Success Update!";
    }

    //강의 게시판 대댓글 삭제
    @Transactional
    public String deleteClassMiniComments(ClassDeleteMiniCommentRequest classDeleteMiniCommentRequest){
        changeNullClassMiniComment(classDeleteMiniCommentRequest);
        return "Success Delete!";
    }




    @Transactional
    public int changeNoticeFileUrl(NoticeFileRequest noticeFileRequest){
        try {
            NoticeDto noticeDto = adminBoardRepository.findByAdminIdandNoticeId(noticeFileRequest.getAdminId(), noticeFileRequest.getNoticeId()).get();

            noticeDto.changeFileUrl(noticeFileRequest.getFileUrl());
            noticeDto.changeUpdateTime();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Transactional
    public int deleteNoticeFileUrl(NoticeFileRequest noticeFileRequest){
        try {
            NoticeDto noticeDto = adminBoardRepository.findByAdminIdandNoticeId(noticeFileRequest.getAdminId(), noticeFileRequest.getNoticeId()).get();
            noticeDto.deleteFileUrl();
            noticeDto.changeUpdateTime();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Transactional
    public int changeNoticeComment(NoticeUpdateCommentRequest noticeUpdateCommentRequest){
        NoticeComment noticeComment = noticeCommentRepository.findById(noticeUpdateCommentRequest.getNoticeId()).get();
        noticeComment.changeNoticeComment(noticeUpdateCommentRequest.getComment());
        return 1;
    }


    @Transactional
    public int changeNullNoticeComment(NoticeDeleteCommentRequest noticeDeleteCommentRequest){
        NoticeComment noticeComment = noticeCommentRepository.findById(noticeDeleteCommentRequest.getId()).get();
        noticeComment.deleteNoticeComment();
        return 1;
    }

    @Transactional
    public int changeNoticeMiniComment(NoticeUpdateMiniCommentRequest noticeUpdateMiniCommentRequest){
        NoticeMiniComment noticeMiniComment = noticeMiniCommentRepository.findById(noticeUpdateMiniCommentRequest.getMiniCommentId()).get();
        noticeMiniComment.changeNoticeMiniComment(noticeUpdateMiniCommentRequest.getComment());
        return 1;
    }


    @Transactional
    public int changeNullNoticeMiniComment(NoticeDeleteMiniCommentRequest noticeDeleteMiniCommentRequest){
        NoticeMiniComment noticeMiniComment = noticeMiniCommentRepository.findById(noticeDeleteMiniCommentRequest.getId()).get();
        noticeMiniComment.deleteNoticeMiniComment();
        return 1;
    }


    @Transactional
    public int deleteClassFileUrl(ClassFileRequest classFileRequest){
        try {
            CLassDto cLassDto = classBoardRepository.findByUserIdandClassId(classFileRequest.getAdminId(), classFileRequest.getNoticeId()).get();
            cLassDto.deleteFileUrl();
            cLassDto.changeUpdateTime();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Transactional
    public int changeClassFileUrl(ClassFileRequest classFileRequest){
        try {
            CLassDto cLassDto = classBoardRepository.findByUserIdandClassId(classFileRequest.getAdminId(), classFileRequest.getNoticeId()).get();
            cLassDto.changeFileUrl(classFileRequest.getFileUrl());
            cLassDto.changeUpdateTime();
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Transactional
    public int changeClassComment(ClassUpdateCommentRequest classUpdateCommentRequest ){
        ClassComment classComment = classCommentRepository.findById(classUpdateCommentRequest.getClassBoarId()).get();
        classComment.changeClassComment(classUpdateCommentRequest.getComment());
        return 1;
    }


    @Transactional
    public int changeNullClassComment(ClassDeleteCommentRequest classDeleteCommentRequest){
        ClassComment classComment = classCommentRepository.findById(classDeleteCommentRequest.getClassBoarId()).get();
        classComment.deleteClassComment();
        return 1;
    }

    @Transactional
    public int changeClassMiniComment(ClassUpdateMiniCommentRequest classUpdateMiniCommentRequest){
        ClassMiniComment classMiniComment = classMiniCommentRepository.findById(classUpdateMiniCommentRequest.getCommentId()).get();
        classMiniComment.changeClassMiniComment(classUpdateMiniCommentRequest.getComment());
        return 1;
    }


    @Transactional
    public int changeNullClassMiniComment(ClassDeleteMiniCommentRequest classDeleteMiniCommentRequest){
        ClassMiniComment classMiniComment = classMiniCommentRepository.findById(classDeleteMiniCommentRequest.getClassCommentId()).get();
        classMiniComment.deleteClassMiniComment();
        return 1;
    }
}
