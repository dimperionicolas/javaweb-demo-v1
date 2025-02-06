$(document).ready(function() {
	// Cargar odontólogos en el combo al cargar la página
	$.ajax({
		url: 'calendar',
		method: 'GET',
		dataType: 'json',
		data: {
			_method: 'combo'
		}
		,
		success: function(odontologos) {
			const lista = $('#odontologosList');
			lista.empty(); // Limpiar lista existente

			// Llenar la lista de odontólogos
			odontologos.forEach(function(odontologo) {
				lista.append(`
		                    <a class="dropdown-item" 
		                       href="#" 
		                       data-id="${odontologo.id}"
		                       onclick="cargarTurnos(${odontologo.id})">
		                        ${odontologo.nombre}
		                    </a>
		                `);
			});

			// Si hay odontólogos, cargar turnos del primero automáticamente
			if (odontologos.length > 0) {
				$('#odontologos').text($(`[data-id="${odontologos[0].id}"]`).text());
				$(`[data-id="${odontologos[0].id}"]`).addClass('active');
				cargarTurnos(odontologos[0].id);
			}
		},
		error: function() {
			$('#odontologosList').html('<a class="dropdown-item">Error al cargar odontólogos</a>');
		}
	});


	// Inicializar calendario con carga dinámica
	$('#calendar').datepicker({
		language: "es",
		todayHighlight: true,
		autoclose: true
	}).on('changeDate', function(e) {
		const selectedDate = e.format('yyyy-mm-dd');
		const odontologo = $('#odontologosList .dropdown-item.active').data('id');
		cargarTurnos(odontologo, selectedDate);
	});


});

// Función para cargar turnos desde el servidor
function cargarTurnos(odontologo, fecha) {
	if (fecha == null)
		fecha = new Date().toISOString().split('T')[0];

	$.ajax({
		url: 'calendar', // La URL de tu servlet
		method: 'GET',
		data: { _method: 'lista', fecha: fecha, odontologo: odontologo }, // Pasar la fecha como parámetro
		dataType: 'json',
		success: function(turnos) {
			// Limpiar franjas existentes
			$('#timeSlots').empty();

			// Iterar sobre los turnos recibidos
			turnos.forEach(function(turno) {
				// Crear elemento para cada turno
				const turnoElemento = `
                    <a href="#" style="font-size: 0.85rem" class="list-group-item list-group-item-action time-slot ${turno.estado}" 
                       data-state="${turno.estado}" 
                       data-hour="${turno.hora}"
                       data-id="${turno.id}"
					   onclick="modificarTurno(${odontologo},${turno.id}, '${turno.estado}', ${turno.hora})">
                        ${turno.hora} - ${turno.estado}
                    </a>
                `;
				$('#timeSlots').append(turnoElemento);
			});
		},
		error: function(xhr, status, error) {
			console.error("Error al cargar turnos:", error);
			$('#timeSlots').html('<p>No se pudieron cargar los turnos</p>');
		}
	});
}

function modificarTurno(odonotoid, turnoid, estado, hora) {
	fecha = $('#calendar').datepicker('getDate');
	if (fecha != null) {
		fecha = fecha.toISOString().split('T')[0];
	} else {
		fecha = new Date().toISOString().split('T')[0];
	}
	$.ajax({
		url: 'calendar',
		method: 'GET',
		data: {
			_method: 'prepare',
			odonotoid: odonotoid,
			fecha: fecha,
			turnoid: turnoid,
			estado: estado,
			hora: hora
		},
		dataType: 'json',
		success: function(response) {
			console.log(response);
			if (response.success) {
				// Redirección exitosa
				window.location.href = response.redirectUrl || 'turnoalta.jsp';
			} else {
				// Mostrar mensaje de error
				alert(response.message || 'Error al modificar el turno');
			}
		},
		error: function(xhr, status, error) {
			console.error("Error al cargar turnos:", error);
			alert('Error en la comunicación con el servidor');
		}
	});
}

// Función auxiliar para determinar siguiente estado
function siguienteEstado(estadoActual) {
	const estados = ['disponible', 'ocupado', 'bloqueado'];
	const indiceActual = estados.indexOf(estadoActual);
	return estados[(indiceActual + 1) % estados.length];
}