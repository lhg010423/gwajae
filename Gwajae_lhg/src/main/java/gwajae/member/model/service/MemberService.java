package gwajae.member.model.service;

import static gwajae.common.JDBCTemplate.close;
import static gwajae.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import gwajae.member.model.dao.MemberDAO;
import gwajae.member.model.dto.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	/** 회원 로그인
	 * @param inputId
	 * @param inputPw
	 * @return loginMember
	 */
	public Member login(String inputId, String inputPw) throws Exception{
		
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, inputId, inputPw);
		
		close(conn);
		
		return loginMember;
	}

	

}
