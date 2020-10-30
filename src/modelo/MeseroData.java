
package modelo;

import java.sql.Connection;

/**
 *
 * @author mgara
 */
public class MeseroData {
    
    private Connection con = null;
    
    public MeseroData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    //
}
