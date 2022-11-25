/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Interfaces.input;
import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

/**
 *
 * @author sensei
 */
public class Intento6 extends SimpleBehaviour{
        double[] x;
        double[] y;
        int maxSteps=500000;
        int step =0;
        double B0;
        double B1;
        double learning_rate;
        double minError=5.3;
        double error;
        double aB0;
        double aB1;
        public Intento6(Agent a, double[] xt,double[] yt){
        myAgent  = a;
        x = new double[xt.length];
        y = new double[yt.length];
        x = xt;
        y = yt;
        setep1();  
        
    }
    public void action(){          
        aB0 = getStep22();
        aB1 = getStep23(); 
        System.out.println("aB0: "+aB0);
        System.out.println("aB1: "+aB1);
        step4();
        error = step3();
        System.out.println("Error: "+error);
        step++;
            }
    private void setep1() {
        B0 = Math.random()*8+1;
        B1 = Math.random()*8+1;
        learning_rate = 0.000008;
        System.out.println("---------------Initial Values---------------------------");
        System.out.println("B0: "+B0+"\nB1: "+B1+"\nLearn: "+learning_rate);
         System.out.println("------------------------------------------------------------");
    }
    
    private double getStep22() {
        double acum=0;
        for (int i = 0; i < x.length; i++) {
            acum += y[i] - B0 - (B1*x[i]);

        }
        double mul = (double)-2/x.length;
        acum = mul*acum;
        return acum;
    }
    private double getStep23() {
        double acum=0;
        for (int i = 0; i < x.length; i++) {
            acum += (y[i]-B0-(B1*x[i]))*x[i];
        }
        double mul = (double)-2/x.length;
        acum = mul*acum;
        return acum;
    }
    private double step3() {
        double acum=0;
        for (int i = 0; i < x.length; i++) {
            acum += Math.pow((y[i]-(B0+(B1*x[i]))), 2);
        }
        double mul = (double)1/x.length;
        acum = mul*acum;
        error = acum;
        return acum;
    }
    private void step4() {
        B0 = B0 - (learning_rate*aB0);
        B1 = B1 - (learning_rate*aB1);
        System.out.println("B0: "+B0);
        System.out.println("B1: "+B1);
    }
    public boolean done(){
            if (step>=maxSteps || error<=minError){
                System.out.println("---------------------------Valores finales-----------------------");
                System.out.println("B0: "+B0+"\nB1: "+B1);
                System.out.println("Error: "+error);
                System.out.println("Iteraciones: "+step);
                System.out.println(B0+" + "+B1+"x");
                System.out.println("-----------------------------------------------------------------");
                myAgent.doDelete();
                input q = new input();
                q.setVisible(true);
                q.setValues(B0, B1, x, y);
                
                return true;
            }
                
            return false;
        }
        
}