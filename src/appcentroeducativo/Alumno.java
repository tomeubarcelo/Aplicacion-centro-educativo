/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appcentroeducativo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tomeu barceló
 * Tarea PROG11
 */
public class Alumno {
    private String codi;
    private String nombre;
    private String codiTutorAlu;
    
    //CONSTRUCTOR POR DEFECTO
    public Alumno(){
    }
    
    public Alumno(String codi, String nombre, String codiTutorAlu){
        this.codi = codi;
        this.nombre = nombre;
        this.codiTutorAlu = codiTutorAlu;
    }
      
    //MÉTODO SETTER DEL CÓDIGO
    //ANTES DE ESTABLECER UN CÓDIGO SIEMPRE VALIDAREMOS EL PATRÓN 
    //LLAMANDO AL MÉTODO DE CLASE validaCodi()
    public void setCodi(String codiAlumne) throws Exception {
        if (!validaCodi(codiAlumne)) { //crea l'excepció
            throw new Exception ("Formato incorrecto");
        } else{ //guarda el codi
            this.codi = codiAlumne;
        }
    }
   
    //GETTER DEL CÓDIGO
    public String getCodi () {
        return codi;

    }

    //SETTER NOMBRE
    public void setNombre(String nombre) throws Exception {
        if (nombre.length() < 10 || nombre.length() > 40) {
            throw new Exception("Formato incorrecto. Mínimo 10 letras y máximo 40.");
        } else{
            this.nombre = nombre;
        }
    }

    //GETTER NOMBRE
    public String getNombre() {
        return nombre;
    }

    //SETTER TUTOR ALU
    public void setCodiTutorAlu(String codiTutorAlu) throws Exception {
        if (!validaCodiTutor(codiTutorAlu)) { //crea l'excepció
            throw new Exception ("Formato incorrecto");
        } else{ //guarda el codi
            this.codiTutorAlu = codiTutorAlu;
        }
    }
    
    //GETTER TUTOR ALU
    public String getCodiTutorAlu() {
        return codiTutorAlu;
    }
    
    //MÉTODO ESTÁTICO DE CLASE USADO PARA VALIDAR EL CÓDIGO DE LOS ALUMNOS
    public static boolean validaCodi(String codiAlumne) {
        Pattern p=Pattern.compile("[A-Z]{3}[0-9]{2}");
        Matcher m=p.matcher(codiAlumne);
        
        if (m.matches()){ //formato correcto
            return true; 
        } else{ //formato incorrecto
            return false;
        }
    }
    
    //MÉTODO ESTÁTICO DE CLASE USADO PARA VALIDAR EL CÓDIGO DE LOS TUTORES
    public static boolean validaCodiTutor(String codiTutorAlu) {
        Pattern p=Pattern.compile("[0-9]{3}");
        Matcher m=p.matcher(codiTutorAlu);
        
        if (m.matches()){ //formato correcto
            return true; 
        } else{ //formato incorrecto
            return false;
        }
    }
    
}


