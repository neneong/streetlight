package novel;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.NovelDAO;
import dao.NovelTagDAO;
import dao.TagDAO;


@WebServlet("/AddNovel")
public class AddNovel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNovel() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
        String userId, novelName, novelIntro;
        NovelDAO dao = new NovelDAO();
        
        int n = 0;

        userId = request.getParameter("userId");
        novelName = request.getParameter("title");
        novelIntro = request.getParameter("intro");
        String[] novelTag = request.getParameter("tag").split(",");
        
        
        n = dao.addNovel(userId, novelName, novelIntro, novelTag);

        if(n>0) {
        	out.print("<script>alert('소설 작성이 완료되었습니다.'</script>");
            response.sendRedirect("/write/novelWriteRoom.jsp");
        }
        else
            out.print("<script>alert('입력 양식에 오류가 있습니다.');history.back();</script>");
	}



}
