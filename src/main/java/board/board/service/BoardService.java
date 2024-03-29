package board.board.service;

import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;
import board.board.mapper.BoardMapper;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
	void setBoardMapper(BoardMapper boardMapper);
	List<BoardDto> selectBoardList();
	void insertBoard(BoardDto board,MultipartHttpServletRequest multipartHttpServletRequest);
	void insertBoard(BoardDto board);
	BoardDto selectBoardDetail(int boardIdx);
	void updateBoard(BoardDto board);
	void deleteBoard(int boardIdx);
	BoardFileDto selectBoardFileInformation(int idx, int boardIdx);
}
