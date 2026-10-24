package umg.nomina.app;

import umg.nomina.app.service.GestorNomina;
import umg.nomina.app.utils.Utils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorNomina gestor = new GestorNomina(solicitarMes(scanner));
        int opcion = 0;

        do {
            Utils.limpiarPantalla();
            System.out.println("NOMINA DEL MES: " + gestor.getNombreMes());
            System.out.println("1. Ingreso de nomina");
            System.out.println("2. Impresion de nomina");
            System.out.println("3. Cambiar de mes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = Utils.leerEntero(scanner);
            switch (opcion) {
                case 1:
                    menuIngresoEmpleados(scanner, gestor);
                    break;
                case 2:
                    gestor.imprimirNomina(scanner);
                    break;
                case 3:
                    gestor.setMesActual(solicitarMes(scanner));
                    break;
                case 4:
                    System.out.println("\nGracias por utilizar el sistema de nomina. Hasta pronto!");
                    break;
                default:
                    System.out.println("\n[Error] Opcion invalida. Seleccione una opcion entre 1 y 4.");
                    Utils.pausar(scanner);
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static int solicitarMes(Scanner scanner) {
        while (true) {
            Utils.limpiarPantalla();
            System.out.println("=== CALCULO DE NOMINA ===");
            System.out.print("Ingrese el mes a trabajar (1-12): ");

            int mes = Utils.leerEntero(scanner);
            if (mes >= 1 && mes <= 12) {
                return mes;
            }
            System.out.println("\n[Error] Debe ingresar un numero entero entre 1 y 12.");
            Utils.pausar(scanner);
        }
    }

    private static void menuIngresoEmpleados(Scanner scanner, GestorNomina gestor) {
        while (true) {
            Utils.limpiarPantalla();
            System.out.println("NOMINA DEL MES: " + gestor.getNombreMes());
            System.out.println("INGRESO DE EMPLEADO A NOMINA");
            System.out.println("Trabajadores actuales = " + gestor.getTotalEmpleados());
            System.out.println("1. Empleado fijo");
            System.out.println("2. Empleado por hora");
            System.out.println("3. Volver al menu principal");
            System.out.print("Seleccione el tipo de empleado: ");

            int tipo = Utils.leerEntero(scanner);
            if (tipo == 3) {
                return;
            }
            if (tipo == 1 || tipo == 2) {
                gestor.registrarEmpleado(scanner, tipo);
                if (!deseaContinuarIngreso(scanner, gestor)) {
                    return;
                }
            } else {
                System.out.println("\n[Error] Opcion invalida.");
                Utils.pausar(scanner);
            }
        }
    }

    private static boolean deseaContinuarIngreso(Scanner scanner, GestorNomina gestor) {
        System.out.println("\nEmpleado agregado correctamente.");
        System.out.println("Trabajadores actuales = " + gestor.getTotalEmpleados());
        System.out.println("Desea ingresar otro empleado?");
        System.out.println("1. Si, ingresar otro empleado");
        System.out.println("2. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");
        return Utils.leerOpcionBinaria(scanner) == 1;
    }
}
