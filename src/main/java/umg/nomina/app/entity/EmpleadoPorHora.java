package umg.nomina.app.entity;

public class EmpleadoPorHora extends Empleado {

    private double pagoPorHora;
    private double horasTrabajadas;

    public EmpleadoPorHora(String dpi, String name, double pagoPorHora, double horasTrabajadas) {
        super(dpi, name);
        this.pagoPorHora = pagoPorHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularPago() {
        return this.pagoPorHora * this.horasTrabajadas;
    }

    @Override
    public String obtenerTipo() {
        return "Por hora";
    }

    public double getPagoPorHora() {
        return pagoPorHora;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }
}
