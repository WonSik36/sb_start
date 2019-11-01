package board.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;

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
	void insertBoardFileList(List<BoardFileDto> list);
	List<BoardFileDto> selectBoardFileList(int boardIdx);
	BoardFileDto selectBoardFileInformation(@Param("idx") int idx, @Param("boardIdx") int boardIdx);
}
