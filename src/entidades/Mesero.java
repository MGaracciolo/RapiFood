package entidades;

public class Mesero {
    
    private int idMesero;

    private long dni;

    private String nombre;

    private String apellido;

    private long cuit;

    private boolean activo;

    public Mesero(int idMesero, long dni, String nombre, String apellido, long cuit, boolean activo) {
        this.idMesero = idMesero;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuit = cuit;
        this.activo = activo;
    }

    public Mesero(long dni, String nombre, String apellido, long cuit, boolean activo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuit = cuit;
        this.activo = activo;
    }

    public Mesero() {
    }

    public int getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(int idMesero) {
        this.idMesero = idMesero;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Mesero{" + "idMesero=" + idMesero + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", cuit=" + cuit + ", activo=" + activo + '}';
    }

    

    
}
