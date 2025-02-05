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
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("odontotodos.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("_method");
		if (method == null && usuarioTienePermiso(request, method)) { // Create
			createOdontologo(request, response);
		} else if (method.equalsIgnoreCase("DELETE") && usuarioTienePermiso(request, method)) { // Delete
			doDelete(request, response);
		} else if (method.equalsIgnoreCase("PATCH") && usuarioTienePermiso(request, method)) { // Prepare
			doPrepareForUpdate(request, response);
		} else if (method.equalsIgnoreCase("UPDATE") && usuarioTienePermiso(request, method)) { // Update
			doUpdate(request, response);
		} else if (method.equalsIgnoreCase("DETAILS") && usuarioTienePermiso(request, method)) { // Details
			showDetails(request, response);
		}

	}

	private void createOdontologo(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("index.jsp");
	}

	private void showDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id_detalles = request.getParameter("id_detalles");
		OdontoDTO odontoById = controller.getOdontoById(id_detalles);
		HttpSession session = request.getSession();
		session.setAttribute("odontodetalle", odontoById);
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("odontodetalle.jsp");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_eliminar = request.getParameter("id_eliminar");
		controller.deleteOdonto(id_eliminar);
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("odontologo");
	}

	protected void doPrepareForUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_editar = request.getParameter("id_editar");
		OdontoDTO odontoToEdit = controller.getOdontoById(id_editar);
		request.getSession().setAttribute("odontoToEdit", odontoToEdit);
		response.setStatus(HttpServletResponse.SC_OK);
		response.sendRedirect("odontoeditar.jsp");
	}

	protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				hora_inicio, hora_fin); // TODO v2 ValidationService para cada DTO y pasar DTO y no todos los campos
		response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("odontologo").forward(request, response);
	}

	private boolean usuarioTienePermiso(HttpServletRequest request, String accion) {
		// TODO v2 Implementar validación de permisos
		// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 para no
		// autorizado (AuthorizationService)
		return true; // Temporalmente permitir todo
	}

}
