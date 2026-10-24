package umg.nomina.app.utils;

import java.util.Scanner;

public class Utils {

    public static void limpiarPantalla() {
        try {
            String sistemaOperativo = System.getProperty("os.name").toLowerCase();
            if (sistemaOperativo.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void pausar(Scanner scanner) {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    public static int leerEntero(Scanner scanner) {
        if (scanner.hasNextInt()) {
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        }
        scanner.nextLine();
        return -1;
    }

    public static int leerOpcionBinaria(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int op = scanner.nextInt();
                scanner.nextLine();
                if (op == 1 || op == 2) {
                    return op;
                }
                System.out.print("[Error] Seleccione 1 o 2: ");
            } else {
                System.out.print("[Error] Ingrese una opcion valida: ");
                scanner.nextLine();
            }
        }
    }

    public static double leerNumeroPositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                if (valor > 0 && !Double.isInfinite(valor) && !Double.isNaN(valor)) {
                    return valor;
                }
                System.out.println("[Error] El valor debe ser mayor a 0.");
            } else {
                System.out.println("[Error] Entrada invalida. Ingrese un valor numerico.");
                scanner.nextLine();
            }
        }
    }

    public static String leerDpiValido(Scanner scanner) {
        String dpi = "";
        while (true) {
            System.out.print("DPI (13 digitos): ");
            dpi = scanner.nextLine().trim();
            if (dpi.matches("\\d{13}")) {
                return dpi;
            }
            System.out.println("[Error] El DPI debe contener exactamente 13 digitos numericos.");
        }
    }

    public static String leerNombreValido(Scanner scanner) {
        while (true) {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("[Error] El nombre no puede estar vacio.");
            } else if (nombre.length() < 2 || nombre.length() > 255) {
                System.out.println("[Error] El nombre debe tener entre 2 y 255 caracteres.");
            } else if (!nombre.matches("[a-zA-Z ]+")) {
                System.out.println("[Error] El nombre solo puede contener letras y espacios.");
            } else {
                return nombre;
            }
        }
    }
}
