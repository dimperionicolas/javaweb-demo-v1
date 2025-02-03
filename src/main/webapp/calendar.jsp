<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="components/plantillastart.jsp"%>


        <h1 class="h3 mb-2 text-gray-800">Lista de días y horarios</h1>
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Turnos</h6>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class=" col-sm-4 mb-3 mb-sm-0">
                        <div class="card  container-fluid">
                            <!-- Calendario (Izquierda) -->
                            <div class="col-md-6 h-50 d-flex flex-column">

                                <div id="calendar"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-8 mb-3 mb-sm-0">
                        <div class="card  container-fluid ">
                            <!-- Franjas Horarias (Derecha) -->
                            <div class="col-md-18 h-80 d-flex flex-column">
                                <div id="timeSlots" class="list-group overflow-auto" style="max-height: 70vh;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<%@include file="components/plantillaend.jsp"%>
