
package modelo;

import entidades.Mesero;
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
public class MeseroData {
    
    private Connection con = null;
    
    public MeseroData(Conexion conexion) {
        con = conexion.getConnection();
    }
    public void guardarMesero(Mesero mesero){
            String sql = "INSERT INTO `mesero`(`dni`, `nombre`, `apellido`, `cuit`, `activo`) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, mesero.getDni());
                statement.setString(2, mesero.getNombre());
                statement.setString(3, mesero.getApellido());
                statement.setLong(4, mesero.getCuit());
                statement.setBoolean(5, mesero.isActivo());
                
                statement.executeUpdate();
                
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    mesero.setIdMesero(rs.getInt(1));
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el id del mesero");
                }
            }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar al mesero");
        }
    }
    
    public void actualizarMesero(Mesero mesero){
        String sql = " UPDATE `mesero` SET `dni`=?,`nombre`=?,`apellido`=?,`cuit`=?,`activo`=? WHERE id_mesero=?";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, mesero.getDni());
            statement.setString(2,mesero.getNombre());
            statement.setString(3, mesero.getApellido());
            statement.setLong(4, mesero.getCuit());
            statement.setBoolean(5, mesero.isActivo());
                
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo actualizar el mesero");
        }       
    }
    
    public void borrarMesero(int id){
        String sql = " UPDATE `mesero` SET `activo`= ? WHERE id_mesero=?";
        
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setBoolean(1, false);
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo eliminar el mesero");
        }
    }
    
    public Mesero buscarMesero(int id){
        Mesero mesero= new Mesero();
        String sql = "SELECT * FROM mesero WHERE id_mesero=?";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();       
            if(rs.next()){
                mesero.setDni(rs.getLong(1));
                mesero.setNombre(rs.getString(2));
                mesero.setApellido(rs.getString(3));
                mesero.setCuit(rs.getLong(4));
                mesero.setActivo(rs.getBoolean(5));

                System.out.println(mesero.getIdMesero());
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar al mesero");
        }        
        return mesero;       
    }
    
    public List<Mesero> obtenerMeseros(){
        Mesero mesero= new Mesero();
        List<Mesero> meseros = new ArrayList<>();
        String sql = "SELECT * FROM mesero";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                mesero.setIdMesero(rs.getInt(1));
                mesero.setDni(rs.getLong(2));
                mesero.setApellido(rs.getString(3));
                mesero.setCuit(rs.getLong(4));
                mesero.setActivo(rs.getBoolean(5));
                System.out.println("Mesero: "+mesero.getIdMesero());
                meseros.add(mesero);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo listar los meseros");
        }
        return meseros;        
    }
    
}
