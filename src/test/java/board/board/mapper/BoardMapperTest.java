package board.board.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import board.board.dto.BoardDto;

@Transactional
@SpringBootTest
public class BoardMapperTest {
	@Autowired private BoardMapper boardMapper;
	private List<BoardDto> list = new ArrayList<BoardDto>();
	
	@BeforeEach
	public void setUp() {
		BoardDto board1 = new BoardDto();
		board1.setBoardIdx(1);
		board1.setTitle("hello");
		board1.setContents("world");
		board1.setHitCnt(0);
		board1.setCreatorId("id1");
		board1.setCreatedDatetime("123");
		board1.setUpdaterId("456");
		board1.setUpdatedDatetime("2789");
		
		BoardDto board2 = new BoardDto();
		board2.setBoardIdx(2);
		board2.setTitle("ho");
		board2.setContents("world1");
		board2.setHitCnt(0);
		board2.setCreatorId("id2");
		board2.setCreatedDatetime("1243");
		board2.setUpdaterId("4586");
		board2.setUpdatedDatetime("7389");
		
		BoardDto board3 = new BoardDto();
		board3.setBoardIdx(3);
		board3.setTitle("hell");
		board3.setContents("world12");
		board3.setHitCnt(0);
		board3.setCreatorId("id3");
		board3.setCreatedDatetime("21243");
		board3.setUpdaterId("458621");
		board3.setUpdatedDatetime("787389");
		
		BoardDto board4 = new BoardDto();
		board4.setBoardIdx(4);
		board4.setTitle("howowow");
		board4.setContents("world1 123");
		board4.setHitCnt(0);
		board4.setCreatorId("id4");
		board4.setCreatedDatetime("781243");
		board4.setUpdaterId("412435");
		board4.setUpdatedDatetime("7234kjbweg89");
		
		BoardDto board5 = new BoardDto();
		board5.setBoardIdx(5);
		board5.setTitle("ho5555");
		board5.setContents("world11212");
		board5.setHitCnt(0);
		board5.setCreatorId("id5");
		board5.setCreatedDatetime("112243");
		board5.setUpdaterId("4586123");
		board5.setUpdatedDatetime("7123389");
		
		list.add(board1);
		list.add(board2);
		list.add(board3);
		list.add(board4);
		list.add(board5);
	}
	
	@Test
	public void deleteBoardAllTest(){
		boardMapper.deleteBoardAll();
		int cnt = boardMapper.countBoard();
		assertThat(cnt, is(0));
	}
	
	@Test
	public void insertBoardTest() {
		boardMapper.deleteBoardAll();
		
		for(int i=0;i<list.size();i++) {
			boardMapper.insertBoard(list.get(i));
		}
		
		assertThat(list.size(),is(5));
		assertThat(boardMapper.countBoard(), is(5));
		List<BoardDto> resList = boardMapper.selectBoardList();
		assertThat(resList.size(),is(5));
		compareDto(resList.get(0), list.get(4));
		compareDto(resList.get(1), list.get(3));
		compareDto(resList.get(2), list.get(2));
		compareDto(resList.get(3), list.get(1));
		compareDto(resList.get(4), list.get(0));
	}
	
	@Test
	public void updateBoardTest() {
		boardMapper.deleteBoardAll();
		
		for(int i=0;i<list.size();i++) {
			boardMapper.insertBoard(list.get(i));
		}		
		assertThat(boardMapper.countBoard(), is(5));
		List<BoardDto> firstList = boardMapper.selectBoardList();
		assertThat(firstList.size(),is(5));
		
		BoardDto temp1 = firstList.get(0);
		temp1.setTitle("baseball");
		temp1.setContents("baseball");
		boardMapper.updateBoard(temp1);
		BoardDto temp2 = firstList.get(3);
		temp2.setTitle("soccer");
		temp2.setContents("soccer");
		boardMapper.updateBoard(temp2);
		
		List<BoardDto> secondList = boardMapper.selectBoardList();
		compareDto(temp1, secondList.get(0));
		compareDto(firstList.get(1), secondList.get(1));
		compareDto(firstList.get(2), secondList.get(2));
		compareDto(temp2, secondList.get(3));
		compareDto(firstList.get(4), secondList.get(4));
	}
	
	@Test
	public void deleteBoardTest() {
boardMapper.deleteBoardAll();
		
		for(int i=0;i<list.size();i++) {
			boardMapper.insertBoard(list.get(i));
		}		
		assertThat(boardMapper.countBoard(), is(5));
		List<BoardDto> firstList = boardMapper.selectBoardList();
		assertThat(firstList.size(),is(5));
		
		BoardDto temp = firstList.get(0);
		boardMapper.deleteBoard(temp.getBoardIdx());
		temp = firstList.get(3);
		boardMapper.deleteBoard(temp.getBoardIdx());
		assertThat(boardMapper.countBoard(), is(5));
		
		List<BoardDto> secondList = boardMapper.selectBoardList();
		compareDto(firstList.get(1), secondList.get(0));
		compareDto(firstList.get(2), secondList.get(1));
		compareDto(firstList.get(4), secondList.get(2));
	}
	
	@Test
	public void updateHitCountTest() {
		boardMapper.deleteBoardAll();
		
		boardMapper.insertBoard(list.get(0));
		assertThat(boardMapper.countBoard(),is(1));
		List<BoardDto> firstList = boardMapper.selectBoardList();
		BoardDto temp = firstList.get(0);
		for(int i=0;i<5;i++)
			boardMapper.updateHitCount(temp.getBoardIdx());
		
		List<BoardDto> secondList = boardMapper.selectBoardList();
			
		assertThat(5, is(secondList.get(0).getHitCnt()));
	}
	
	private void compareList(List<BoardDto> list1, List<BoardDto> list2) {
		assertThat(list1.size(), is(list2.size()));
		for(int i=0;i<list1.size();i++) {
			compareDto(list1.get(i), list2.get(i));
		}
	}
	
	private void compareDto(BoardDto b1,BoardDto b2) {
//		assertThat(b1.getBoardIdx(), is(b2.getBoardIdx()));
		assertThat(b1.getTitle(), is(b2.getTitle()));
//		assertThat(b1.getContents(), is(b2.getContents()));
		assertThat(b1.getHitCnt(), is(b2.getHitCnt()));
//		assertThat(b1.getCreatorId(), is(b2.getCreatorId()));
//		assertThat(b1.getCreatedDatetime(), is(b2.getCreatedDatetime()));
//		assertThat(b1.getUpdaterId(), is(b2.getUpdaterId()));
//		assertThat(b1.getUpdatedDatetime(), is(b2.getUpdatedDatetime()));
	}
	
}
