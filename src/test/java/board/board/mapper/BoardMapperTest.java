package board.board.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import board.board.dto.BoardDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardMapperTest {
	@Autowired private BoardMapper boardMapper;
	
	@Test
	public void selectBoardList(){
		List<BoardDto> list = boardMapper.selectBoardList();
	}
}
