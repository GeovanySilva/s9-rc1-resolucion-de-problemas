package modelo;

import java.time.LocalDateTime;

public class Movimiento {
    private final Producto producto;
    private final TipoMovimiento tipo;
    private final int unidades, saldo;
    private final LocalDateTime fecha;

    public Movimiento(Producto producto, TipoMovimiento tipo, int unidades) {
        this.producto = producto;
        this.tipo = tipo;
        this.unidades = unidades;
        this.saldo = producto.existencias();
        this.fecha = LocalDateTime.now();
    }
    public Producto producto() { return producto; }
    public TipoMovimiento tipo() { return tipo; }
    public int unidades() { return unidades; }
    public int saldo() { return saldo; }
    public LocalDateTime fecha() { return fecha; }
}