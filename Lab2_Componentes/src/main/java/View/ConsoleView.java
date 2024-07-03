package View;

import Model.TareaModel;

public class ConsoleView {

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void errorMessage(String message) {
        System.err.println(message);
    }

    public void showTarea(TareaModel tarea) {
        System.out.println("ID: " + tarea.getId());
        System.out.println("Nombre: " + tarea.getNombre());
        System.out.println("Descripción: " + tarea.getDescripcion());
        System.out.println("Prioridad: " + tarea.getPrioridad());
        System.out.println("Fecha de Vencimiento: " + tarea.getFechaVencimiento());
    }
}
