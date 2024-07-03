package Controller;

import ConexionSql.conexion;
import DAOs.TareaDao;
import Model.TareaModel;
import View.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class TareaController {

    private ConsoleView viewConsole;
    private TareaDao tareaDAO;

    public TareaController(ConsoleView viewConsole) {
        this.viewConsole = viewConsole;
        Connection connection = conexion.getConnection();
        this.tareaDAO = new TareaDao(connection);
    }

    public void agregarTarea(String nombre, String descripcion, int prioridad, Date fechaVencimiento) {
        TareaModel tarea = new TareaModel(nombre, descripcion, prioridad, fechaVencimiento);
        try {
            tareaDAO.agregarTarea(tarea);
            viewConsole.showMessage("Tarea agregada correctamente");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al agregar tarea: " + e.getMessage());
        }
    }

    public void actualizarTarea(int id, String nombre, String descripcion, int prioridad, Date fechaVencimiento) {
        TareaModel tarea = new TareaModel(id, nombre, descripcion, prioridad, fechaVencimiento);
        try {
            tareaDAO.actualizarTarea(tarea);
            viewConsole.showMessage("Tarea actualizada correctamente");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar tarea: " + e.getMessage());
        }
    }

    public void eliminarTarea(int id) {
        try {
            tareaDAO.eliminarTarea(id);
            viewConsole.showMessage("Tarea eliminada correctamente");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar tarea: " + e.getMessage());
        }
    }

    public void consultarTarea(int id) {
        try {
            TareaModel tarea = tareaDAO.consultarTarea(id);
            if (tarea != null) {
                viewConsole.showTarea(tarea);
            } else {
                viewConsole.showMessage("Tarea no encontrada");
            }
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al consultar tarea: " + e.getMessage());
        }
    }
}
