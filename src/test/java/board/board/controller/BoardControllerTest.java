// reference: https://brunch.co.kr/@goldfing/1
package board.board.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BoardControllerTest {
	@Mock private BoardService boardService;	// mock
	@InjectMocks private BoardController controller; // controller is injected mock
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void openBoardListTest() {
		try {
			mockMvc.perform(get("/board/openBoardList.do"))
			.andExpect(status().isOk())
			.andExpect(view().name("/board/boardList"))
			.andExpect(model().attributeExists("list"));			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void openBoardWriteTest() {
		try {
			mockMvc.perform(get("/board/openBoardWrite.do"))
			.andExpect(status().isOk())
			.andExpect(view().name("/board/boardWrite"));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void insertBoardTest() {
		BoardDto testBoard = new BoardDto();
		testBoard.setTitle("Hello World");
		testBoard.setContents("hi!");
		
		try {
			mockMvc.perform(post("/board/insertBoard.do")
					.param("title",testBoard.getTitle())
					.param("contents", testBoard.getContents()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/board/openBoardList.do"))
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("boardDto"))
			.andExpect(model().attribute("boardDto", equalTo(testBoard)))
			.andDo(print());
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
