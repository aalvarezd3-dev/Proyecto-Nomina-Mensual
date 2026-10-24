package umg.nomina.app.entity;

public class EmpleadoFijo extends Empleado {

    private double sueldoMensual;
    private double bonificacion;

    public EmpleadoFijo(String dpi, String name, double sueldoMensual, double bonificacion) {
        super(dpi, name);
        this.sueldoMensual = sueldoMensual;
        this.bonificacion = bonificacion;
    }

    @Override
    public double calcularPago() {
        return this.sueldoMensual+this.bonificacion;
    }

    @Override
    public String obtenerTipo() {
        return "Fijo";
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public double getBonificacion() {
        return bonificacion;
    }

}
