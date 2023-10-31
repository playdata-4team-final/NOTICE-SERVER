package com.example.notice.board.service;

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
    private void writeMiniComments(){}

    //파일 업로드
    private void uploadFile(){}

    //글 작성
    private void writeText(){}

    //글 삭제
    private void deleteText(){}

    //댓글 삭제
    private void deleteMiniComments(){}

    //파일 삭제
    private void deleteFile(){}

}
