/**
 * 
 */

$(document).ready(function(){
    // Inicializar calendario
    $('#calendar').datepicker({
        language: "es",
        todayHighlight: true,
        autoclose: true
    }).on('changeDate', function(e){
        const selectedDate = e.format();
        updateTimeSlots(selectedDate); // Actualizar franjas al seleccionar fecha
    });

    // Generar franjas horarias
    function updateTimeSlots(date) {
        const slots = [];
        // Ejemplo: 3 estados (disponible, ocupado, pendiente)
        const states = ['disponible', 'ocupado', 'pendiente'];
        
        // Generar franjas de 8:00 a 20:00
        for (let hour = 8; hour <= 20; hour++) {
			
            const state = states[Math.floor(Math.random() * 3)]; // Estado aleatorio para el ejemplo
            slots.push(`
                <a href="#" style="font-size: 0.85rem" class="list-group-item list-group-item-action time-slot ${state}" 
                   data-state="${state}" 
                   data-hour="${hour}:00">
                    ${hour}:00 - ${state}
                </a>
            `);
			
			
        }
        
        $('#timeSlots').html(slots.join(''));
    }

    // Cambiar estado al hacer clic (ejemplo)
    $(document).on('click', '.time-slot', function(){
        const states = ['disponible', 'ocupado', 'pendiente'];
        const currentState = $(this).data('state');
        const newIndex = (states.indexOf(currentState) + 1) % 3;
        const newState = states[newIndex];
        
        $(this)
            .removeClass(currentState)
            .addClass(newState)
            .data('state', newState)
            .text(`${$(this).data('hour')} - ${newState}`);
    });

    // Inicializar con fecha actual
    updateTimeSlots(new Date());
});