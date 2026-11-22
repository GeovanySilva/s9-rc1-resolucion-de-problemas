package modelo;

public class Producto {
    private final String codigo, nombre;
    private final int minimo;
    private int existencias;

    public Producto(String codigo, String nombre, int existencias, int minimo) {
        if (codigo.isBlank() || nombre.isBlank() || existencias < 0 || minimo < 0)
            throw new IllegalArgumentException("Complete los datos y use cantidades no negativas.");
        this.codigo = codigo;
        this.nombre = nombre;
        this.existencias = existencias;
        this.minimo = minimo;
    }
    public String codigo() { return codigo; }
    public String nombre() { return nombre; }
    public int existencias() { return existencias; }
    public int minimo() { return minimo; }
    public boolean requiereReposicion() { return existencias <= minimo; }
    public void aplicar(TipoMovimiento tipo, int unidades) {
        if (unidades <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva.");
        if (tipo == TipoMovimiento.SALIDA) {
            if (unidades > existencias) throw new IllegalArgumentException("Existencias insuficientes.");
            existencias -= unidades;
        } else {
            if (unidades > Integer.MAX_VALUE - existencias)
                throw new IllegalArgumentException("La entrada supera la capacidad numérica.");
            existencias += unidades;
        }
    }
}