/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ontology;

import java.io.Serializable;

/**
 *
 * @author sensei
 */
public class Parent implements Serializable{
    double fitness=0;//individual fitness
    double B0=0;
    double B1=0;
    
    public void setB0(double b0){
        B0 = b0;
    }
    public void setB1(double b1){
        B1 = b1;
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
    public void setFitness(double ft){
        fitness = ft;
    }
}