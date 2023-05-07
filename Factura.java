import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.FileWriter;
import java.io.IOException;
public class Factura {
    private String numeroFactura;
    private Date fechaFactura;
    private String nombreCliente;
    private ArrayList<Producto> items = new ArrayList<Producto>();

    public Factura(String numeroFactura, String nombreCliente, Date fechaFactura) {
        this.numeroFactura = numeroFactura;
        this.nombreCliente = nombreCliente;
        this.fechaFactura = fechaFactura;
    }
    public Factura() {

    }

    public void ingresarDatosFactura() {
        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese el número de factura: ");
        String numeroFactura = input.nextLine();
        this.numeroFactura = numeroFactura;
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = input.nextLine();
        this.nombreCliente = nombreCliente;
        System.out.print("Ingrese la fecha de factura (en formato dd/mm/aaaa): ");
        String fechaStr = input.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaFactura = formatter.parse(fechaStr);
            this.fechaFactura = fechaFactura;
        } catch (ParseException e) {
            System.out.println("Error al ingresar la fecha. Formato debe ser dd/mm/aaaa.");
        }
    }

    

    public void agregarProducto() {
        AgregarProductoDialog agregarProductoDialog = new AgregarProductoDialog(this);
        agregarProductoDialog.setVisible(true);
        if (agregarProductoDialog.getProducto() != null) {
            Producto producto = agregarProductoDialog.getProducto();
            this.items.add(producto);
            JOptionPane.showMessageDialog(null, "Producto agregado con éxito");
        }

    }
    public boolean estaVacia() {
        return items.isEmpty();
    }
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public ArrayList<Producto> getItems() {
        return items;
    }

    public void setItems(ArrayList<Producto> items) {
        this.items = items;
    }
    public double getPrecioTotal() {
        double total = 0.0;
        for (Producto producto : this.items) {
            total += producto.getPrecioUnitario();
        }
        return total;
    }

    /*public double calcularTotal() {
        double total = 0;
        for (Producto item : items) {
            total += item.getSubtotal();
        }
        return total;
    }*/
    public void guardarFactura(String nombreArchivo) {
        try {
            FileWriter writer = new FileWriter(nombreArchivo);
            writer.write("NOMBRE_CLIENTE:" + nombreCliente + "\n");
            writer.write("FECHA:" + fechaFactura.toString() + "\n");
            writer.write("ITEMS:\n");
            for (Producto producto : items) {
                writer.write(producto.toString() + "\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Factura guardada con éxito en " + nombreArchivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la factura");
        }
    }


    public String toString() {
        String facturaStr = "";
        facturaStr += "Factura #" + numeroFactura + "\n";
        facturaStr += "Fecha: " + fechaFactura + "\n";
        facturaStr += "Cliente: " + nombreCliente + "\n";
        facturaStr += "---------------------------------------\n";
        facturaStr += "Item                 Cantidad     Precio\n";
        facturaStr += "---------------------------------------\n";
        for (Producto item : items) {
            facturaStr += item + "\n";
        }
        facturaStr += "---------------------------------------\n";
        facturaStr += "Total: $" + getPrecioTotal() + "\n";
        return facturaStr;
    }
}

class Producto {
    private String descripcion;
    private double precioUnitario;
    private int cantidad;

    public Producto(String descripcion, double precioUnitario, int cantidad) {
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return cantidad * precioUnitario;
    }

    public double getPrecioTotal() {
        return precioUnitario * cantidad;
    }
    public String toString() {
        String itemStr = "";
        itemStr += this.descripcion + "                  ";
        itemStr += this.cantidad + "        ";
        itemStr += "$" + this.precioUnitario + "\n";        
        return itemStr;
    }
}

