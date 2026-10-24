package umg.nomina.app.entity;

public abstract class Empleado {

    private String dpi;
    private String nombre;

    public Empleado(String dpi, String name) {
        this.dpi = dpi;
        this.nombre = name;
    }

    public String getDpi() {
        return dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularPago();
    public abstract String obtenerTipo();
}
