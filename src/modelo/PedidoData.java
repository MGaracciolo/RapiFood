/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import entidades.Mesa;
import entidades.Mesero;
import entidades.Pedido;
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
public class PedidoData {
    private Connection con = null;
    public PedidoData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarPedido(Pedido pedido){
            String sql = "INSERT INTO `pedido`( `id_mesero`, `id_mesa`, `monto`, `activo`) VALUES (?,?,?,?); ";
            try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, pedido.getMesero().getIdMesero());
                statement.setInt(2, pedido.getMesa().getIdMesa());
                statement.setDouble(3, pedido.getMonto());
                statement.setBoolean(4, pedido.isActivo());
                statement.executeUpdate();
                
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    pedido.setIdPedido(rs.getInt(1));
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el id del pedido");
                }
            }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar el pedido");
        }
    }
    
    public void actualizarPedido(Pedido pedido){
        String sql = " UPDATE `pedido` SET `id_mesero`=?,`id_mesa`=?,`monto`=?,`activo`=? WHERE id_pedido=?;";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, pedido.getMesero().getIdMesero());
            statement.setInt(2, pedido.getMesa().getIdMesa());
            statement.setDouble(3, pedido.getMonto());
            statement.setBoolean(4, pedido.isActivo());
 
                
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo actualizar el pedido");
        }       
    }
    
    public void borrarPedido(int id){
        String sql = " UPDATE `pedido` SET `activo`=? WHERE id_pedido="+id+";";
        
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setBoolean(1, false);
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo eliminar el pedido");
        }
    }
    
    public Pedido buscarPedido(int id){
        Pedido pedido= new Pedido();
        String sql = " SELECT * FROM pedido WHERE id_pedido="+id+";";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();       
            if(rs.next()){
                pedido.setMesero((Mesero) rs.getObject(1));//****************
                pedido.setMesa((Mesa) rs.getObject(2));//************************
                pedido.setMonto(rs.getDouble(3));
                pedido.setActivo(rs.getBoolean(4));

                System.out.println(pedido.getIdPedido());
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar el pedido");
        }        
        return pedido;       
    }
    
    public List<Pedido> obtenerPedidos(){
        Pedido pedido= new Pedido();
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pedido.setMesero((Mesero) rs.getObject(1));
                pedido.setMesa((Mesa) rs.getObject(2));
                pedido.setMonto(rs.getDouble(3));
                pedido.setActivo(rs.getBoolean(4));
                System.out.println("Pedido: "+pedido.getIdPedido());
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo listar los pedidos");
        }
        return pedidos;        
    }
}
