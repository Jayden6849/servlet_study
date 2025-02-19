package com.gn.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Attach;

@WebServlet("/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FileDownloadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 읽어올 첨부파일의 PK를 읽어옴
		int attachNo = Integer.parseInt(request.getParameter("attach_no"));
		// System.out.println("FileDownloadServlet : "+attachNo);
		
		// 2. `attach` 테이블에서 PK를 기준으로 FilePath와 OriName을 가져올 것임 (Attach 객체에 담을 것) - 재사용
		Attach attach = new BoardService().selectAttachOne(attachNo);
		
		// 3. 파일명이 비어있는지 확인 (메타데이터가 있나요?)
		String filePath = attach.getAttachPath();
		if(filePath == null || filePath.trim().equals("")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		// 4. 파일 경로에 파일이 실제로 존재하는지 확인 (해당 메타데이터에 따라 실제로 파일이 존재하나요?)
		File file = new File(filePath);
		if(!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// 5. 파일 다운로드 응답 헤더를 설정 (지금 다운로드 받으려는 MimeType과 크기를 제공)
		response.setContentType("application/octet-stream");
		response.setContentLength((int)file.length());	// file.length() 는 반환타입이 long

		// 6. 파일명 설정 - oriName, encoding 세팅해줘야함
		String encodedFileName = URLEncoder.encode(attach.getOriName(), "UTF-8").replaceAll("\\+", "%20");
		response.setHeader("Content-Disposition", "attachment; filename=\""+encodedFileName+"\"");
		
		// 7. 서버단으로 파일을 읽어서(InputStream) 클라이언트로 전송함(OutputStream)
		//    cf. OutputStream이 최고 조상인데 이 클래스만 getOutputStream()을 지니고 있어서 사용하는 것 
		try(FileInputStream fis = new FileInputStream(file); OutputStream out = response.getOutputStream();) {
			byte[] buffer = new byte[1024];
			
			int byteRead;
			while((byteRead = fis.read(buffer)) != -1) {
				out.write(buffer, 0, byteRead);
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
