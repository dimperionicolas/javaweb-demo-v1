# JavaWeb Demo (v1)

Proyecto de prueba para practicar tecnologías Java web.

## Descripción
Este proyecto es parte de mi camino de aprendizaje en Java web. 
Utilicé Maven, Webjars, JPA, Hibernate, MySQL, JSP y Servlets sobre Java 17 y Jakarta EE 9.1. 
Experimenté con estas tecnologías para conocer sobre los pilares para trabajar con Java Enterprise. 
Es una demo básica que quedará aquí, pero es la primera prueba de una serie de demos. Próximas pruebas integrarán otras tecnologías y frameworks. 

## Tecnologías y Herramientas

- **Java 17:**  

- **Jakarta EE 9.1:**  
  - **Jakarta Servlet** Gestión de peticiones HTTP y el ciclo de vida de las servlets.
  - **Jakarta Server Pages (JSP)** Generación de contenido HTML.
  - **Jakarta Expression Language (EL)** Expresiones en las vistas.
  - **Jakarta Persistence (JPA)** Gestión de la persistencia y ORM.

- **Hibernate** Implementación de JPA.

- **MySQL Connector/J**  Driver JDBC para conectar con base de datos MySQL.

- **Webjars:**  Integración de recursos web para gestionar dependencias del lado del cliente. Versiones especificas por compatibilidad con SB Admin 2:
  - **Startbootstrap SB Admin 2 (v4.1.3):** Tema de administración para interfaces de usuario.
  - **Bootstrap 4.6.0:** Framework CSS para diseño responsivo y componentes UI.
  - **jQuery 3.6.0:** Biblioteca JavaScript para manipulación del DOM y AJAX.
  - **DataTables 1.10.24:** Plugin de jQuery para la gestión y visualización de tablas de datos.
  - **jQuery Easing 1.4.1:** Para animaciones suaves en la interfaz.
  - **Chart.js 2.9.4:** Biblioteca para la representación gráfica de datos.
  - **Font Awesome 6.5.1:** Conjunto de íconos vectoriales para mejorar la apariencia visual.
  - **Bootstrap Datepicker 1.9.0:** Componente para la selección de fechas.
  - **Webjars Locator Core y Webjars Servlet 2.x:** Para facilitar la localización y carga de los recursos de Webjars en la aplicación.

  - **IDE:** Eclipse IDE con servidor Tomcat v11 y Maven embebidos.  

## Estructura del Proyecto
- `src/main/java`: Código fuente.
- `src/main/webapp`: web.xml, *.JSP , componentes, js y css específico.
- `src/main/resources`: Configuraciones (persistence.xml).
- `src/test/java`: A implementar.


## Configuración y Ejecución
1. Clonar el repositorio.
2. Configurar la base de datos MySQL en `src/main/resources/META-INF/persistence.xml` 
3. Ejecutar `mvn clean install` para compilar.
4. Desplegar la aplicación en servidor.

## Estado del Proyecto
Proyecto incompleto; es parte de una serie de proyectos demos de aprendizaje:
- **javaweb-demo:** Versión actual, desarrollada con JSP, Servlets, JPA/Hibernate, y recursos gestionados vía Webjars.
- **javaweb-demo2:** Próxima versión en la que se explorarán nuevas tecnologías y arquitecturas.
- **javaweb-demo3:** Futuro proyecto que incluirá dockerización, despliegue automático y testing integrado.


## Futuras Mejoras
- Migración a nuevas tecnologías.
- Documentación mejorada.
- Mejora de UI.
- Integración de tests automatizados.
- Dockerización y despliegue continuo.
