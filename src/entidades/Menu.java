/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author mgara
 */
public class Menu {
    private int idMenu;
    
    private Pedido pedido;
    
    private Producto producto;

    public Menu(int idMenu, Pedido pedido, Producto producto) {
        this.idMenu = idMenu;
        this.pedido = pedido;
        this.producto = producto;
    }

    public Menu(Pedido pedido, Producto producto) {
        this.pedido = pedido;
        this.producto = producto;
    }

    public Menu() {
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Menu{" + "idMenu=" + idMenu + ", pedido=" + pedido + ", producto=" + producto + '}';
    }

   

    
    
}
