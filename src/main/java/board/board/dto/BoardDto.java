package board.board.dto;
import lombok.Data;

@Data
public class BoardDto {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String CreatorId;
	private String createdDatetime;
	private String updatorId;
	private String updatedDatetime;
}
