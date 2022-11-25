/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.io.Serializable;

/**
 *
 * @author sensei
 */
public class Expresion implements Serializable{
    float TV;
    float Radio;
    float Newspaper;
    float Sales;
    public void setSales(float s){
        Sales = s;
    }
    public void setTV(float t){
        TV = t;
    }
    public void setRadio(float r){
        Radio = r;
    }
    public void setNewspaper(float n){
        Newspaper = n;
    }
    public float getTV(){
        return TV;
    }
    public float getRadio(){
        return Radio;
    }
    public float getNewspaper(){
        return Newspaper;
    }
    public float getSales(){
        return Sales;
    }
    
}
