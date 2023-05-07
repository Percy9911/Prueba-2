
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
    private JTextField precioTextField; // Agregar este campo
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
        JLabel precioLabel = new JLabel("Precio:");
        precioTextField = new JTextField(); // Agregar este campo
        agregarButton = new JButton("Agregar");
        cancelarButton = new JButton("Cancelar");

        // Configurar el layout
        JPanel panel = new JPanel(new GridLayout(5, 2)); // Cambiar el GridLayout
        panel.add(descripcionLabel);
        panel.add(descripcionTextField);
        panel.add(precioUnitarioLabel);
        panel.add(precioUnitarioTextField);
        panel.add(cantidadLabel);
        panel.add(cantidadTextField);
        panel.add(precioLabel);
        panel.add(precioTextField); // Agregar este campo
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
        setSize(300, 180); // Ajustar el tamaño
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public Producto getProducto() {
        String descripcion = descripcionTextField.getText();
        String precioTexto = precioTextField.getText();
        String cantidadTexto = cantidadTextField.getText();
        
        // Validar que los campos no estén vacíos
        if (descripcion.isEmpty() || precioTexto.isEmpty() || cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
            return null;
        }
        
        double precio = 0.0;
        int cantidad = 0;
        
        try {
            precio = Double.parseDouble(precioTexto);
            cantidad = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos para el precio y la cantidad.");
            return null;
        }
        
        Producto producto = new Producto(descripcion, precio, cantidad);
        return producto;
    }
    
    private void agregarProducto() {
        String descripcion = descripcionTextField.getText();
        double precioUnitario = Double.parseDouble(precioUnitarioTextField.getText());
        int cantidad = Integer.parseInt(cantidadTextField.getText());
        Producto producto = new Producto(descripcion, precioUnitario, cantidad);
        factura.getItems().add(producto);
        dispose();
    }
}

