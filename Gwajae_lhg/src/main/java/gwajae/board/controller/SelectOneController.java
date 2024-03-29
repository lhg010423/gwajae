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

@WebServlet("/selectOne")
public class SelectOneController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			Member member = (Member) session.getAttribute("loginMember");
			
			BoardService service = new BoardService();
			
			List<Board> boardList = service.selectOne(member.getMemberId());
			
			req.setAttribute("boardList", boardList);
			
			req.getRequestDispatcher("/WEB-INF/views/selectOne.jsp").forward(req, resp);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
