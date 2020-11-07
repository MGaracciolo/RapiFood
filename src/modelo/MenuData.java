/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import entidades.Menu;
import entidades.Pedido;
import entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mgara
 */
public class MenuData {
    private Connection con = null;
    
    public MenuData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarMenu(Menu menu){
            String sql = "INSERT INTO `menu`(`id_pedido`, `codigo`) VALUES (?,?); ";
            try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, menu.getPedido().getIdPedido());
                statement.setInt(2, menu.getProducto().getCodigo());
                
                statement.executeUpdate();
                
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    menu.setIdMenu(rs.getInt(1));
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el id del menu");
                }
            }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar el menu");
        }
    }
    
    public void actualizarMenu(Menu menu){
        String sql = " UPDATE `menu` SET `id_pedido`=?,`codigo`=? WHERE `id_menu`=?;";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, menu.getPedido().getIdPedido());
            statement.setInt(2, menu.getProducto().getCodigo());
            
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo actualizar el menu");
        }       
    }
    
    public void borrarMenu(int id){
        String sql = "DELETE FROM menu WHERE id_menu="+id+";";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e) {
             System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo eliminar el menu");
        }
    }
    
    public Menu buscarMenu(int id){
        Menu menu= new Menu();
        String sql = " SELECT * FROM menu WHERE id_menu="+id+";";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();       
            if(rs.next()){
                menu.setPedido((Pedido) rs.getObject(1));
                menu.setProducto((Producto) rs.getObject(2));

                System.out.println(menu.getIdMenu());
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar el menu");
        }        
        return menu;       
    }
    
    public List<Menu> obtenerMenus(){
        Menu menu= new Menu();
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //aca guardamos tambien el id o no?
                
                menu.setPedido((Pedido) rs.getObject(1));
                menu.setProducto((Producto) rs.getObject(2));
                
                System.out.println("Menu: "+menu.getIdMenu());
                menus.add(menu);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo listar los menus");
        }
        return menus;        
    }
}
