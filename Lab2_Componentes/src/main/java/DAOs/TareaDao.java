package DAOs;

import Model.TareaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class TareaDao {
    private Connection connection;

    public TareaDao(Connection connection) {
        this.connection = connection;
    }

    public void agregarTarea(TareaModel tarea) throws SQLException {
        String query = "INSERT INTO `JA_DR_AP_tareas`(`nombre`, `descripcion`, `prioridad`, `fechaVencimiento`) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tarea.getNombre());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setInt(3, tarea.getPrioridad());
            stmt.setDate(4, new Date(tarea.getFechaVencimiento().getTime()));
            stmt.executeUpdate();
        }
    }

    public void actualizarTarea(TareaModel tarea) throws SQLException {
        String query = "UPDATE `JA_DR_AP_tareas` SET `nombre` = ?, `descripcion` = ?, `prioridad` = ?, `fechaVencimiento` = ? WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tarea.getNombre());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setInt(3, tarea.getPrioridad());
            stmt.setDate(4, new Date(tarea.getFechaVencimiento().getTime()));
            stmt.setInt(5, tarea.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarTarea(int id) throws SQLException {
        String query = "DELETE FROM `JA_DR_AP_tareas` WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public TareaModel consultarTarea(int id) throws SQLException {
        String query = "SELECT `id`, `nombre`, `descripcion`, `prioridad`, `fechaVencimiento` FROM `JA_DR_AP_tareas` WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int tareaId = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    int prioridad = rs.getInt("prioridad");
                    Date fechaVencimiento = rs.getDate("fechaVencimiento");
                    return new TareaModel(tareaId, nombre, descripcion, prioridad, fechaVencimiento);
                } else {
                    return null;
                }
            }
        }
    }
}
