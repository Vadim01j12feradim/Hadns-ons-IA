/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author sensei
 */
public class swarm {
    double B0;
    double B1;
    double fitness;
    double xBestB0;
    double xBestB1;
    public void setxBestB0(double xBt){
        xBestB0 = xBt;
    }
    public void setxBestB1(double xBt){
        xBestB1 = xBt;
    }
    public void setB0(double B0t){
        B0 = B0t;
    }
    public void setB1(double B1t){
        B1 = B1t;
    }
    public void setFitness(double fit){
        fitness = fit;
    }
    public double getB0(){
        return B0;
    }
    public double getB1(){
        return B1;
    }
    public double getFitness(){
        return fitness;
    }
    public double getxBestB0(){
        return xBestB0;
    }
    public double getxBestB1(){
        return xBestB1;
    }
}
