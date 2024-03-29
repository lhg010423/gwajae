package gwajae.member.controller;

import java.io.IOException;
import java.util.List;

import gwajae.board.model.dto.Board;
import gwajae.board.service.BoardService;
import gwajae.member.model.dto.Member;
import gwajae.member.model.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
				
			// 아이디, 비번 가져오기
			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");
			
			MemberService service = new MemberService();
			
			Member loginMember = service.login(inputId, inputPw);
			
			HttpSession session = req.getSession();
			
			if(loginMember != null) { // 로그인 성공
				
				session.setAttribute("loginMember", loginMember);
				
				session.setMaxInactiveInterval(60 * 30);
				
				BoardService boardService = new BoardService();
				
				List<Board> boardList = boardService.selectAll();
				
				session.setAttribute("boardList", boardList);
				
				resp.sendRedirect("/");
				
			} else {
				
				session.setAttribute("message", "아이디 또는 비밀번호 불일치");
				
				String referer = req.getHeader("referer");
				
				resp.sendRedirect(referer);
				
			}
			
		} catch (Exception e) {
			System.out.println("로그인 중 오류 발생");
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
