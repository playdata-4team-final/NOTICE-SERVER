package com.example.notice.board.service;

import com.example.notice.admin.dto.AdminDto;
import com.example.notice.board.domain.entity.AdminBoard;
import com.example.notice.board.domain.request.AdminFileRequest;
import com.example.notice.board.repository.AdminBoardRepository;
import com.example.notice.board.repository.ClassBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoarderService {

    private final AdminBoardRepository adminBoardRepository;
    private final ClassBoardRepository classBoardRepository;

    //댓글 작성
    private void writeMiniComments(){


    }

    //파일 업로드
    private String uploadFile(AdminFileRequest adminFileRequest){
        changeFileUrl(adminFileRequest);
        return "Success Upload!";
    }

    //공지사항 생성
    private AdminBoard writeText(AdminDto adminDto){
        AdminBoard save = adminBoardRepository.save(adminDto.toEntity());
        return save;
    }

    //글 삭제
    private void deleteText(){}


    //댓글 삭제
    private void deleteMiniComments(){

    }

    //파일 삭제
    private void deleteFile(){

    }

    private int changeFileUrl(AdminFileRequest adminFileRequest){
       try {
           AdminBoard adminBoard = adminBoardRepository.findByAdminId(adminFileRequest.getAdminId()).get();

           adminBoard.changeFileUrl(adminBoard);
           adminBoard.changeUpdateTime();
           return 1;
       }
       catch (Exception e){
           return 0;
       }
    }

}
