package gwajae.board.dao;

import static gwajae.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import gwajae.board.model.dto.Board;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public BoardDAO() {
		
		try {
			prop = new Properties();
			
			String filePath = BoardDAO.class.getResource("/gwajae/sql/board-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 전체 게시판 출력
	 * @param conn
	 * @param memberId
	 * @return boardList
	 */
	public List<Board> selectAll(Connection conn) throws Exception{
		
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setWriter(rs.getString("MEMBER_ID"));
				
				boardList.add(board);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		return boardList;
	}


	public Board selectOne(Connection conn, String boardNo, String memberId) throws Exception{
		
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectOne");
			 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board = new Board();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setWriter(rs.getString("MEMBER_ID"));
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}


	public int update(Connection conn, String title, String content, String memberId, String number) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, number);
			pstmt.setString(4, memberId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 내 게시글 조회
	 * @param conn
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectMine(Connection conn, String memberId) throws Exception{
		
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			
			String sql = prop.getProperty("selectMine");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setWriter(rs.getString("MEMBER_ID"));
				
				boardList.add(board);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return boardList;
	}


	public List<Board> selectOne(Connection conn, String memberId) throws Exception{
		
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectMine");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setWriter(rs.getString("MEMBER_ID"));
				
				boardList.add(board);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}


	public int delete(Connection conn, String boardNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public List<Board> selectAll(Connection conn, String memberId) throws Exception{
		
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectMine");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setWriter(rs.getString("MEMBER_ID"));
				
				boardList.add(board);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
