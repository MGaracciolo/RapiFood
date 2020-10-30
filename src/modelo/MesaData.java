
package modelo;

import java.sql.Connection;

public class MesaData {
    
    private Connection con = null;
    
    public MesaData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
}
