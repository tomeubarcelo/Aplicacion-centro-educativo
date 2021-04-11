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
 * @author tomeu
 */
public class Tutor {
    private String nombre;
    private String codi;
    
    public Tutor(String nombre, String codi){
        this.nombre = nombre;
        this.codi = codi;
    }
    
    //SETTER NOMBRE TUTOR
    public void setNombre(String nombre) throws Exception {
        if (nombre.length() < 10 || nombre.length() > 40) {
            throw new Exception("Formato incorrecto. Mínimo 10 letras y máximo 40.");
        } else{
            this.nombre = nombre;
        }
    }
      
    //GETTER NOMBRE TUTOR
    public String getNombre() {
        return nombre;
    }

    //SETTER CODI TUTOR
    public void setCodi(String codi) throws Exception {
        if (!validaCodiTutor(codi)) { //crea l'excepció
            throw new Exception ("Formato incorrecto");
        } else{ //guarda el codi
            this.codi = codi;
        }  
    }
    
    //GETTER CODI TUTOR
    public String getCodi() {
        return codi;
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
