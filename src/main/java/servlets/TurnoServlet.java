package servlets;

import java.io.IOException;
import java.time.LocalDate;

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
		boolean disponible = controller.verificarDisponibilidadTurno(LocalDate.now().plusDays(1), "14:00", 1);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			LocalDate fecha = LocalDate.now().plusDays(1);
			String hora = "14:00";
			String motivo = "Consulta general";
			int odontologoId = 1;
			int pacienteId = 1;

			controller.agendarTurno(fecha, hora, motivo, odontologoId, pacienteId);
		} catch (Exception e) {
			// Manejar el error (turno no disponible, entidades no encontradas, etc.)
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Para cancelar un turno
		try {
			controller.cancelarTurno(1);
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
