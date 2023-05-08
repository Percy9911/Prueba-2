public class Sede {
    private String direccion;
    private int id;

    public Sede(String direccion, int id) {
        this.direccion = direccion;
        this.id = id;
    }

    // Getters and Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
