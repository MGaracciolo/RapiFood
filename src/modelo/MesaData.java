
package modelo;

import entidades.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MesaData {
    
    private Connection con = null;
    
    public MesaData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    public void guardarMesa(Mesa mesa){
            String sql = "INSERT INTO `mesa`(`capacidad`, `activo`) VALUES (?,?) ";
            try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1,mesa.getCapacidad());
                statement.setBoolean(2, mesa.getActivo());
                statement.executeUpdate();
                
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    mesa.setIdMesa(rs.getInt(1));
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el id de la mesa");
                }
            }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo guardar la mesa");
        }
    }
    
    public void actualizarMesa(Mesa mesa){
        String sql = " UPDATE `mesa` SET `capacidad`=?,`activo`=? WHERE id_mesa=?";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, mesa.getCapacidad());
            statement.setBoolean(2, mesa.getActivo());
                
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo actualizar la mesa");
        }       
    }
    
    public void borrarMesa(int id){
        String sql = " UPDATE `mesa` SET `activo`=? WHERE id_mesa=?";
        
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setBoolean(1, false);
            statement.executeUpdate();
        }catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo eliminar la mesa");
        }
    }
    
    public Mesa buscarMesa(int id){
        Mesa mesa= new Mesa();
        String sql = "SELECT * FROM mesa WHERE id_mesa=?";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();       
            if(rs.next()){
                mesa.setCapacidad(rs.getInt(1));
                mesa.setActivo(rs.getBoolean(2));
                System.out.println(mesa.getIdMesa());
                /*
                alumno.setIdAlumno(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setLegajo(rs.getInt(3));
                alumno.setActivo(rs.getBoolean(4));
                System.out.println(alumno.getNombre());*/
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo encontrar la mesa");
        }        
        return mesa;       
    }
    
    public List<Mesa> obtenerMesas(){
        Mesa mesa= new Mesa();
        List<Mesa> mesas = new ArrayList<>();
        String sql = "SELECT * FROM mesa";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                mesa.setIdMesa(rs.getInt("id_mesa"));
                mesa.setCapacidad(rs.getInt("capacidad"));
                mesa.setActivo(rs.getBoolean("activo"));
                System.out.println("Mesa: "+mesa.getIdMesa());
                mesas.add(mesa);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
            JOptionPane.showMessageDialog(null, " No se pudo listar las mesas");
        }
        return mesas;        
    }
 
}
