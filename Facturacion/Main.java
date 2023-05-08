import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ArrayList<Sede> sedes = new ArrayList<>();
        sedes.add(new Sede("Sede A", 1));
        sedes.add(new Sede("Sede B", 2));

        RegistroUsuarios registro = new RegistroUsuarios(sedes);

        boolean seguir = true;
        while (seguir) {
            int opcion = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?", "Facturación",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                    new String[] { "Registrar una nueva cuenta", "Iniciar sesión", "Salir" }, null);

            switch (opcion) {
                case 0:
                    registro.agregarUsuario();
                    break;
                case 1:
                    Usuario usuarioAutenticado = registro.iniciarSesion();
                    if (usuarioAutenticado == null) {
                        JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión. Inténtelo de nuevo.");
                    } else {
                        Factura factura = new Factura();
                        boolean continuar = true;
                        while (continuar) {
                            int opcionFactura = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?",
                                    "Facturación", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                    new String[] { "Ingresar datos de la factura", "Agregar producto",
                                            "Ver factura preliminar", "Ver factura final", "Guardar factura en archivo",
                                            "Salir" }, null);

                            switch (opcionFactura) {
                                case 0:
                                    factura.ingresarDatosFactura();
                                    break;
                                case 1:
                                    factura.agregarProducto();
                                    break;
                                case 2:
                                    factura.verPreliminar();
                                    break;
                                case 3:
                                    JOptionPane.showMessageDialog(null, factura.toString());
                                    break;
                                case 4:
                                    String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo:");
                                    factura.guardarFactura(nombreArchivo);
                                    break;
                                case 5:
                                    continuar = false;
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,
                                            "Opción inválida. Por favor, ingrese una opción válida.");
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
                    seguir = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }
    }
}
