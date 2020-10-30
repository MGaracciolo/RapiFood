package entidades;

public class Mesa {

    private int idMesa;

    private int capacidad;

    private boolean activo;

    public Mesa(int idMesa, int capacidad, boolean activo) {
        this.idMesa = idMesa;
        this.capacidad = capacidad;
        this.activo = activo;
    }

    public Mesa(int capacidad, boolean activo) {
        this.capacidad = capacidad;
        this.activo = activo;
    }

    public Mesa() {
    }

    public void setIdMesa(int idMesa) {
        this.idMesa=idMesa;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad=capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setActivo(boolean activo) {
        this.activo=activo;
    }

    public boolean getActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "Mesa{" + "idMesa=" + idMesa + ", capacidad=" + capacidad + ", activo=" + activo + '}';
    }

    
    
}
