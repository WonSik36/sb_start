package board.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;

public interface JpaBoardService {

	List<BoardEntity> selectBoardList();

	void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest);
	
	BoardEntity selectBoardDetail(int boardIdx);

	void deleteBoard(int boardIdx);

	BoardFileEntity selectBoardFileInformation(int boardIdx, int idx);
}