/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tique
 */
public class Conexion {

    public Conexion() throws SQLException, ClassNotFoundException {
        getConexion();
    }
    ;
    private static Connection con;

    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://proyecto.cdpsawbnswbf.us-east-1.rds.amazonaws.com:3306/proyecto";
        String user = "admin";
        String clave = "12345678";
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        con = DriverManager.getConnection(url, user, clave);
        return con;
    }

    public void setValues(String user, String password, String carrera) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO user values (?,?,?)");
        ps.setString(1, user);
        ps.setString(2, password);
        ps.setString(3, carrera);
        ps.executeUpdate();
    }
}
