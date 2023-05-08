import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.awt.Dimension;
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
        this.items = new ArrayList<Producto>();
    }

    public void ingresarDatosFactura() {

        String numeroFactura = JOptionPane.showInputDialog(null, "Ingrese el número de factura:");
        String nombreCliente = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente:");
        String fechaFactura = JOptionPane.showInputDialog(null, "Ingrese la fecha de la factura (DD/MM/AAAA):");

        if (numeroFactura.equals("") || nombreCliente.equals("") || fechaFactura.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos de la factura.");
            return;
        }

        // Convertir la fecha a un objeto de tipo Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha;
        try {
            fecha = dateFormat.parse(fechaFactura);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "La fecha ingresada no tiene un formato válido. Por favor, ingrese la fecha en formato DD/MM/AAAA.");
            return;
        }

        // Asignar los datos de la factura al objeto Factura
        this.numeroFactura = numeroFactura;
        this.nombreCliente = nombreCliente;

        this.fechaFactura = fecha;
        this.items = new ArrayList<Producto>();
    }

    

    public void agregarProducto() {
        if (this.numeroFactura == null || this.fechaFactura == null || this.nombreCliente == null || this.items == null) {
            System.out.println("Por favor, ingrese primero los datos de la factura antes de agregar un producto.");
            return;
        }

        String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        double precioProducto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto:"));
        int cantidadProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto:"));

        Producto producto = new Producto(nombreProducto, precioProducto, cantidadProducto);
        this.items.add(producto);
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


    public double calcularTotal() {
        double total = 0;
        for (Producto item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
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
    public void verPreliminar() {
        if (this.numeroFactura == null || this.fechaFactura == null || this.nombreCliente == null || this.items == null) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese los datos de la factura antes de visualizar la previsualización.");
            return;
        }
    
        String textoFactura = "============================================\n";
        textoFactura += "                 FACTURA N° " + this.numeroFactura + "\n";
        textoFactura += "============================================\n";
        textoFactura += "Fecha: " + this.fechaFactura + "\n";
        textoFactura += "Cliente: " + this.nombreCliente + "\n\n";
        textoFactura += "--------------------------------------------\n";
        textoFactura += "Cantidad   Producto            Precio unit.\n";
        textoFactura += "--------------------------------------------\n";
    
        double total = 0.0;
        for (Producto producto : this.items) {
            textoFactura += String.format("%-11d%-20s%-11.2f\n", producto.getCantidad(), producto.getDescripcion(), producto.getPrecioUnitario());
            total += producto.getCantidad() * producto.getPrecioUnitario();
        }
    
        textoFactura += "--------------------------------------------\n";
        textoFactura += String.format("Total: $%.2f\n", total);
        textoFactura += "============================================\n";
    
        JTextArea textArea = new JTextArea(textoFactura);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 500));
    
        JOptionPane.showMessageDialog(null, scrollPane, "Previsualización de factura", JOptionPane.PLAIN_MESSAGE);
    }
    

    public String toString() {
        String factura = "Factura No. " + this.numeroFactura + "\n";
        factura += "Fecha: " + this.fechaFactura.toString() + "\n";
        factura += "Cliente: " + this.nombreCliente + "\n\n";

        factura += "Productos:\n";
        factura += "----------------------------------------\n";
        factura += "Nombre\t\tPrecio\t\tCantidad\n";
        factura += "----------------------------------------\n";

        double total = 0.0;

        for (Producto producto : this.items) {
            factura += producto.getDescripcion() + "\t\t" + producto.getPrecioUnitario() + "\t\t" + producto.getCantidad() + "\n";
            total += producto.getPrecioUnitario() * producto.getCantidad();
        }

        factura += "\nTotal: " + total;

        return factura;
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

