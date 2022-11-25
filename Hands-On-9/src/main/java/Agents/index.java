/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agents;

import Behaviours.init;
import jade.core.Agent;

/**
 *
 * @author sensei
 */

public class index extends Agent{
    protected void setup(){
        System.out.println("Hello i am "+getLocalName()+" e inicializare el algoritmo");
        double[] y = {651,762,856,1063,1190,1298,1421,1440,1518};
        double[] x = {23,26,30,34,43,48,52,57,58};
        addBehaviour(new init(this,x,y));
    }
}