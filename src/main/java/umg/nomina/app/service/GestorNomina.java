package umg.nomina.app.service;

import umg.nomina.app.entity.Empleado;
import umg.nomina.app.entity.EmpleadoFijo;
import umg.nomina.app.entity.EmpleadoPorHora;
import umg.nomina.app.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GestorNomina {

    private static final String[] MESES = {
            "Enero", "Febrero", "Marzo", "Abril",
            "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    private final Map<Integer, List<Empleado>> nominasPorMes = new HashMap<>();
    private int mesActual;

    public GestorNomina(int numeroMes) {
        setMesActual(numeroMes);
    }

    public void setMesActual(int numeroMes) {
        this.mesActual = numeroMes;
        nominasPorMes.putIfAbsent(numeroMes, new ArrayList<>());
    }

    public String getNombreMes() {
        return MESES[mesActual - 1].toUpperCase();
    }

    private List<Empleado> getEmpleadosMesActual() {
        return nominasPorMes.get(mesActual);
    }

    public int getTotalEmpleados() {
        return getEmpleadosMesActual().size();
    }

    public void registrarEmpleado(Scanner scanner, int tipo) {
        Utils.limpiarPantalla();
        System.out.println("NOMINA DEL MES: " + getNombreMes());
        System.out.println(tipo == 1 ? "INGRESO DE EMPLEADO FIJO" : "INGRESO DE EMPLEADO POR HORA");

        String dpi = Utils.leerDpiValido(scanner);
        String nombre = Utils.leerNombreValido(scanner);

        Empleado nuevoEmpleado;
        if (tipo == 1) {
            double sueldo = Utils.leerNumeroPositivo(scanner, "Sueldo mensual (Q): ");
            double bonificacion = Utils.leerNumeroPositivo(scanner, "Bonificacion de este mes (Q): ");
            nuevoEmpleado = new EmpleadoFijo(dpi, nombre, sueldo, bonificacion);
        } else {
            double pagoHora = Utils.leerNumeroPositivo(scanner, "Pago por hora (Q): ");
            double horas = Utils.leerNumeroPositivo(scanner, "Horas trabajadas este mes: ");
            nuevoEmpleado = new EmpleadoPorHora(dpi, nombre, pagoHora, horas);
        }

        getEmpleadosMesActual().add(nuevoEmpleado);
        System.out.printf("\nPago de %s = Q %.2f\n", nuevoEmpleado.getNombre(), nuevoEmpleado.calcularPago());
    }

    public void imprimirNomina(Scanner scanner) {
        Utils.limpiarPantalla();
        System.out.println("NOMINA DEL MES: " + getNombreMes());

        List<Empleado> listaEmpleados = getEmpleadosMesActual();

        if (listaEmpleados.isEmpty()) {
            System.out.println("\nNo hay empleados registrados en la nomina de este mes.");
            System.out.println("Total de trabajadores: 0");
            System.out.println("Total de nomina: Q 0.00");
        } else {
            System.out.printf("%-15s %-20s %-12s %12s\n", "DPI", "Nombre", "Tipo", "Pago (Q)");
            System.out.println("---------------------------------------------------------------");

            double totalNomina = 0.0;
            for (Empleado emp : listaEmpleados) {
                double pagoActual = emp.calcularPago();
                totalNomina += pagoActual;

                System.out.printf("%-15s %-20s %-12s %12.2f\n",
                        emp.getDpi(),
                        emp.getNombre(),
                        emp.obtenerTipo(),
                        pagoActual);
            }

            System.out.println("---------------------------------------------------------------");
            System.out.println("Total de trabajadores: " + listaEmpleados.size());
            System.out.printf("Total de nomina: Q %.2f\n", totalNomina);
        }

        Utils.pausar(scanner);
    }
}
