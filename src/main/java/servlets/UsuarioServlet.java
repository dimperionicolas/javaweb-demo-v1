package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DTO.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.Controller;
import model.Usuario;

@WebServlet(name = "UsuarioServlet", urlPatterns = "/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controller controller = new Controller();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<UsuarioDTO> userList = new ArrayList<>();
		userList = controller.getAllUsers();
		HttpSession session = request.getSession();
		session.setAttribute("userList", userList);
		response.sendRedirect("usuariostodos.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("_method");
		if (method == null) {
			// doPost
			String nombreusuario = request.getParameter("nombreusuario");
			String rol = request.getParameter("rol");
			String pass = request.getParameter("password");
			String repePass = request.getParameter("repeatpassword");

			if (!pass.equals(repePass)) {
				request.setAttribute("error", "Las contraseñas no coinciden");
				// TODO volver a intentarlo
				return;
			}
			controller.createUser(nombreusuario, rol, pass);
			response.sendRedirect("index.jsp");
		} else if (method.equalsIgnoreCase("DELETE")) {
			doDelete(request, response);
		} else if (method.equalsIgnoreCase("PATCH")) {
			doPrepareForUpdate(request, response);
		} else if (method.equalsIgnoreCase("UPDATE")) {
			doUpdate(request, response);
		}

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_eliminar = Integer.parseInt(request.getParameter("id_eliminar"));
		controller.deleteUser(id_eliminar);
		response.sendRedirect("usuariostodos.jsp");
	}

	protected void doPrepareForUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id_editar = Integer.parseInt(request.getParameter("id_editar"));
		Usuario userToEdit = controller.findUserById(id_editar);
		userToEdit.setContrasenia("");
		request.getSession().setAttribute("puedeEditar", true);
		try {
			if (request.getSession().getAttribute("idLogueado") != null && Integer
					.parseInt((String) request.getSession().getAttribute("idLogueado")) != userToEdit.getId_usuario()) {
				request.getSession().setAttribute("puedeEditar", true);
			}
		} catch (Exception e) {
			System.out.println("Esta dando error al obtener el ID Logueado");
			// TODO
		}
		request.getSession().setAttribute("userToEdit", userToEdit);
		response.sendRedirect("usuarioeditar.jsp");

	}

	protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario userToEdit = (Usuario) request.getSession().getAttribute("userToEdit");
		int id_editar = Integer.parseInt(request.getParameter("id_editar"));
		boolean puedeEditar = (boolean) request.getSession().getAttribute("puedeEditar");

		if (userToEdit.getId_usuario() != id_editar) {
			System.err.println("Error al obtener el usuario para editar. ");
			response.sendRedirect("index.jsp");
			return;
		}

		String nombreusuario = (String) request.getParameter("nombreusuario");
		String rol = (String) request.getParameter("rol");
		String password = (String) request.getParameter("password");
		String repeatpassword = (String) request.getParameter("repeatpassword");

		if (puedeEditar) {
			if (password.equals(repeatpassword)) {
				userToEdit.setContrasenia(password);
			} else {
				System.err.println("Error al cambiar la contraseña. No coinciden. ");
				response.sendRedirect("index.jsp");
				return;
			}
		}
		userToEdit.setNombre_usuario(nombreusuario);
		userToEdit.setRol(rol);

		controller.updateUser(userToEdit);
		response.sendRedirect("usuariostodos.jsp");

	}

}
