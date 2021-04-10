/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appcentroeducativo;

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
    public String getCodi() {
        return codi;
    }

    //GETTER CODI TUTOR
    public void setCodi(String codi) {
        this.codi = codi;
    }
    
    
}
