package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DTO.OdontoDTO;
import DTO.TurnoDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Controller;

@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Controller controller = new Controller();

	public CalendarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String method = request.getParameter("_method");

		if ("combo".equals(method)) {
			List<OdontoDTO> list = controller.getAllOdonto();

			StringBuilder json = new StringBuilder("[");
			for (OdontoDTO odonto : list) {
				json.append("{").append("\"id\":\"").append(odonto.getId()).append("\",").append("\"nombre\":\"")
						.append(odonto.getNombre()).append("\",").append("\"apellido\":\"").append(odonto.getApellido())
						.append("\"").append("},");
			}
			// Eliminar última coma y cerrar array
			if (json.charAt(json.length() - 1) == ',') {
				json.setLength(json.length() - 1);
			}
			json.append("]");

			response.getWriter().write(json.toString());

		} else if ("lista".equals(method)) {
			String fecha = request.getParameter("fecha");
			String odontoId = request.getParameter("odontologo");
			// Lógica para obtener los turnos
			List<TurnoDTO> turnos = controller.getTurnoByOdontoIdAndDate(odontoId, fecha);

			// Construcción manual de JSON
			StringBuilder json = new StringBuilder("[");
			for (TurnoDTO turno : turnos) {
				json.append("{").append("\"hora\":\"").append(turno.getHoraTurno()).append("\",")
						.append("\"estado\":\"").append(turno.getEstado()).append("\",").append("\"id\":\"")
						.append(turno.getId_turno()).append("\"").append("},");
			}
			// Eliminar última coma y cerrar array
			if (json.charAt(json.length() - 1) == ',') {
				json.setLength(json.length() - 1);
			}
			json.append("]");

			response.setContentType("application/json");
			response.getWriter().write(json.toString());
		} else if ("prepare".equals(method)) {
			try {
				// Procesar la solicitud
				String odontoId = request.getParameter("odonotoid");
				String turnoId = request.getParameter("turnoid");
				String estado = request.getParameter("estado");
				String hora = request.getParameter("hora");
				String fecha = request.getParameter("fecha");

				boolean validateDisponibilidadTurno = controller.validateDisponibilidadTurno(fecha, hora, estado,
						turnoId, odontoId);

				if (validateDisponibilidadTurno) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("turnos");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				out.println("{\"success\":false,\"message\":\"" + e.getMessage() + "\"}");
			}

		}
		// TODO _method es distinto, arrojar error
	}

	// crea o actualiza los turnos
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
