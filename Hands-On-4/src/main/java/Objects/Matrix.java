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
    float[][] data = new float[4][4];
    float[] solutions = new float[4];
    float[][] xy = new float[4][200];
        public void setXY(float[][] d){
        xy = d;
    }
    public float[][] getXY(){
        return xy;
    }
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
