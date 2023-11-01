package com.example.notice.board.service;

import com.example.notice.admin.dto.AdminDto;
import com.example.notice.board.entity.AdminBoard;
import com.example.notice.board.repository.AdminBoardRepository;
import com.example.notice.board.repository.ClassBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private void uploadFile(){

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

}
