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
public class Matrix implements Serializable{
    public Matrix(){
        
    }
    float[][] data = new float[4][8];
    float[] solutions = new float[4];
    
    public void setData(float[][] d){
        data = d;
    }
    public float[][] getData(){
        return data;
    }
    public void setSolutions(float[] s){
        solutions = s;
    }
    public float[] getSolutions(){
        return solutions;
    }
}
