/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

/**
 *
 * @author sensei
 */
public class Intento4 extends SimpleBehaviour{
    public Intento4(Agent a){
        myAgent  = a;
    }
    int k=0;
    double learn = 0.01;//(Math.random()*0.9+0.1);
    double xk = (Math.random()*9+0);
    public void action(){    
            iteration();
            k++;
            }
    public void iteration(){
            System.out.println(xk);
        }

        public boolean done(){
            if(k==5)
                return true;
            return false;
        }
}