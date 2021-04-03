/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appcentroeducativo;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tomeu
 */
public class AppCentroEducativo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con;
        Statement stmt;
        ResultSet rs;
        
        //variables para conexion bbdd
        String connexioUrl,user,password;
        try {
            connexioUrl = "jdbc:mysql://localhost:3306/bd_tomeu_barcelo_pons";
            user = "root";
            password = "12345";   
            con=(Connection) DriverManager.getConnection(connexioUrl,user,password);  
            System.out.println("Connexio realitzada!");
            
            //crear sentencia
            stmt = con.createStatement();
            //execucio
            rs = stmt.executeQuery("SELECT * FROM alumno");
            while (rs.next()) {                
                //client = new cliente(rs.getInt("idCliente"), rs.getString("NOMBRE"));
                //System.out.println(client.getIdCliente() + " "+ client.getNombre());
            }
            //tancat les connexions
            rs.close();
            stmt.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AppCentroEducativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
