import javax.swing.JOptionPane;
public class Usuario {
    private String nombre;
    private int edad;
    private String correoElectronico;
    private String ruc;
    private Sede sede;
    private String contrasena;

    public Usuario(String nombre, int edad, String correoElectronico, String ruc, Sede sede, String contrasena) {
        this.nombre = nombre;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
        this.ruc = ruc;
        this.sede = sede;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
 
    
}
