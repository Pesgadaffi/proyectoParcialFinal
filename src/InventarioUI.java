import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.URI;

public class InventarioUI {
    private Inventario inventario = new Inventario(100);

    public InventarioUI() {
        JFrame frame = new JFrame("Sistema de Inventario");
        frame.setSize(520, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblTitulo = new JLabel("Inventario - Agregar Producto");
        lblTitulo.setBounds(150, 10, 250, 20);

        JTextField txtId = new JTextField();
        txtId.setBounds(150, 40, 200, 20);
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 40, 100, 20);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(150, 70, 200, 20);
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 70, 100, 20);

        JTextField txtCantidad = new JTextField();
        txtCantidad.setBounds(150, 100, 200, 20);
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 100, 100, 20);

        JTextField txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 130, 200, 20);
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 130, 100, 20);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(150, 160, 180, 30);

        JButton btnMostrar = new JButton("Mostrar Productos");
        btnMostrar.setBounds(150, 200, 180, 30);

        JButton btnBuscar = new JButton("Buscar por ID");
        btnBuscar.setBounds(150, 240, 180, 30);

        JButton btnEliminar = new JButton("Eliminar por ID");
        btnEliminar.setBounds(150, 280, 180, 30);

        JButton btnEaster = new JButton("🔍");
        btnEaster.setBounds(460, 10, 45, 25);

        btnAgregar.addActionListener(e -> {
            try {
                String idText = txtId.getText().trim();
                String nombre = txtNombre.getText().trim();
                String cantidadText = txtCantidad.getText().trim();
                String precioText = txtPrecio.getText().trim();

                // Easter egg check
                if (idText.equals("perfect") && nombre.equals("perfect") && cantidadText.equals("perfect") && precioText.equals("perfect")) {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=IJxv-9flH8E"));
                    return;
                }

                int id = Integer.parseInt(idText);
                int cantidad = Integer.parseInt(cantidadText);
                double precio = Double.parseDouble(precioText);

                Producto nuevo = new Producto(id, nombre, cantidad, precio);
                inventario.agregarProducto(nuevo);

                JOptionPane.showMessageDialog(frame, "Producto agregado exitosamente.");
                txtId.setText(""); txtNombre.setText(""); txtCantidad.setText(""); txtPrecio.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Entrada inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnMostrar.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            Producto[] lista = inventario.getProductos();
            for (Producto p : lista) {
                if (p != null) {
                    sb.append("ID: ").append(p.getId())
                      .append(", Nombre: ").append(p.getNombre())
                      .append(", Cantidad: ").append(p.getCantidad())
                      .append(", Precio: $").append(p.getPrecio())
                      .append("\n");
                }
            }
            JOptionPane.showMessageDialog(frame, sb.length() > 0 ? sb.toString() : "Inventario vacío.");
        });

        btnBuscar.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Ingrese ID a buscar:");
            try {
                int id = Integer.parseInt(input);
                Producto p = inventario.buscarProducto(id);
                if (p != null) {
                    JOptionPane.showMessageDialog(frame, p.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Producto no encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID inválido.");
            }
        });

        btnEliminar.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Ingrese ID a eliminar:");
            try {
                int id = Integer.parseInt(input);
                boolean eliminado = inventario.eliminarProducto(id);
                String msg = eliminado ? "Producto eliminado." : "No se encontró el producto.";
                JOptionPane.showMessageDialog(frame, msg);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID inválido.");
            }
        });

        btnEaster.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "¿Buscas algo especial?");
        });

        frame.add(lblTitulo);
        frame.add(lblId); frame.add(txtId);
        frame.add(lblNombre); frame.add(txtNombre);
        frame.add(lblCantidad); frame.add(txtCantidad);
        frame.add(lblPrecio); frame.add(txtPrecio);
        frame.add(btnAgregar); frame.add(btnMostrar);
        frame.add(btnBuscar); frame.add(btnEliminar);
        frame.add(btnEaster);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new InventarioUI();
    }
}
