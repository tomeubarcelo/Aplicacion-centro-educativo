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

    public Alumno(String codi, String nombre, String codiTutorAlu){
        this.codi = codi;
        this.nombre = nombre;
        this.codiTutorAlu = codiTutorAlu;
    }
    
    //MÉTODO ESTÁTICO DE CLASE USADO PARA VALIDAR UN CÓDIGO, SE LLAMA 
    //DIRECTAMENTE DESDE CLASE
    public static boolean validaCodi(String codiAlumne) {
        Pattern p=Pattern.compile("[A-Z]{3}[0-9]{2}");
        Matcher m=p.matcher(codiAlumne);
        
        if (m.matches()){ //formato correcto
            return true; 
        } else{ //formato incorrecto
            return false;
        }
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //GETTER NOMBRE
    public String getNombre() {
        return nombre;
    }

    //SETTER TUTOR ALU
    public void setCodiTutorAlu(String codiTutorAlu) {
        this.codiTutorAlu = codiTutorAlu;
    }
    
    //GETTER TUTOR ALU
    public String getCodiTutorAlu() {
        return codiTutorAlu;
    }
}


