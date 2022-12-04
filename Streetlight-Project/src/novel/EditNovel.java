package novel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.NovelDAO;


@WebServlet("/EditNovel")
public class EditNovel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNovel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			MultipartRequest mr = new MultipartRequest(request, request.getRealPath("/image"), 1024*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			String fileName = mr.getFilesystemName("image");
			String novelId,userId, novelName, novelIntro;
	        NovelDAO dao = new NovelDAO();
	        
	        int n = 0;
	        
	        
	        novelId = mr.getParameter("novelId");
	        novelName = mr.getParameter("title");
	        novelIntro = mr.getParameter("intro");
	        String[] novelTag = mr.getParameter("tag").split(",");
	        n = dao.updateNovel(novelId, fileName, novelName, novelIntro, novelTag);

	        if(n>0) {
	        	out.print("<script>alert('소설 수정이 완료되었습니다.'</script>");
	            response.sendRedirect("/write/novelWriteRoom.jsp");
	        }
	        else
	            out.print("<script>alert('입력 양식에 오류가 있습니다.');history.back();</script>");
		}catch(Exception e) {
			
		}
        
	}

}
