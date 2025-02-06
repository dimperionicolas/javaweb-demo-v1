package servlets;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;

import DTO.OdontoDTO;
import DTO.PacienteDTO;
import DTO.TurnoDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.Controller;

@WebServlet("/turnos")
public class TurnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Controller controller = new Controller();

	public TurnoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String odontoId = request.getParameter("odonotoid");
		String turnoId = request.getParameter("turnoid");
		String estado = request.getParameter("estado");
		String hora = request.getParameter("hora");
		String fecha = request.getParameter("fecha");

		SimpleEntry<OdontoDTO, TurnoDTO> odontologoAndTurno = controller.prepareOdontologoAndTurno(fecha, hora, estado,
				turnoId, odontoId);
		OdontoDTO odonto = odontologoAndTurno.getKey();
		TurnoDTO turno = odontologoAndTurno.getValue();
		request.getSession().setAttribute("odonto", odonto);
		request.getSession().setAttribute("turno", turno);

		response.getWriter().write("{\"success\":true,\"redirectUrl\":\"turnoalta.jsp\"}");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Object odontoObj = request.getSession().getAttribute("odonto");
			OdontoDTO odont;
			if (odontoObj != null && odontoObj instanceof OdontoDTO) {
				odont = (OdontoDTO) odontoObj;
			} else {
				response.sendRedirect("index.jsp");
				return;
			}
			TurnoDTO turno;
			Object turnoObj = request.getSession().getAttribute("turno");
			if (turnoObj != null && turnoObj instanceof TurnoDTO) {
				turno = (TurnoDTO) turnoObj;
			} else {
				response.sendRedirect("index.jsp");
				return;
			}

			PacienteDTO paci = new PacienteDTO();
			String dni = request.getParameter("dni");
			paci.setDni(dni);
			String nombre = request.getParameter("nombre");
			paci.setNombre(nombre);
			String apellido = request.getParameter("apellido");
			paci.setApellido(apellido);
			String telefono = request.getParameter("telefono");
			paci.setTelefono(telefono);
			String direccion = request.getParameter("direccion");
			paci.setDireccion(direccion);
			String fechanac = request.getParameter("fechanac");
			paci.setFechanac(fechanac);
			String tieneos = request.getParameter("tieneos");// si no
			paci.setTiene_os(false);
			String horario = request.getParameter("horario");
			turno.setHoraTurno(horario);

			if (tieneos.equals("true")) {
				paci.setTiene_os(true);
			}
			String tiposangre = request.getParameter("tiposangre");
			paci.setTipo_sangre(tiposangre);
			// String responsable = request.getParameter("responsable"); // null
			// paci.setResponsable(new Responsable());

			String motivoconsulta = request.getParameter("motivoconsulta");
			turno.setMotivo(motivoconsulta);

			controller.agendarTurno(odont, turno, paci);

			System.out.println();

//			controller.agendarTurno(fecha, hora, motivo, odontologoId, pacienteId);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Para cancelar un turno
		try {
			controller.deleteTurno(1);
		} catch (Exception e) {
			// Manejar el error
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

}
