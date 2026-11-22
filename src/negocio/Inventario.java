package negocio;

import java.util.ArrayList;
import modelo.Movimiento;
import modelo.Producto;
import modelo.TipoMovimiento;

public class Inventario {
    private final ArrayList<Producto> productos = new ArrayList<>();
    private final ArrayList<Movimiento> movimientos = new ArrayList<>();

    public void registrar(Producto producto) {
        if (buscar(producto.codigo()) != null) throw new IllegalArgumentException("Código duplicado.");
        productos.add(producto);
    }
    public Producto buscar(String codigo) {
        for (Producto producto : productos) {
            if (producto.codigo().equalsIgnoreCase(codigo)) return producto;
        }
        return null;
    }
    public void mover(String codigo, TipoMovimiento tipo, int unidades) {
        Producto producto = buscar(codigo);
        if (producto == null) throw new IllegalArgumentException("Producto inexistente.");
        producto.aplicar(tipo, unidades);
        movimientos.add(new Movimiento(producto, tipo, unidades));
    }
    public ArrayList<Producto> alertas() {
        ArrayList<Producto> resultado = new ArrayList<>();
        for (Producto producto : productos)
            if (producto.requiereReposicion()) resultado.add(producto);
        return resultado;
    }
    public ArrayList<Producto> productos() { return new ArrayList<>(productos); }
    public ArrayList<Movimiento> movimientos() { return new ArrayList<>(movimientos); }
}