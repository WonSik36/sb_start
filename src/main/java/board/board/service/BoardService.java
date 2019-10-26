package board.board.service;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

import java.util.List;

public interface BoardService {
	void setBoardMapper(BoardMapper boardMapper);
	List<BoardDto> selectBoardList();
	void insertBoard(BoardDto board);
}
