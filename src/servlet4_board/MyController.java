package servlet4_board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// URL패턴 경로지정 
// @WebServlet("/")
@WebServlet(urlPatterns = { "/", "*.do" })
public class MyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("doGet 요청을 받음.");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req, resp);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("doPost 요청을 받음.");
	}

	

	void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		System.out.println("doProcess 요청을 받음");

		req.setCharacterEncoding("UTF-8");

		// URL패턴 경로지정 - 끝문자가 .do로 끝나는 경로에 대해서 지정
		// 예) http://localhost:8081/servlet4_board/
		// 예) http://localhost:8081/servlet4_board/list.do
		// 예) http://localhost:8081/servlet4_board/write.do
		// http://localhost:8081/servlet4_board/list.jsp 안됨.

		// 요청 URL을 분석
		String requestURI = req.getRequestURI();
		System.out.println("requestURI : " + requestURI);

		int cmdIndex = requestURI.lastIndexOf("/") + 1;
		String command = requestURI.substring(cmdIndex); // "" / "list.do"
		System.out.println("Command : " + command);

		String jspPage = "";

		// 기본 URI
		 // 요청 URL : http://localhost:8081/servlet4_borad/
		if (command.equals("") || command.equals("/")) {
			resp.sendRedirect("list.do");
		}
		// 메인 화면
		 // 요청 URL : http://localhost:8081/servlet4_board/list.do
		else if( command.equals( "list.do" ) ) {
			ArrayList<BoardDto> board_list = BoardDao.board_list();
			req.setAttribute("board_list", board_list); // JSP페이지 전송

			jspPage = "/list.jsp";
			
		} 
		// 글쓰기 화면
		 // 요청 URL : http://localhost:8081/servlet4_board/edit_view.do
		else if( command.equals( "edit_view.do" ) ) { 
			
			jspPage = "/edit_view.jsp";
		}
		// 글쓰기
		  // 요청 URL : http://localhost:8081/servlet4_board/edit.do 
		else if( command.equals( "edit.do" ) ) {
			// 코드 작성
			String board_name = req.getParameter( "board_name" );
			String board_title = req.getParameter( "board_title" );
			String board_content = req.getParameter( "board_content" );
			
			System.out.println( "board_name : " + board_name );
			System.out.println( "board_title : " + board_title );
			System.out.println( "board_content : " + board_content );
			
			BoardDao.edit( board_name, board_title, board_content );
			
			resp.sendRedirect( "list.do" );

		} 
		// 게시글 + 댓글 보기 화면
		else if( command.equals( "content_view.do" ) ) {
		      String board_idx = req.getParameter( "board_idx" );
		      BoardDao.hit( board_idx ); // 조회수 증가
		      
		      BoardDto board_dto = BoardDao.content_view( board_idx );
		      req.setAttribute( "board_dto", board_dto );
		      
		      ArrayList<ReplyDto> reply_list = BoardDao.reply_list(board_idx);
		      req.setAttribute("reply_list", reply_list);
		      
		      jspPage = "/content_view.jsp";
		}
		
		if ( !jspPage.equals("") ) { // jspPage가 비어있지 않다면
			RequestDispatcher dp = req.getRequestDispatcher( jspPage );
			dp.forward(req, resp);
		}
	}

}
