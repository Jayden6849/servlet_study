package com.gn.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gn.board.service.BoardService;
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
					// System.out.println(fileItem.getFieldName()); // name 속성(키값)이 찍힘
					
					// 각 키값에 따라 구분하여 바구니(Board 객체)에 담아줌
					switch(fileItem.getFieldName()) {
					case "board_title":
						b.setBoardTitle(fileItem.getString("UTF-8"));
						break;
					case "board_content":
						b.setBoardContent(fileItem.getString("UTF-8"));
						break;
					case "board_writer":
						b.setBoardWriter(Integer.parseInt(fileItem.getString("UTF-8")));
						break;
					}
				} else {
					// 2) 파일 형태인 form 내부 요소
					// System.out.println(fileItem.getName()); // 파일명이 찍힘
					
					if(fileItem.getSize() > 0) {
						// 파일의 내용물이 있을 때만 저장
						String oriName = fileItem.getName();
						
						// 확장자는 UUID로 변경하면 안 되기 때문에 따로 추출
						int idx = oriName.lastIndexOf(".");
						String ext = oriName.substring(idx);
						
						// UUID 방식으로 숨겨서 저장할 것임
						String uuid = UUID.randomUUID().toString().replace("-","");
						String newName = uuid+ext;
						
						File uploadFile = new File(dir, newName);
						fileItem.write(uploadFile);
						// 파일 데이터를 서버에 저장하는 절차 끝!
						
						// 파일 메타데이터를 Attach 바구니에 담아둠
						a.setOriName(oriName);
						a.setNewName(newName);
						a.setAttachPath(path+"\\"+newName); // C:\\upload\\board\\새로운이름.확장자
					}
				}
			}
			
			// 바구니에 들어있는 데이터를 확인 - 콘솔창에 찍히는지 확인
			System.out.println(b);
			System.out.println(a);		
			
			// 지정한 경로에 파일이 잘 업로드 되었는지 확인 - 폴더가서 눈으로 확인
			
			// 여기까지 완료되면 파일이 정상적으로 업로드 되었다는 것
			
			// 이제 파일 정보를 DB에 업로드해야함 (Board, Attatch 각각의 정보를 insert 하는 트랜잭션 처리해줘야함)
			int result = new BoardService().createBoard(b, a);
			
			// 결과에 따라 각각 jsp로 이동
			RequestDispatcher view = request.getRequestDispatcher("/views/board/create_fail.jsp");
			
			if(result > 0) {
				view = request.getRequestDispatcher("/views/board/create_success.jsp");
			}
			
			view.forward(request, response);
			
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
