package board.board.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardDtoTest {
	BoardDto board1, board2;
	@BeforeEach
	public void setUp() {
		board1 = new BoardDto();
		board1.setBoardIdx(1);
		board1.setTitle("hello");
		board1.setContents("world");
		board1.setHitCnt(3);
		board1.setCreatorId("id1");
		board1.setCreatedDatetime("123");
		board1.setUpdatorId("456");
		board1.setUpdatedDatetime("2789");
		
		board2 = new BoardDto();
		board2.setBoardIdx(2);
		board2.setTitle("ho");
		board2.setContents("world1");
		board2.setHitCnt(4);
		board2.setCreatorId("id2");
		board2.setCreatedDatetime("1243");
		board2.setUpdatorId("4586");
		board2.setUpdatedDatetime("7389");
	}
	
	@Test
	public void getTest() {
		assertThat(board1.getBoardIdx(), is(1));
		assertThat(board1.getTitle(), is("hello"));
		assertThat(board1.getContents(), is("world"));
		assertThat(board1.getHitCnt(), is(3));
		assertThat(board1.getCreatorId(), is("id1"));
		assertThat(board1.getCreatedDatetime(), is("123"));
		assertThat(board1.getUpdatorId(), is("456"));
		assertThat(board1.getUpdatedDatetime(), is("2789"));
	}
	
	@Test
	public void setTest() {
		board1.setBoardIdx(2);
		board1.setTitle("ho");
		board1.setContents("world1");
		board1.setHitCnt(4);
		board1.setCreatorId("id2");
		board1.setCreatedDatetime("1243");
		board1.setUpdatorId("4586");
		board1.setUpdatedDatetime("7389");
		
		compareDto(board1, board2);
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
