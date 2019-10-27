package board.board.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BoardServiceTest {
	@Mock private BoardMapper mockMapper;
	@Autowired private BoardService boardService;
	private List<BoardDto> list = new ArrayList<BoardDto>();
	
	@BeforeEach
	public void setUp() {
		BoardDto board1 = new BoardDto();
		board1.setBoardIdx(1);
		board1.setTitle("hello");
		board1.setContents("world");
		board1.setHitCnt(3);
		board1.setCreatorId("id1");
		board1.setCreatedDatetime("123");
		board1.setUpdatorId("456");
		board1.setUpdatedDatetime("2789");
		
		BoardDto board2 = new BoardDto();
		board2.setBoardIdx(2);
		board2.setTitle("ho");
		board2.setContents("world1");
		board2.setHitCnt(4);
		board2.setCreatorId("id2");
		board2.setCreatedDatetime("1243");
		board2.setUpdatorId("4586");
		board2.setUpdatedDatetime("7389");
		
		BoardDto board3 = new BoardDto();
		board3.setBoardIdx(3);
		board3.setTitle("hell");
		board3.setContents("world12");
		board3.setHitCnt(5);
		board3.setCreatorId("id3");
		board3.setCreatedDatetime("21243");
		board3.setUpdatorId("458621");
		board3.setUpdatedDatetime("787389");
		
		BoardDto board4 = new BoardDto();
		board4.setBoardIdx(4);
		board4.setTitle("howowow");
		board4.setContents("world1 123");
		board4.setHitCnt(5);
		board4.setCreatorId("id4");
		board4.setCreatedDatetime("781243");
		board4.setUpdatorId("412435");
		board4.setUpdatedDatetime("7234kjbweg89");
		
		BoardDto board5 = new BoardDto();
		board4.setBoardIdx(5);
		board4.setTitle("ho5555");
		board4.setContents("world11212");
		board4.setHitCnt(5);
		board4.setCreatorId("id5");
		board4.setCreatedDatetime("112243");
		board4.setUpdatorId("4586123");
		board4.setUpdatedDatetime("7123389");
		
		list.add(board1);
		list.add(board2);
		list.add(board3);
		list.add(board4);
		list.add(board5);
	}
	
	/*
	 * Unit Test
	 * 
	*/
	@Test
	public void selectBoardList(){
		when(mockMapper.selectBoardList())
			.thenReturn(list);
		boardService.setBoardMapper(mockMapper);
		
		List<BoardDto> compList = boardService.selectBoardList();
		compareList(list,compList);
	}
	
	@Test
	public void insertBoard(){
		boardService.setBoardMapper(mockMapper);
		boardService.insertBoard(list.get(0));
		boardService.insertBoard(list.get(1));
		boardService.insertBoard(list.get(2));
		boardService.insertBoard(list.get(3));
		boardService.insertBoard(list.get(4));
		
		ArgumentCaptor<BoardDto> boardArg = ArgumentCaptor.forClass(BoardDto.class);
		verify(mockMapper, times(5)).insertBoard(boardArg.capture());
		List<BoardDto> insertedDtos = boardArg.getAllValues();
		compareList(insertedDtos, list);
	}
	
	@Test
	public void selectBoardDetail(){
		when(mockMapper.selectBoardDetail(anyInt()))
			.thenReturn(list.get(0));
		boardService.setBoardMapper(mockMapper);
		for(int i=0;i<5;i++) {			
			boardService.selectBoardDetail(i);
		}
		verify(mockMapper,times(5)).updateHitCount(anyInt());
		BoardDto testBoard = boardService.selectBoardDetail(0);
		compareDto(testBoard, list.get(0));
	}
	
	private void compareList(List<BoardDto> list1, List<BoardDto> list2) {
		assertThat(list1.size(), is(list2.size()));
		for(int i=0;i<list1.size();i++) {
			compareDto(list1.get(i), list2.get(i));
		}
	}
	
	private void compareDto(BoardDto b1,BoardDto b2) {
		assertThat(b1.getBoardIdx(), is(b2.getBoardIdx()));
		assertThat(b1.getTitle(), is(b2.getTitle()));
		assertThat(b1.getContents(), is(b2.getContents()));
		assertThat(b1.getHitCnt(), is(b2.getHitCnt()));
		assertThat(b1.getCreatorId(), is(b2.getCreatorId()));
		assertThat(b1.getCreatedDatetime(), is(b2.getCreatedDatetime()));
		assertThat(b1.getUpdatorId(), is(b2.getUpdatorId()));
		assertThat(b1.getUpdatedDatetime(), is(b2.getUpdatedDatetime()));
	}

}
