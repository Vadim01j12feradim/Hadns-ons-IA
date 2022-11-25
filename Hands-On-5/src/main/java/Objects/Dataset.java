/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sensei
 */
public class Dataset implements Serializable{
    private List<Expresion> data = new ArrayList<>();
    public Dataset(){
        
    }
    public void setExpresion(Expresion p){
        data.add(p);
    }
    public int getSize(){
        return data.size();
    }
    public Expresion getExpresion(int index){
        return data.get(index);
    }
}
