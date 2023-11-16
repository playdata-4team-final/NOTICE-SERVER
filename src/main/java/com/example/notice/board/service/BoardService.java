package com.example.notice.board.service;

import com.example.notice.admin.dto.*;
import com.example.notice.board.domain.entity.*;
import com.example.notice.board.domain.request.*;
import com.example.notice.board.domain.response.*;
import com.example.notice.board.repository.*;
import com.example.notice.global.exception.ClientException;
import com.example.notice.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private final String fileDirectory = "C:/LmsFile";

    @Transactional
    public List<NoticeRes> getAllNotices(){
        List<Notice> all = adminBoardRepository.findAll();

        List<NoticeRes> noticeResList = new ArrayList<>();

        for(Notice notice : all) {
            NoticeRes noticeRes = new NoticeRes(notice);
            noticeResList.add(noticeRes);
        }
        return noticeResList;
    }


    //공지사항 보기
    @Transactional
    public NoticeRes getNotice(Long id){
        Notice notice = adminBoardRepository.findById(id).get();
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
    public String deleteNotice(NoticeDeleteRequest noticeDeleteRequest){

        adminBoardRepository.deleteByIdsQuery(noticeDeleteRequest.getNoticeIds());

        return "Success Delete!";
    }

    //공지사항 파일 다운
    @Transactional
    public FileSystemResource downloadNoticeFile(NoticeFileRequest request) {
        try {
            String fileName = request.getFileName();
            Path filePath = Paths.get(fileDirectory, fileName);
            File file = filePath.toFile();

            if (file.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

                FileSystemResource response = new FileSystemResource(file);

                return response;
            } else {
                throw new NotFoundException("파일이 없습니다.");
            }
        } catch (Exception e) {
            throw new ClientException("클라이언트 오류");
        }
    }

    //공지사항 파일 가져오기
    public List<String> getNoticeFile(NoticeFileRequest request){

        // 공지사항 파일 가져오기 로직을 활용하여 파일 목록 가져오기
        List<String> allFiles = getNoticeFiles();

        // noticeId가  파일 필터링
        List<String> filteredFiles = allFiles.stream()
                .filter(fileName -> fileName.contains("_" + request.getNoticeId() + "_"))
                .collect(Collectors.toList());

        return filteredFiles;
    }


    //공지사항 파일 업로드
    @Transactional
    public String uploadNoticeFile(NoticeFileRequest request) {
        try {
            // 저장할 디렉토리 생성
            Files.createDirectories(Path.of(fileDirectory));

            // 파일 경로에 noticeId를 포함시킴
            String fileName = generateNoticeFileName(request);
            Path filePath = Path.of(fileDirectory, fileName);

            // 파일 저장
            Files.copy(request.getFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "File uploaded Success!";
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    // 공지사항 파일 삭제
    @Transactional
    public boolean deleteNoticeFile(NoticeFileRequest request) {
        try {
            // 파일명 생성
            String fileName = generateNoticeFileName(request);

            // 파일 경로 생성
            Path filePath = Path.of(fileDirectory, fileName);

            // 파일 삭제
            boolean isDeleted = Files.deleteIfExists(filePath);

            if (isDeleted) {
                return true;
            } else {
                throw new ClientException("잘못된 파일명입니다:" + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting file: " + e.getMessage(), e);
        }
    }

    //공지사항 댓글 보기
    @Transactional
    public List<NoticeCommentRes> getNoticeComments(Long id){
        List<NoticeCommentDto> all = noticeCommentRepository.findByNoticeId(id);
        List<NoticeCommentRes> noticeCommentResList = new ArrayList<>();

        for(NoticeCommentDto noticeComment : all) {
            NoticeCommentRes noticeCommentRes = new NoticeCommentRes(noticeComment.toEntity());
            noticeCommentResList.add(noticeCommentRes);
        }
        return noticeCommentResList;
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
        NoticeMiniCommentDto noticeMiniCommentDto = noticeMiniCommentRepository.findByNoticeIdComAndNoticeCommentId(noticeGetMiniCommentRequest.getNoticeId(), noticeGetMiniCommentRequest.getCommentId()).get();
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
    public String deleteClass(ClassDeleteRequest classdeleteRequest){;
        classBoardRepository.deleteById(classdeleteRequest.getId());
        return "Success Delete!";
    }


    //강의 파일 다운
    @Transactional
    public FileSystemResource downloadClassFile(ClassFileRequest classFileRequest) {
        try {
            String fileName = classFileRequest.getFileName();
            Path filePath = Paths.get(fileDirectory, fileName);
            File file = filePath.toFile();

            if (file.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

                FileSystemResource response = new FileSystemResource(file);

                return response;
            } else {
                throw new NotFoundException("파일이 없습니다.");
            }
        } catch (Exception e) {
            throw new ClientException("클라이언트 오류");
        }
    }

    //강의 파일 가져오기
    public List<String> getClassFile(ClassFileRequest request){

        // 공지사항 파일 가져오기 로직을 활용하여 파일 목록 가져오기
        List<String> allFiles = getNoticeFiles();

        // noticeId가  파일 필터링
        List<String> filteredFiles = allFiles.stream()
                .filter(fileName -> fileName.contains("_" + request.getClassId() + "_"))
                .collect(Collectors.toList());

        return filteredFiles;
    }


    //강의 파일 업로드
    public String uploadClassFile(ClassFileRequest request) {
        try {
            // 저장할 디렉토리 생성
            Files.createDirectories(Path.of(fileDirectory));

            // 파일 경로에 noticeId를 포함시킴
            String fileName = generateClassFileName(request);
            Path filePath = Path.of(fileDirectory, fileName);

            // 파일 저장
            Files.copy(request.getFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "File uploaded Success!";
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    // 강의 파일 삭제
    public boolean deleteClassFile(ClassFileRequest request) {
        try {
            // 파일명 생성
            String fileName = generateClassFileName(request);

            // 파일 경로 생성
            Path filePath = Path.of(fileDirectory, fileName);

            // 파일 삭제
            boolean isDeleted = Files.deleteIfExists(filePath);

            if (isDeleted) {
                return true;
            } else {
                throw new ClientException("잘못된 파일명입니다:" + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting file: " + e.getMessage(), e);
        }
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
        ClassMiniCommentDto classMiniCommentDto = classMiniCommentRepository.findByClassIdComAndClassCommentId(classMiniCommentRequest.getClassBoarId(), classMiniCommentRequest.getClassCommentId()).get();
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
    public int changeNoticeComment(NoticeUpdateCommentRequest noticeUpdateCommentRequest){
        NoticeComment noticeComment = noticeCommentRepository.findById(noticeUpdateCommentRequest.getNoticeId()).get();
        noticeComment.changeNoticeComment(noticeUpdateCommentRequest.getComment());
        return 1;
    }


    @Transactional
    public int changeNullNoticeComment(NoticeDeleteCommentRequest noticeDeleteCommentRequest){
        NoticeComment noticeComment = noticeCommentRepository.findByUserId(noticeDeleteCommentRequest.getId(),noticeDeleteCommentRequest.getUserId()).get();
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


    private String generateClassFileName(ClassFileRequest request) {
        // 파일명 규칙: classId_originalFilename
        return String.format("%02d_%s",
                request.getClassId(),
                request.getFileName());
    }

    private String generateNoticeFileName(NoticeFileRequest request) {
        // 파일명 규칙: noticeId_originalFilename
        return String.format("%02d_%s",
                request.getNoticeId(),
                request.getFileName());
    }

    private List<String> getNoticeFiles(){
        File directory = new File(fileDirectory);
        File[] files = directory.listFiles();

        if (files != null) {
            return Arrays.stream(files)
                    .map(File::getName)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
