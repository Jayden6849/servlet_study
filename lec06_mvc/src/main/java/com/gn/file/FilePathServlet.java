package com.gn.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Attach;

@WebServlet("/filePath")
public class FilePathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FilePathServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 읽어올 첨부파일의 PK를 가져옴		
		int attachNo = Integer.parseInt(request.getParameter("attach_no"));
		
		// 2. `attach` 테이블에서 PK를 기준으로 FilePath와 OriName을 가져올 것임 (Attach 객체에 담을 것)
		Attach attach = new BoardService().selectAttachOne(attachNo);
		
		// 3. 파일명이 비어있는지 확인 (메타데이터가 있나요?)
		String filePath = attach.getAttachPath();
		if(filePath == null || filePath.trim().equals("")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);		// 400 오류를 발생시킬 것임 = 클라이언트의 요청에 오류가 있는 경우(잘못된 요청 : Bad Request)
			return;
		}
		
		// 4. 파일 경로에 파일이 실제로 존재하는지 확인 (해당 메타데이터에 따라 실제로 파일이 존재하나요?)
		File file = new File(filePath);
		if(!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);		// 404 오류를 발생시킬 것임 = 요청한 파일을 찾을 수 없는 경우(Not Found)
			return;
		}
		
		// 5. MIME 타입을 감지해야함 - 어떤 타입인지에 따라서 방식이 달라짐 (모든 형태가 가능하도록 보편적으로 작성)
		String mimeType = getServletContext().getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		
		// 6. 서버단으로 파일을 읽어서(InputStream) 클라이언트로 전송함(OutputStream)
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
		// System.out.println("FilePathServlet : "+filePath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
