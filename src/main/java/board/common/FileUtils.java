package board.common;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.board.dto.BoardFileDto;

@Component
public class FileUtils {
	
	// save file and return parsed BoardFileDto list
	public List<BoardFileDto> parseFileInfoOrNull(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest){
		if(ObjectUtils.isEmpty(multipartHttpServletRequest))
			return null;
		
		// make directories yyyyMMdd for file
		List<BoardFileDto> fileList = new ArrayList<BoardFileDto>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/"+current.format(format);
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		String newFileName, originalFileExtension, contentType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for(MultipartFile multipartFile: list) {
				if(multipartFile.isEmpty()) 
					continue;
				
				contentType = multipartFile.getContentType();
				if(ObjectUtils.isEmpty(contentType))
					break;
				
				if(contentType.contains("image/jpeg"))
					originalFileExtension = ".jpg";
				else if(contentType.contains("image/png"))
					originalFileExtension = ".png";
				else if(contentType.contains("image/gif"))
					originalFileExtension = ".gif";
				else
					break;
				
				// make file name which is not duplicated
				newFileName = Long.toString(System.nanoTime())+originalFileExtension;
				
				// make BoardFileDto
				BoardFileDto boardFile = new BoardFileDto();
				boardFile.setBoardIdx(boardIdx);
				boardFile.setFileSize(multipartFile.getSize());
				boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
				boardFile.setStoredFilePath(path+"/"+newFileName);
				fileList.add(boardFile);
				
				// save new file to server
				file = new File(path+"/"+newFileName);
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return fileList;
	}
}
