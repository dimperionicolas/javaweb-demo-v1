package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DTO.OdontoDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.Controller;

@WebServlet(name = "OdontologoServlet", urlPatterns = "/odontologo")
public class OdontologoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controller controller = new Controller();

	public OdontologoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<OdontoDTO> odontoList = new ArrayList<>();
		odontoList = controller.getAllOdonto();
		HttpSession session = request.getSession();
		session.setAttribute("odontoList", odontoList);
		response.sendRedirect("odontotodos.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("_method");
		if (method == null) { // Create
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String telefono = request.getParameter("telefono");
			String direccion = request.getParameter("direccion");
			String especialidad = request.getParameter("especialidad");
			String fechanac = request.getParameter("fechanac");
			String hora_inicio = request.getParameter("hora_inicio");
			String hora_fin = request.getParameter("hora_fin");
			controller.createOdontologo(dni, nombre, apellido, telefono, direccion, especialidad, fechanac, hora_inicio,
					hora_fin);
			response.sendRedirect("index.jsp");
		} else if (method.equalsIgnoreCase("DELETE")) { // Delete
			doDelete(request, response);
		} else if (method.equalsIgnoreCase("PATCH")) { // Prepare
			doPrepareForUpdate(request, response);
		} else if (method.equalsIgnoreCase("UPDATE")) { // Update
			doUpdate(request, response);
		} else if (method.equalsIgnoreCase("DETAILS")) { // Details
			showDetails(request, response);
		}

	}

	private void showDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id_detalles = request.getParameter("id_detalles");
		if (true) {
			// TODO validar si puede ver detalles
		}
		OdontoDTO odontoById = controller.findOdontoById(id_detalles);
		HttpSession session = request.getSession();
		session.setAttribute("odontodetalle", odontoById);
		response.sendRedirect("odontodetalle.jsp");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (true) {
			// TODO validar si puede eliminar
		}
		String id_eliminar = request.getParameter("id_eliminar");
		controller.deleteOdonto(id_eliminar);
		response.sendRedirect("odontologo");
	}

	protected void doPrepareForUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_editar = request.getParameter("id_editar");
		OdontoDTO odontoToEdit = controller.findOdontoById(id_editar);
		if (true) {
			// TODO validar si puede editar
		}
		request.getSession().setAttribute("odontoToEdit", odontoToEdit);
		response.sendRedirect("odontoeditar.jsp");
	}

	protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		// response.setHeader("Pragma", "no-cache");
		// response.setDateHeader("Expires", 0);
		if (true) {
			// TODO validar si puede editar
		}
		String id_editar = request.getParameter("id_editar");
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		String especialidad = request.getParameter("especialidad");
		String fechanac = request.getParameter("fechanac");
		String hora_inicio = request.getParameter("hora_inicio");
		String hora_fin = request.getParameter("hora_fin");
		controller.updateOdonto(id_editar, dni, nombre, apellido, telefono, direccion, especialidad, fechanac,
				hora_inicio, hora_fin);
		request.getRequestDispatcher("odontologo").forward(request, response);
	}

}
