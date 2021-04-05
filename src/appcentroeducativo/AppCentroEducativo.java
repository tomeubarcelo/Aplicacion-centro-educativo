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
import java.util.ArrayList;
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
                        //1a execucio
                        rs = stmt.executeQuery("SELECT codiAlumne, nomAlumne, codiTutorAlumne FROM alumno ORDER BY nomAlumne");
                        while (rs.next()) {                
                            alumno = new Alumno(rs.getString("codiAlumne"),rs.getString("nomAlumne") ,rs.getString("codiTutorAlumne"));
                            System.out.println(alumno.getCodi() + " "+ alumno.getNombre() + " "+alumno.getCodiTutorAlu());
                        }
                        break;
                    case 2:
                        //2A execucio
                        rs = stmt.executeQuery("SELECT codiTutor, nomTutor FROM tutor");
                        while (rs.next()) {                
                            tutor = new Tutor(rs.getString("codiTutor"),rs.getString("nomTutor"));
                            System.out.println(tutor.getCodi() + " "+ tutor.getNombre());
                        }
                        break;
                    case 3:
                        //3A execucio

                        System.out.println("Código de los tutores:");
                        //creamos arrayList donde almacenamos el cod de cada profesor
                        ArrayList <String> arrayCodTutor = new ArrayList<>();
                        
                        //ENUMERAR TODOS LOS CODIGOS DE LOS TUTORES
                        try {
                            rs = stmt.executeQuery("SELECT distinct codiTutorAlumne FROM alumno");
                            while (rs.next()) {                
                                alumno = new Alumno("","",rs.getString("codiTutorAlumne"));
                                System.out.print(alumno.getCodiTutorAlu()+", ");
                                arrayCodTutor.add(alumno.getCodiTutorAlu()); //guardamos el cod en array
                            }    
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                        //variable per controlar que el codi alu sigui correcte
                        boolean dadaOk;
                        String codiInserted = "";
                        //Demanar el codi fins que sigui correcte
                        do { 
                            try {
                                System.out.println("Inserte codigo tutor para ver sus alumnos: ");
                                Scanner sc = new Scanner (System.in);
                                codiInserted = sc.next();
                                dadaOk = arrayCodTutor.contains(codiInserted); 
                                //dada ok sera true o false si existe o no el codigo
                            }catch (Exception e){ //tractam l'excepció generada per setCodi
                                System.out.println(e.getMessage()+ ". Torna a introduir el codi del tutor: ");
                                dadaOk = false;
                            }
                        } while (!dadaOk);
                        
                        //consulta de todos los alumnos con el codigo del tutor introducido
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
