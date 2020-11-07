/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
public class ProductoData {
   private Connection con = null;
    public ProductoData(Conexion conexion) {
        con = conexion.getConnection();
    } 
    
    public void guardarProducto(Producto producto){
        //El codigo en producto es autogenerable?
        String sql = "INSERT INTO `producto`( `nombre`, `precio`, `activo`) VALUES (?,?,?); ";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setBoolean(3, producto.isActivo());
            statement.executeUpdate();
                
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                producto.setCodigo(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el id del producto");
            }
            }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar el producto");
        }
    }
    
    public void actualizarProducto(Producto producto){
        String sql = " UPDATE `producto` SET `nombre`=?,`precio`=?,`activo`=? WHERE `codigo`=?;";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setBoolean(3, producto.isActivo());
      
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo actualizar el producto");
        }       
    }
    
    public void borrarProducto(int id){
        String sql = " UPDATE `producto` SET `activo`=? WHERE codigo="+id+";";
        
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setBoolean(1, false);
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo eliminar el producto");
        }
    }
    
    public Producto buscarProducto(int id){
        Producto producto= new Producto();
        String sql = " SELECT * FROM producto WHERE codigo="+id+";";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();       
            if(rs.next()){
                producto.setNombre(rs.getString(1));
                producto.setPrecio(rs.getDouble(2));
                producto.setActivo(rs.getBoolean(3));

                System.out.println(producto.getCodigo());
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar el producto");
        }        
        return producto;       
    }
    
    public List<Producto> obtenerProducto(){
        Producto producto= new Producto();
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                producto.setCodigo(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecio(rs.getDouble(3));
                producto.setActivo(rs.getBoolean(4));
               
                System.out.println("Producto: "+producto.getCodigo());
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo listar los productos");
        }
        return productos;        
    }
}
