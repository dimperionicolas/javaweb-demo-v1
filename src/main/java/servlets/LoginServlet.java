package servlets;

import java.io.IOException;

import DTO.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.Controller;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controller controller = new Controller();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");

		UsuarioDTO loggedUser = controller.validateLogin(username, password);
		if (loggedUser != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedUser", loggedUser);
			response.sendRedirect("index.jsp");
		} else {
			// TODO redireccionar?
			response.sendRedirect("loginerror.jsp");
		}
	}

}
