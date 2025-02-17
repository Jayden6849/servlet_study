package com.gn.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

@WebServlet("/boardCreateEnd")
public class BoardCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardCreateEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청시에 전달된 데이터를 담을 바구니를 만들어줌
		Board b = new Board();
		Attach a = new Attach();
		
		// 2. 파일을 업로드할 경로를 설정해줌 - io
		String path = "C:\\upload\\board";
		
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 3. 파일을 업로드할 저장 공간의 정보를 세팅 - apache.fileupload, apache.commons.jar
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(dir);		// 레포지토리로 사용할 공간의 위치
		factory.setSizeThreshold(1024*1024*10);	// 크기 제약 - 바이트 단위 (byte -> kb -> mb -> gb -> tb)
		
		// 4. 요청을 통해 전달된 데이터 읽어오기 - apache.fileupload, apache.commons.jar
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem = items.get(i);
				
				if(fileItem.isFormField()) {
					// 1) 파일 형태가 아닌 form 내부 요소
					System.out.println(fileItem.getFieldName());
				} else {
					// 2) 파일 형태인 form 내부 요소
					System.out.println(fileItem.getName());
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
