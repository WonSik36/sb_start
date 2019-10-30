package board.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import board.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList();
	void insertBoard(BoardDto board);
	void updateHitCount(int boardIdx);
	BoardDto selectBoardDetail(int boardIdx);
	void updateBoard(BoardDto board);
	void deleteBoard(int boardIdx);
	void deleteBoardAll();
	int countBoard();
}
