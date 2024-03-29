package gwajae.board.controller;

import java.io.IOException;
import java.util.List;

import gwajae.board.model.dto.Board;
import gwajae.board.service.BoardService;
import gwajae.member.model.dto.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateController extends HttpServlet{
	
	private BoardService service = new BoardService();
	private Board bd = new Board();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		try {
			
			HttpSession session = req.getSession();
			
			Member member = (Member) session.getAttribute("loginMember");
			
			
			Board board = service.selectOne(req.getParameter("boardNo"), member.getMemberId());
			
			req.setAttribute("board", board);
			
			req.getRequestDispatcher("WEB-INF/views/update.jsp").forward(req, resp);
			
			/*
			if(member.getMemberId().equals(bd.getWriter())) {
				req.getRequestDispatcher("WEB-INF/views/update.jsp").forward(req, resp);
				resp.sendRedirect("/");
				return;
			}
			resp.sendRedirect("/");
			return;
			*/
			
		} catch (Exception e) {
			System.out.println("게시글 수정 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			HttpSession session = req.getSession();
			
			Member member = (Member) session.getAttribute("loginMember");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String number = req.getParameter("boardNo");
			
			int result = service.update(title, content, member.getMemberId(), number);
			
			if(result > 0) {
				
				session.setAttribute("message", "수정되었습니다");
				
				List<Board> boardList = service.selectMine(member.getMemberId());
				session.setAttribute("boardList", boardList);
				
				resp.sendRedirect("/selectOne");
			} else {
				session.setAttribute("message", "수정 실패");
				
				resp.sendRedirect(req.getHeader("referer"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
