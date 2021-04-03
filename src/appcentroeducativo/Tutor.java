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
    public void setNombre(String nombre, String codi) {
        this.nombre = nombre;
        this.codi = codi;
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
