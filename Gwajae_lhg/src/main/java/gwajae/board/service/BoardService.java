package gwajae.board.service;

import static gwajae.common.JDBCTemplate.close;
import static gwajae.common.JDBCTemplate.commit;
import static gwajae.common.JDBCTemplate.getConnection;
import static gwajae.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import gwajae.board.dao.BoardDAO;
import gwajae.board.model.dto.Board;

public class BoardService {

	private BoardDAO dao =new BoardDAO();

	/** 게시판 전체 보기
	 * @param memberId
	 * @return
	 */
	public List<Board> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectAll(conn);
		
		close(conn);
		
		
		return boardList;
	}

	/** 로그인한 회원의 게시판 보기
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public Board selectOne(String boardNo, String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		Board boardOne = dao.selectOne(conn, boardNo, memberId);
		
		close(conn);
		
		return boardOne;
	}

	public int update(String title, String content, String memberId, String number) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.update(conn, title, content, memberId, number);
		
		if(result > 0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
	
	/** 내 게시글 전체 조회
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectMine(String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectMine(conn, memberId);
		
		close(conn);
		
		return boardList;
	}

	
	
	public List<Board> selectOne(String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectOne(conn, memberId);
		
		close(conn);
		
		return boardList;
	}

	public int delete(String boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.delete(conn, boardNo);
		
		if(result > 0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 삭제용 select
	 * @param memberId
	 * @return
	 */
	public List<Board> selectAll(String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectAll(conn, memberId);
		
		close(conn);
		
		return boardList;
	}



	
	
	
	
	
	
}
