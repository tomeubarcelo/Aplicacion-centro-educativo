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
import java.util.Scanner;

/**
 *
 * @author tomeu
 */
public class AppCentroEducativo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //variables necesarias para la conexion a la bbdd
        Connection con;
        Statement stmt;
        ResultSet rs = null;
        
        Alumno alumno; //declaramos objeto alumno
        Tutor tutor; //declaramos objeto tutor
        
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
            
            byte opcio = 0;
            //bucle para el menu
            do {
                opcio = menuOpcions();
                switch (opcio) {
                    case 1: 
                    System.out.println("1a ejecucion");
                    //execucio
                    rs = stmt.executeQuery("SELECT codiAlumne, nomAlumne, codiTutorAlumne FROM alumno ORDER BY nomAlumne");
                    while (rs.next()) {                
                        alumno = new Alumno(rs.getString("codiAlumne"),rs.getString("nomAlumne") ,rs.getString("codiTutorAlumne"));
                        System.out.println(alumno.getCodi() + " "+ alumno.getNombre() + " "+alumno.getCodiTutorAlu());
                    }
                        break;
                    case 2:
                        System.out.println("2a ejecucion");
                        //2A execucio
                        rs = stmt.executeQuery("SELECT codiTutor, nomTutor FROM tutor");
                        while (rs.next()) {                
                            tutor = new Tutor(rs.getString("codiTutor"),rs.getString("nomTutor"));
                            System.out.println(tutor.getCodi() + " "+ tutor.getNombre());
                        }
                        break;
                    case 3:
                        System.out.println("3a ejecucion");
                        //String codiInserted = "";
                        
                        System.out.print("Inserte codigo tutor: ");
                        Scanner sc = new Scanner (System.in);
                        String codiInserted = sc.next();
                        try {
                            rs = stmt.executeQuery("SELECT * FROM alumno WHERE codiTutorAlumne ="+codiInserted);
                            while (rs.next()) {                
                                alumno = new Alumno(rs.getString("codiAlumne"),rs.getString("nomAlumne") ,rs.getString("codiTutorAlumne"));
                                System.out.println(alumno.getCodi() + " "+ alumno.getNombre() + " "+alumno.getCodiTutorAlu());
                            }    
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                        
                        break;

                    case 4:
                        System.out.println("\n4a ejecucion");
                        break;
                    case 5:
                        System.out.println("\n5a ejecucion");
                        break;
                    case 6:
                        System.out.println("\n6a ejecucion");
                        break;
                    case 7:
                        System.out.println("\n7a ejecucion");
                        break;
                    case 8:
                        System.out.println("PROGRAMA FINALITZAT!!!");
                        break;
                    default:
                        System.out.println("Aquesta opció no existeix.");
                } 
            } while (opcio==1 || opcio == 2 || opcio==3 || opcio == 4 || opcio == 5 || opcio == 6 || opcio == 7);

            //tancam les connexions
            rs.close();
            stmt.close();
            con.close();
            
        } catch (SQLException ex) {
            //excepcio en el cas que no s'hagi fet la connexio a la bbdd
            Logger.getLogger(AppCentroEducativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*private static mostrarAlumnos(){
        
    }*/
    
    //MENÚ DE OPCIONS
    private static byte menuOpcions ()  {
        byte opcio=0;
        do{
            try{
                Scanner op = new Scanner (System.in);
                //menú d'opcions del programa
                System.out.println("1. Mostrar a todos los alumnos de la base de datos.");
                System.out.println("2. Mostrar a todos los tutores de la base de datos.");
                System.out.println("3. Mostrar el listado de alumnos de un determinado tutor, indicando el número total de alumnos de dicho tutor al final del listado.");
                System.out.println("4. Insertar un nuevo tutor con código 999 y nombre “Catalina Salas Simón”. Si el código ya existe, mostrar el mensaje correspondiente por pantalla."); 
                System.out.println("5. Insertar un nuevo alumno con código CCC99, nombre “Carme Costa Coll” y código de tutor 999.");
                System.out.println("6. Pedir por teclado el código de un alumno y eliminarlo de la base de datos.");
                System.out.println("7. Modificar el nombre de un tutor, dado su código por teclado.");
                System.out.println("8. Salir de la aplicación.");
                System.out.println("Introdueix l'opcio elegida: ");
                opcio=op.nextByte();
                if (opcio < 1 || opcio > 8) {
                System.out.println("Escollir entre (1..8)!.");    
                }
            }    
            catch(Exception e){
                System.out.println(e.getMessage()+ " . Error al llegir del teclat(1..8)!.");
            }
            
        }while (opcio < 1 || opcio > 8);
        return opcio;
    } 
}
