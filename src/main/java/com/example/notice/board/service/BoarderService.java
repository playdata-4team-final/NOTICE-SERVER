package com.example.notice.board.service;

import com.example.notice.admin.dto.CLassDto;
import com.example.notice.admin.dto.NoticeDto;
import com.example.notice.board.domain.entity.*;
import com.example.notice.board.domain.request.*;
import com.example.notice.board.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoarderService {

    private final NoticeRepository adminBoardRepository;
    private final ClassBoardRepository classBoardRepository;
    private final NoticeMiniCommentRepository noticeMiniCommentRepository;
    private final ClassMiniCommentRepository classMiniCommentRepository;
    private final NoticeCommentRepository noticeCommentRepository;
    private final ClassCommentRepository classCommentRepository;

    //공지사항 생성
    private Notice writeNotice(NoticeCreateRequest noticeCreateRequest){
        Notice save = adminBoardRepository.save(noticeCreateRequest.toEntity());
        return save;
    }

    //공지사항 삭제
    @Transactional
    public String deleteNotice(NoticeDeleteRequest noticedeleteRequest){
        NoticeDto noticeDto = adminBoardRepository.findByNoticeId(noticedeleteRequest.getId()).get();
        adminBoardRepository.deleteByNoticeId(noticeDto.getNoticeId());
        return "Success Delete!";
    }

    //공지사항 파일 업로드
    private String uploadNoticeFile(NoticeFileRequest noticeFileRequest){
        changeNoticeFileUrl(noticeFileRequest);
        return "Success Upload!";
    }

    //공지사항 파일 삭제
    private String deleteNoticeFile(NoticeFileRequest noticeFileRequest){
        deleteNoticeFileUrl(noticeFileRequest);
        return "Success Delete!";
    }

    //공지사항 댓글 작성
    private NoticeComment writeNoticeComments(NoticeCommentRequest noticeCommentRequest){
        NoticeComment save = noticeCommentRepository.save(noticeCommentRequest.toEntity());
        return save;
    }
    
    //공지사항 댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    private String deleteNoticeComments(NoticeDeleteCommentRequest noticeDeleteCommentRequest){
        changeNullNoticeComment(noticeDeleteCommentRequest);
        return "Success Delete!";
    }

    //공지사항 댓글 수정
    private String uploadNoticeComments(NoticeUpdateCommentRequest noticeUpdateCommentRequest){
        changeNoticeComment(noticeUpdateCommentRequest);
        return "Success Update!";
    }
    
    //공지 사항 대댓글 작성
    private NoticeMiniComment writeNoticeMiniComments(NoticeMiniCommentRequest noticeMiniCommentRequest){
        NoticeMiniComment save = noticeMiniCommentRepository.save(noticeMiniCommentRequest.toEntity());
        return save;
    }

    //공지사항 대댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    private String deleteNoticeMiniComments(NoticeDeleteMiniCommentRequest noticeDeleteMiniCommentRequest){
        changeNullNoticeMiniComment(noticeDeleteMiniCommentRequest);
        return "Success Delete!";
    }

    //공지사항 대댓글 수정
    private String uploadNoticeMiniComments(NoticeUpdateMiniCommentRequest noticeUpdateMiniCommentRequest){
        changeNoticeMiniComment(noticeUpdateMiniCommentRequest);
        return "Success Update!";
    }

    //강의게시판 생성
    private ClassBoard writeNotice(ClassCreateRequest classCreateRequest){
        ClassBoard save = classBoardRepository.save(classCreateRequest.toEntity());
        return save;
    }

    //강의 게시판 삭제
    @Transactional
    public String deleteClass(ClassDeleteRequest classdeleteRequest){
        CLassDto cLassDto = classBoardRepository.findByClassId(classdeleteRequest.getId()).get();
        adminBoardRepository.deleteByNoticeId(cLassDto.getClassId());
        return "Success Delete!";
    }

    //강의 파일 업로드
    private String uploadClassFile(ClassFileRequest classFileRequest){
        changeClassFileUrl(classFileRequest);
        return "Success Upload!";
    }

    //강의 파일 삭제
    private String deleteClassFile(ClassFileRequest classFileRequest){
        deleteClassFileUrl(classFileRequest);
        return "Success Delete!";
    }

    //강의 게시판 댓글 작성
    private ClassComment writeClassComments(ClassCommentRequest classCommentRequest){
        ClassComment save = classCommentRepository.save(classCommentRequest.toEntity());
        return save;
    }

    //강의 게시판 댓글 수정
    private String updateClassComments(ClassUpdateCommentRequest classUpdateCommentRequest){
        changeClassComment(classUpdateCommentRequest);
        return "Success Update!";
    }

    //강의 게시판 댓글 삭제 : 실제 삭제하는게 아니라 comment 값을 "삭제된 댓글입니다."로 만듦.
    private String deleteClassComments(ClassDeleteCommentRequest classDeleteCommentRequest){
        changeNullClassComment(classDeleteCommentRequest);
        return "Success Delete!";
    }


    //강의 게시판 대댓글 작성
    private ClassMiniComment writeClassMiniComments(ClassMiniCommentRequest classMiniCommentRequest){
        ClassMiniComment save = classMiniCommentRepository.save(classMiniCommentRequest.toEntity());
        return save;
    }

    //강의 게시판 대댓글 수정
    private String updateClassMiniComments(ClassUpdateMiniCommentRequest classUpdateMiniCommentRequest){
        changeClassMiniComment(classUpdateMiniCommentRequest);
        return "Success Update!";
    }

    //강의 게시판 대댓글 삭제
    private String deleteClassMiniComments(ClassDeleteMiniCommentRequest classDeleteMiniCommentRequest){
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
