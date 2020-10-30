package entidades;


import java.util.Date;

public class Reserva {
    private int idReserva;
    
    private long dni;

    private String nombre;

    private String apellido;

    private Date fecha;

    private Mesa mesa;

    private boolean activo;

    public Reserva(int idReserva, long dni, String nombre, String apellido, Date fecha, Mesa mesa, boolean activo) {
        this.idReserva = idReserva;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.mesa = mesa;
        this.activo = activo;
    }
    
    public Reserva(long dni, String nombre, String apellido, Date fecha, Mesa mesa, boolean activo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.mesa = mesa;
        this.activo = activo;
    }

    public Reserva() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha=" + fecha + ", mesa=" + mesa + ", activo=" + activo + '}';
    }

    
}
