package gwajae.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
	
	private int memberNo; // 회원번호
	private String memberId; // 회원 아이디
	private String memberPw; // 회원 비번
	private String memberNickname; // 회원 이름
	private String enrollDate; // 계정 생성일
	private String memberDeleteFlag; // 탈퇴 여부
	
	
}
