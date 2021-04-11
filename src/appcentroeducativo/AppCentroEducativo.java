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
    public static void main(String[] args) throws Exception {
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
                            //consulta donde aparecen los codigos de los tutores de los alumnos sin repeticion
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
                            }catch (Exception e){ //tractam l'excepció
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
                        
                        //indica el número total de alumnos de dicho tutor 
                        try {
                            rs = stmt.executeQuery("SELECT COUNT(codiAlumne) AS NumberOfAlumns FROM alumno WHERE codiTutorAlumne ="+codiInserted);  
                           
                            rs.next();
                            int count = rs.getInt(1);
                            System.out.println("Número de alumnos: "+count);   
                            
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                        break;

                    case 4:
                        //System.out.println("\n4a ejecucion");
                        try {
                            String codiTutorNuevo = "999";
                            String nombreTutorNuevo = "Catalina Salas Simón";
                            stmt.executeUpdate("INSERT INTO TUTOR (codiTutor, nomTutor) VALUES ('"+codiTutorNuevo+"', '"+nombreTutorNuevo+"')");
                                       
                            tutor = new Tutor(codiTutorNuevo,nombreTutorNuevo);
                            tutor.setCodi(codiTutorNuevo);
                            tutor.setNombre(nombreTutorNuevo);
                            System.out.println(tutor.getCodi() + " "+ tutor.getNombre());
                            System.out.println("Insertado");
                                
                         } catch (SQLException e) {
                             System.err.println(e);
                             //System.err.println("Ya existe el código");
                         }

                        break;
                    case 5:
                        //System.out.println("\n5a ejecucion");
                        String codiAluNuevo = "CCC99";
                        String nombreAluNuevo = "Carme Costa Coll";
                        String codTutorNuevoAlumno = "999";
                        try {
                            stmt.executeUpdate("INSERT INTO ALUMNO VALUES ('"+codiAluNuevo+"', '"+nombreAluNuevo+"', '"+codTutorNuevoAlumno+"')");
                            alumno = new Alumno(codiAluNuevo,nombreAluNuevo, codTutorNuevoAlumno);
                            //declaramos al alumno con los datos a insertar
                            //metodos setters que validan dichos datos
                            alumno.setCodi(codiAluNuevo);         
                            alumno.setNombre(nombreAluNuevo); 
                            alumno.setCodiTutorAlu(codTutorNuevoAlumno); 
                            System.out.println(alumno.getCodi()+ " "+ alumno.getNombre()+" "+ alumno.getCodiTutorAlu());
                            System.out.println("Insertado");
                                
                         } catch (SQLException e) {
                             System.err.println(e);
                         }
                        
                        break;
                    case 6:
                        //System.out.println("\n6a ejecucion");
                        
                        System.out.println("Código de los alumnos:");
                        //creamos arrayList donde almacenamos el cod de cada ALU
                        ArrayList <String> arrayCodAlu = new ArrayList<>();
                        
                        //ENUMERAR TODOS LOS CODIGOS DE LOS ALUMNOS PARA ELEGIR CUAL BORRAR
                        try {
                            rs = stmt.executeQuery("SELECT distinct codiAlumne FROM alumno");
                            //consulta donde aparecen los codigos de los alumnos sin repeticion
                            while (rs.next()) {                
                                alumno = new Alumno(rs.getString("codiAlumne"),"","");
                                System.out.println(alumno.getCodi());
                                arrayCodAlu.add(alumno.getCodi()); //guardamos el cod en array
                            }    
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                        //variable per controlar que el codi alu sigui correcte
                        boolean dadaOk2;
                        String codiInserted2 = "";
                        //Demanar el codi fins que sigui correcte
                        do { 
                            try {
                                System.out.println("Inserte codigo alumno que quiere borrar: ");
                                Scanner sc = new Scanner (System.in);
                                codiInserted2 = sc.next();
                                dadaOk2 = arrayCodAlu.contains(codiInserted2); 
                                //dada ok sera true o false si existe o no el codigo
                            }catch (Exception e){ //tractam l'excepció
                                System.out.println(e.getMessage()+ ". Torna a introduir el codi del alumne: ");
                                dadaOk2 = false;
                            }
                        } while (!dadaOk2);
                        
                        //eliminar codi alu seleccionado              
                        try {                                       
                            System.out.println("Vas a borrar al alumno con código "+codiInserted2);  
                            stmt.executeUpdate("DELETE FROM ALUMNO WHERE codiAlumne ='"+codiInserted2+"'");
                            //borramos el alumno con el codigo de alumno que hemos elegido anteriormente
                            System.out.println("Se borró al alumno con código " + codiInserted2 + " correctamente.") ;
                                
                         } catch (SQLException e) {
                             System.err.println(e);
                         }
                        
                        break;
                    case 7:
                        //System.out.println("\n7a ejecucion");
                        
                        System.out.println("Código de los tutores:");
                        //creamos arrayList donde almacenamos el cod de cada profesor
                        ArrayList <String> arrayCodTutor2 = new ArrayList<>();
                        
                        //ENUMERAR TODOS LOS CODIGOS DE LOS TUTORES
                        try {
                            rs = stmt.executeQuery("SELECT distinct codiTutor FROM tutor");
                            //consulta donde aparecen los codigos de los tutores de los alumnos sin repeticion
                            while (rs.next()) {                
                                tutor = new Tutor("",rs.getString("codiTutor"));
                                System.out.print(tutor.getCodi()+", ");
                                arrayCodTutor2.add(tutor.getCodi()); //guardamos el cod en array
                            }    
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                        //variable per controlar que el codi tutor sigui correcte
                        boolean dadaOk3;
                        String codiInserted3 = "";
                        //Demanar el codi fins que sigui correcte
                        do { 
                            try {
                                System.out.println("Inserte codigo tutor del cual quiere modificar el nombre: ");
                                Scanner sc = new Scanner (System.in);
                                codiInserted3 = sc.next();
                                dadaOk3 = arrayCodTutor2.contains(codiInserted3); 
                                //dada ok sera true o false si existe o no el codigo
                            }catch (Exception e){ //tractam l'excepció
                                System.out.println(e.getMessage()+ ". Torna a introduir el codi del tutor: ");
                                dadaOk3 = false;
                            }
                        } while (!dadaOk3);

                        //modificar nombre tutor
                        try {                                       
                            System.out.println("Vas a modificar el nombre del tutor con código: "+codiInserted3);  
                            Scanner sc = new Scanner (System.in);
                            System.out.println("Inserta el nuevo nombre:");  
                            String nuevoNombreTutor = "";
                            nuevoNombreTutor = sc.nextLine();
                            
                            tutor = new Tutor(codiInserted3,nuevoNombreTutor);
                            tutor.setNombre(nuevoNombreTutor);
                            System.out.println(tutor.getNombre());
                            stmt.executeUpdate("UPDATE TUTOR SET nomTutor='"+nuevoNombreTutor+"' WHERE codiTutor='"+codiInserted3+"'");
                            //System.out.println(nuevoNombreTutor);  
                            //modificamos el nombre del tutor
                            System.out.println("Has modificado el nombre del nuevo tutor " + nuevoNombreTutor + " correctamente.") ;
                                
                         } catch (SQLException e) {
                             System.err.println(e);
                         }  
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
    
    /*public int num() throws Exception {
        try {
        resultSet = statement.executeQuery("select count(*) from testdb.emg");

        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
       } catch (Exception e) {
       }
        return 0;
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
