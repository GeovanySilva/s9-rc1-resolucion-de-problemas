package interfaz;

import java.util.Scanner;
import modelo.Movimiento;
import modelo.Producto;
import modelo.TipoMovimiento;
import negocio.Inventario;

public class Consola {
    private final Inventario inventario = new Inventario();
    private final Scanner entrada;

    public Consola(Scanner entrada) { this.entrada = entrada; }

    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            new Consola(entrada).ejecutar();
        }
    }
    public void ejecutar() {
        while (entrada.hasNextLine()) {
            System.out.println("1 Alta | 2 Entrada | 3 Salida | 4 Existencias | 5 Alertas | 6 Historial | 0 Salir");
            try {
                switch (numero("Opción")) {
                    case 0: return;
                    case 1:
                        inventario.registrar(new Producto(texto("Código"), texto("Nombre"),
                                numero("Existencias iniciales"), numero("Umbral")));
                        System.out.println("Producto registrado.");
                        break;
                    case 2: mover(TipoMovimiento.ENTRADA); break;
                    case 3: mover(TipoMovimiento.SALIDA); break;
                    case 4:
                        for (Producto producto : inventario.productos()) mostrar(producto);
                        break;
                    case 5:
                        if (inventario.alertas().isEmpty()) System.out.println("Sin alertas.");
                        for (Producto producto : inventario.alertas()) mostrar(producto);
                        break;
                    case 6:
                        for (Movimiento mov : inventario.movimientos())
                            System.out.printf("%s | %s | %s | %d | saldo: %d%n",
                                    mov.fecha(), mov.producto().codigo(), mov.tipo(), mov.unidades(), mov.saldo());
                        break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (java.util.NoSuchElementException e) {
                return;
            }
        }
    }
    private void mover(TipoMovimiento tipo) {
        inventario.mover(texto("Código"), tipo, numero("Unidades"));
        System.out.println("Movimiento registrado.");
    }
    private void mostrar(Producto producto) {
        System.out.printf("%s | %s | existencias: %d | umbral: %d%n",
                producto.codigo(), producto.nombre(), producto.existencias(), producto.minimo());
    }
    private String texto(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return entrada.nextLine().trim();
    }
    private int numero(String etiqueta) {
        try {
            return Integer.parseInt(texto(etiqueta));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ingrese un número entero.");
        }
    }
}