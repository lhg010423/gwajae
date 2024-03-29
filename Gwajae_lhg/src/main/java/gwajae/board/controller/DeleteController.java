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

@WebServlet("/delete")
public class DeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String boardNo = req.getParameter("boardNo");
			
			BoardService service = new BoardService();
			
			int result = service.delete(boardNo);
			
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("loginMember");
			
			if(result > 0) {
				
				List<Board> boardList = service.selectAll(member.getMemberId());
				session.setAttribute("boardList", boardList);
				
			} else {
				session.setAttribute("message", "삭제 실패");
			}
			
			resp.sendRedirect("/");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
