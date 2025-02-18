package com.gn.board.dao;

import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

public class BoardDao {
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int boardNo = 0;

		try {
			String sql = "INSERT INTO `board` (`board_title` ,`board_content` ,`board_writer`) "
					+ "VALUES (? ,? ,?)";
			
			// (1) 매개변수 추가
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardWriter());
			
			pstmt.executeUpdate();
			
			// (2) 생성된 키를 반환받음
			rs = pstmt.getGeneratedKeys();	// Auto_INCREMENT 적용된 키를 새로 불러오는 것임
			
			if(rs.next()) {
				boardNo = rs.getInt(1); // getGenerateKeys의 첫번째 컬럼값(int) = PK
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardNo;
	}
	
	public int insertAttach(Connection conn, Attach a) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int boardNo = 0;
		
		try {
			String sql = "INSERT INTO `attach` (`board_no` ,`ori_name` ,`new_name` ,`attach_path`) VALUES(? ,? ,? ,?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, a.getBoardNo());
			pstmt.setString(2, a.getOriName());
			pstmt.setString(3, a.getNewName());
			pstmt.setString(4, a.getAttachPath());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys(); // Auto_INCREMENT 적용된 키를 새로 불러오는 것임
			
			if(rs.next()) {
				boardNo = rs.getInt(1);	// getGenerateKeys의 첫번째 컬럼값(int) = PK
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardNo;
	}
	
	public List<Board> selectBoardList(Connection conn, Board option) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Board> resultList = new ArrayList<>();
		
		// board_no, board_title, board_content, 게시글 작성자의 닉네임(Join해서 가져와야함), reg_date, mod_date
		
		try {
			String sql = "SELECT * FROM `board` b JOIN `member` m ON m.member_no = b.board_writer";
			if(option.getBoardTitle() != null) {
				sql += " WHERE `board_title` LIKE CONCAT('%', '"+option.getBoardTitle()+"', '%')";
			}
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("b.board_no"));
				board.setBoardTitle(rs.getString("b.board_title"));
				board.setBoardContent(rs.getString("b.board_content"));
				board.setMemberName(rs.getString("m.member_name"));
				board.setRegDate(rs.getTimestamp("b.reg_date").toLocalDateTime());
				board.setModDate(rs.getTimestamp("b.mod_date").toLocalDateTime());
				resultList.add(board);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return resultList;
	}
}
