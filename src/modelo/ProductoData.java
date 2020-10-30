/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;

/**
 *
 * @author mgara
 */
public class ProductoData {
   private Connection con = null;
    public ProductoData(Conexion conexion) {
        con = conexion.getConnection();
    } 
}
