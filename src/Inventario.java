public class Inventario {
    private Producto[] productos;
    private int contador;

    public Inventario(int tamaño) {
        productos = new Producto[tamaño];
        contador = 0;
    }

    public void agregarProducto(Producto producto) {
        if (contador < productos.length) {
            productos[contador++] = producto;
            System.out.println("Producto agregado exitosamente.");
        } else {
            System.out.println("Inventario lleno.");
        }
    }

    public void mostrarProductos() {
        for (int i = 0; i < contador; i++) {
            productos[i].mostrarInformacion();
            System.out.println("-----------");
        }
    }

    public boolean eliminarProducto(int id) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getId() == id) {
                productos[i] = productos[--contador];
                System.out.println("Producto eliminado.");
                return true;
            }
        }
        System.out.println("Producto no encontrado.");
        return false;
    }

    public Producto buscarProducto(int id) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getId() == id) return productos[i];
        }
        return null;
    }
}