package entidades;


public class Pedido {

    private int idPedido;

    private double monto;

    private Mesa mesa;

    private Mesero mesero;

    private Producto producto;

    private boolean activo;

    
    public Pedido(int idPedido, double monto, Mesa mesa, Mesero mesero, Producto producto, boolean activo) {
        this.idPedido = idPedido;
        this.monto = monto;
        this.mesa = mesa;
        this.mesero = mesero;
        this.producto = producto;
        this.activo = activo;
    }

    public Pedido(double monto, Mesa mesa, Mesero mesero, Producto producto, boolean activo) {
        this.monto = monto;
        this.mesa = mesa;
        this.mesero = mesero;
        this.producto = producto;
        this.activo = activo;
    }

    public Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", monto=" + monto + ", mesa=" + mesa + ", mesero=" + mesero +", activo=" + activo + '}';
    }
    
    
}
