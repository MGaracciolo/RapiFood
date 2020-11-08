
package modelo;

import entidades.Mesa;
import entidades.Reserva;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ReservaData {
    private Connection con = null;
    public ReservaData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarReserva(Reserva reserva){
            String sql = "INSERT INTO `reserva`(`nombre`, `apellido`, `dni`, `fecha_hora`, `activo`, `id_mesa`) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1,reserva.getNombre());
                statement.setString(2, reserva.getApellido());
                statement.setLong(3, reserva.getDni());
                statement.setDate(4, (Date) reserva.getFecha());
                statement.setBoolean(5, reserva.isActivo());
                statement.setInt(6, reserva.getMesa().getIdMesa());
               
                statement.executeUpdate();
                
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    reserva.setIdReserva(rs.getInt(1));
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el id de la reserva");
                }
            }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar la reserva");
        }
    }
    
    public void actualizarReserva(Reserva reserva){
        String sql = " UPDATE `reserva` SET `nombre`=?,`apellido`=?,`dni`=?,`fecha_hora`=?,`activo`=?,`id_mesa`=? WHERE `id_reserva`=?;";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,reserva.getNombre());
            statement.setString(2, reserva.getApellido());
            statement.setLong(3, reserva.getDni());
            statement.setDate(4, (Date) reserva.getFecha());
            statement.setBoolean(5, reserva.isActivo());
            statement.setInt(6, reserva.getMesa().getIdMesa());
                
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo actualizar la reserva");
        }       
    }
    
    public void borrarReserva(int id){
        String sql = " UPDATE `reserva` SET `activo`=? WHERE id_reserva=?";
        
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setBoolean(1, false);
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo eliminar la reserva");
        }
    }
    
    public Reserva buscarReserva(int id){
        Reserva reserva= new Reserva();
        String sql = "SELECT * FROM reserva WHERE id_reserva=?";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();       
            if(rs.next()){
                reserva.setNombre(rs.getString(1));
                reserva.setApellido(rs.getString(2));
                reserva.setDni(rs.getLong(3));
                reserva.setFecha(rs.getDate(4));
                reserva.setActivo(rs.getBoolean(5));
                reserva.setMesa((Mesa) rs.getObject(6));
               
                System.out.println(reserva.getIdReserva());

            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar la reserva");
        }        
        return reserva;       
    }
    
    public List<Reserva> obtenerReservas(){
        Reserva reserva= new Reserva();
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                reserva.setNombre(rs.getString(1));
                reserva.setApellido(rs.getString(2));
                reserva.setDni(rs.getLong(3));
                reserva.setFecha(rs.getDate(4));
                reserva.setActivo(rs.getBoolean(5));
                reserva.setMesa((Mesa) rs.getObject(6));
               
                System.out.println(reserva.getIdReserva());
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo listar las reservas");
        }
        return reservas;        
    }
    
    public List<Reserva> obtenerReservasXFecha(Date fecha){
       Reserva reserva= new Reserva();
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM `reserva` WHERE fecha_hora=?;";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                reserva.setNombre(rs.getString(1));
                reserva.setApellido(rs.getString(2));
                reserva.setDni(rs.getLong(3));
                reserva.setFecha(rs.getDate(4));
                reserva.setActivo(rs.getBoolean(5));
                reserva.setMesa((Mesa) rs.getObject(6));
               
                System.out.println(reserva.getIdReserva());
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo listar las reservas");
        }
        return reservas;         
    }
}
