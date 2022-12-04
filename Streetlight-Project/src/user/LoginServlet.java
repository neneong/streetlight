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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		String id, pwd;
		boolean result = false;
		PrintWriter out = response.getWriter();
		
		id = request.getParameter("id");
		pwd = request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		result = dao.getMemberPwd(id, pwd);
		
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("loginOK", id);
			response.sendRedirect("/index.jsp");
		}else {
			out.println("<script> alert('아이디 또는 비밀번호가 정확하지 않습니다.');history.back();</script>");
		}
	}



}
