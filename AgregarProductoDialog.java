import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AgregarProductoDialog extends JDialog {
    private JTextField descripcionTextField;
    private JTextField precioUnitarioTextField;
    private JTextField cantidadTextField;
    private JButton agregarButton;
    private JButton cancelarButton;

    private Factura factura;

    public AgregarProductoDialog(Factura factura) {
        super();
        this.factura = factura;
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionTextField = new JTextField();
        JLabel precioUnitarioLabel = new JLabel("Precio unitario:");
        precioUnitarioTextField = new JTextField();
        JLabel cantidadLabel = new JLabel("Cantidad:");
        cantidadTextField = new JTextField();
        agregarButton = new JButton("Agregar");
        cancelarButton = new JButton("Cancelar");

        // Configurar el layout
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(descripcionLabel);
        panel.add(descripcionTextField);
        panel.add(precioUnitarioLabel);
        panel.add(precioUnitarioTextField);
        panel.add(cantidadLabel);
        panel.add(cantidadTextField);
        panel.add(agregarButton);
        panel.add(cancelarButton);

        // Agregar los escuchadores de eventos
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Configurar el diálogo
        add(panel);
        setTitle("Agregar Producto");
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Producto getProducto() {
        String descripcion = descripcionTextField.getText();
        String precioUnitarioTexto = precioUnitarioTextField.getText();
        String cantidadTexto = cantidadTextField.getText();

        // Validar que los campos no estén vacíos
        if (descripcion.isEmpty() || precioUnitarioTexto.isEmpty() || cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
            return null;
        }

        double precioUnitario = 0.0;
        int cantidad = 0;

        try {
            precioUnitario = Double.parseDouble(precioUnitarioTexto);
            cantidad = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos para el precio unitario y la cantidad.");
            return null;
        }

        Producto producto = new Producto(descripcion, precioUnitario, cantidad);
        return producto;
    }

    private void agregarProducto() {
        
        Producto producto = getProducto();
        if (producto != null) {
            factura.getItems().add(producto);
            double precio = producto.getPrecioUnitario() * producto.getCantidad();
            JOptionPane.showMessageDialog(null, "El precio total del producto es: $" + precio);
            dispose();
        }
    }
}
