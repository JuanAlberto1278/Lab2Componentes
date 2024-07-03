package org.example;

import Controller.TareaController;
import View.ConsoleView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleView viewConsole = new ConsoleView();
        TareaController tareaController = new TareaController(viewConsole);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú Principal:");
            System.out.println("1. Tarea");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int opcionPrincipal = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcionPrincipal == 1) {
                menuTarea(scanner, tareaController);
            } else if (opcionPrincipal == 2) {
                System.out.println("Saliendo del programa...");
                scanner.close();
                return;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void menuTarea(Scanner scanner, TareaController tareaController) {
        while (true) {
            System.out.println("Menú Tarea:");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Actualizar Tarea");
            System.out.println("3. Eliminar Tarea");
            System.out.println("4. Consultar Tarea");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la tarea: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese la prioridad de la tarea (1 a 5): ");
                    int prioridad = scanner.nextInt();
                    System.out.print("Ingrese la fecha de vencimiento (yyyy-MM-dd): ");
                    String fechaStr = scanner.next();
                    scanner.nextLine(); // Consumir el salto de línea
                    Date fechaVencimiento = null;
                    try {
                        fechaVencimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
                    } catch (ParseException e) {
                        System.out.println("Formato de fecha no válido.");
                        break;
                    }
                    tareaController.agregarTarea(nombre, descripcion, prioridad, fechaVencimiento);
                    break;
                case 2:
                    System.out.print("Ingrese el ID de la tarea a actualizar: ");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.print("Ingrese el nuevo nombre de la tarea: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Ingrese la nueva descripción de la tarea: ");
                    String nuevaDescripcion = scanner.nextLine();
                    System.out.print("Ingrese la nueva prioridad de la tarea (1 a 5): ");
                    int nuevaPrioridad = scanner.nextInt();
                    System.out.print("Ingrese la nueva fecha de vencimiento (yyyy-MM-dd): ");
                    String nuevaFechaStr = scanner.next();
                    scanner.nextLine(); // Consumir el salto de línea
                    Date nuevaFechaVencimiento = null;
                    try {
                        nuevaFechaVencimiento = new SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaStr);
                    } catch (ParseException e) {
                        System.out.println("Formato de fecha no válido.");
                        break;
                    }
                    tareaController.actualizarTarea(idActualizar, nuevoNombre, nuevaDescripcion, nuevaPrioridad, nuevaFechaVencimiento);
                    break;
                case 3:
                    System.out.print("Ingrese el ID de la tarea a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    tareaController.eliminarTarea(idEliminar);
                    break;
                case 4:
                    System.out.print("Ingrese el ID de la tarea a consultar: ");
                    int idConsultar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    tareaController.consultarTarea(idConsultar);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}