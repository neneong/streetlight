package user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		String id, pwd, birth, nick;
		PrintWriter out = response.getWriter();
		
		id = request.getParameter("id");
		if(id.length()>10) {
			out.println("<script> alert('아이디값이 범위에서 벗어났습니다.\n다시한번 확인해주세요');history.back();</script>");
		}
		
		MemberDAO dao = new MemberDAO();
		String checker = dao.getMemberId(id);
		
		if(id.equals(checker)) {
			out.println("<script> alert('이미 존재하는 아이디입니다.\n다른 아이디로 가입해주세요');history.back();</script>");
		}
		
		pwd = request.getParameter("pwd");
		if(pwd.length()>10) {
			out.println("<script> alert('비밀번호값이 범위에서 벗어났습니다.\n다시한번 확인해주세요');history.back();</script>");
		}
		birth = request.getParameter("birth");
		if(birth.length()>10) {
			out.println("<script> alert('생년월일값이 범위에서 벗어났습니다.\n다시한번 확인해주세요');history.back();</script>");
		}
		nick = request.getParameter("nick");
		if(nick.length()>10) {
			out.println("<script> alert('닉네임 입력값이 범위에서 벗어났습니다.\n다시한번 확인해주세요');history.back();</script>");
		}
	
		dao.insertMember(id, pwd, nick, birth);
		
		out.println("<script> alert('회원가입이 완료되었습니다');</script>");
		response.sendRedirect("/index.jsp");
		
		
	}

}
