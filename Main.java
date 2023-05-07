import java.util.Date;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Factura factura = new Factura();

        boolean seguir = true;
        while (seguir) {
            System.out.println("\n¿Qué acción desea realizar?");
            System.out.println("1. Ingresar datos de la factura");
            System.out.println("2. Agregar producto");
            System.out.println("3. Ver factura");
            System.out.println("4. Guardar factura en archivo");
            System.out.println("5. Salir");

            int opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    factura.ingresarDatosFactura();
                    break;
                case 2:
                    factura.agregarProducto();
                    break;
                case 3:
                    System.out.println(factura);
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del archivo: ");
                    String nombreArchivo = input.next();
                    factura.guardarFactura(nombreArchivo);
                    break;
                case 5:
                    seguir = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        }
    }
}

