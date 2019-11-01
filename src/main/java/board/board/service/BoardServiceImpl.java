package board.board.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired private BoardMapper boardMapper;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void setBoardMapper(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public List<BoardDto> selectBoardList() {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) {
		//boardMapper.insertBoard(board);
		if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			String name;
			while(iterator.hasNext()) {
				name = iterator.next();
				log.debug("file tag name: "+name);
				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile multipartFile: list) {
					log.debug("start file information");
					log.debug("file name: "+multipartFile.getOriginalFilename());
					log.debug("file size: "+multipartFile.getSize());
					log.debug("file content type: "+multipartFile.getContentType());
					log.debug("end file information\n");
				}
				
			}
		}
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) {
		boardMapper.updateHitCount(boardIdx);

		return boardMapper.selectBoardDetail(boardIdx);
	}

	@Override
	public void updateBoard(BoardDto board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardIdx) {
		boardMapper.deleteBoard(boardIdx);
	}
}
