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
    int a,b,c,d,e,f;
    int fitness;
    public int getA(){
        return a;
    }
    public void setA(int v){
        a = v;
    }
    public int getB(){
        return b;
    }
    public void setB(int v){
        b = v;
    }
    public int getC(){
        return c;
    }
    public void setC(int v){
        c = v;
    }
    public int getD(){
        return d;
    }
    public void setD(int v){
        d = v;
    }
    public int getE(){
        return e;
    }
    public void setE(int v){
        e = v;
    }
    public int getF(){
        return f;
    }
    public void setF(int v){
        f = v;
    }
    public void setFitness(int f){
        fitness = f;
    }
    public int getFitness(){
        return fitness;
    }
}