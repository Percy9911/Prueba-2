import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RegistroUsuarios {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Sede> sedes;

    public RegistroUsuarios() {
        usuarios = new ArrayList<>();
        sedes = new ArrayList<>();
        sedes.add(new Sede("Sede principal", 1));
        sedes.add(new Sede("Sede secundaria", 2));
    }
    public RegistroUsuarios(ArrayList<Sede> sedes) {
        this.usuarios = new ArrayList<>();
        this.sedes = sedes;
    }
    public ArrayList<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(ArrayList<Sede> sedes) {
        this.sedes = sedes;
    }


    public void agregarUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del usuario:"));
        String correo = JOptionPane.showInputDialog("Ingrese el correo electrónico del usuario:");
        String ruc = JOptionPane.showInputDialog("Ingrese el RUC del usuario:");
        int sedeSeleccionada = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la sede:\n" + listarSedes()));
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña del usuario:");
        Usuario usuario = new Usuario(nombre, edad, correo, ruc, sedes.get(sedeSeleccionada), contrasena);
        usuarios.add(usuario);
        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
    }

    public void mostrarUsuarios() {
        String listaUsuarios = "Lista de usuarios:\n";
        for (Usuario usuario : usuarios) {
            listaUsuarios += usuario.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listaUsuarios);
    }
    public Usuario iniciarSesion() {
        String correoElectronico = JOptionPane.showInputDialog("Ingrese su correo electrónico:");
        String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getCorreoElectronico().equals(correoElectronico) && usuario.getContrasena().equals(contrasena)) {
                JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario.getNombre() + "!");
                return usuario;
            }
        }
    
        JOptionPane.showMessageDialog(null, "Credenciales inválidas. Inténtelo de nuevo.");
        return null;
    }
    private String listarSedes() {
        String lista = "";
        for (int i = 0; i < sedes.size(); i++) {
            lista += i + ". " + sedes.get(i).getDireccion() + "\n";
        }
        return lista;
    }
}
