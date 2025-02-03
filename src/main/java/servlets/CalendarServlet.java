package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DTO.OdontoDTO;
import DTO.TurnoDTO;
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
		String odontoId = request.getParameter("odontologo");
		String fecha = request.getParameter("fecha");
		String method = request.getParameter("_method");

		if ("combo".equals(method)) {
			response.setContentType("application/json");
			List<OdontoDTO> list = new ArrayList<>();
			OdontoDTO odontologo = new OdontoDTO();
			odontologo.setId("5");
			odontologo.setNombre("Nicolas");
			odontologo.setApellido("Dimperio");
			list.add(odontologo);
			OdontoDTO od = new OdontoDTO();
			od.setId("6");
			od.setNombre("roberto");
			od.setApellido("sarasa");
			list.add(od);

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
			// Lógica para obtener los turnos
			List<TurnoDTO> turnos = controller.obtenerTurnosPorMedico(odontoId, fecha);

			// Construcción manual de JSON
			StringBuilder json = new StringBuilder("[");
			for (TurnoDTO turno : turnos) {
				json.append("{").append("\"hora\":\"").append(turno.getHoraTurno()).append("\",")
						.append("\"estado\":\"").append(turno.getEstado()).append("\",").append("\"id\":\"")
						.append(turno.getEstado()).append("\"").append("},");
			}
			// Eliminar última coma y cerrar array
			if (json.charAt(json.length() - 1) == ',') {
				json.setLength(json.length() - 1);
			}
			json.append("]");

			response.setContentType("application/json");
			response.getWriter().write(json.toString());
		}

	}

	// actualiza los turnos
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
