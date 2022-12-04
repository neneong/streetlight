package novel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NovelContentDAO;

/**
 * Servlet implementation class EditNovelContent
 */
@WebServlet("/EditNovelContent")
public class EditNovelContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNovelContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
        String contentName, content, novelIdx;
        NovelContentDAO dao = new NovelContentDAO();
        
        int n = 0;
        int i = 0;
        novelIdx = request.getParameter("novelId");
        contentName = request.getParameter("title");
        content = request.getParameter("content").replace("\r\n","<br>");
        
        n = dao.updateNovelContent(Integer.parseInt(novelIdx), contentName, content);

        if(n>0) {
        	out.print("<script>alert('소설내용 수정이 완료되었습니다.'</script>");
            response.sendRedirect("/write/novelWriteRoom.jsp");
        }
        else
            out.print("<script>alert('입력 양식에 오류가 있습니다.');history.back();</script>");
	}

}
