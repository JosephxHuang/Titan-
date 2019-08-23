package rpc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection connection = DBConnectionFactory.getConnection();//創建一個DB connection, by default is mysql.
		try {
			HttpSession session = request.getSession(false);//false 代表在get的時候不會創建新的session. even if there is no session, we still do not create a session.
			JSONObject obj = new JSONObject();
			if (session != null) {//get, if there is session, 截取出來, and return to client
				String userId = session.getAttribute("user_id").toString();
				obj.put("status", "OK").put("user_id", userId).put("name", connection.getFullname(userId));
			} else {
				obj.put("status", "Invalid Session");//if no session, status invalid
				response.setStatus(403);
			}
			RpcHelper.writeJsonObject(response, obj);
		} catch (Exception e) {// if there is exception, print on command line
			e.printStackTrace();
		} finally {
			connection.close();// do it no matter what. 
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		DBConnection connection = DBConnectionFactory.getConnection();
		try {
			JSONObject input = RpcHelper.readJSONObject(request);
			String userId = input.getString("user_id");//enter user name
			String password = input.getString("password");//enter password
			
			JSONObject obj = new JSONObject();
			if (connection.verifyLogin(userId, password)) {// if password match
				HttpSession session = request.getSession();//create a session to the user if there is no session.
				session.setAttribute("user_id", userId);//why we need to set attribute?
				session.setMaxInactiveInterval(600);//600second no operation, we kill the session.
				obj.put("status", "OK").put("user_id", userId).put("name", connection.getFullname(userId));
			} else {
				obj.put("status", "User Doesn't Exist");
				response.setStatus(401);
			}
			RpcHelper.writeJsonObject(response, obj);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

}
