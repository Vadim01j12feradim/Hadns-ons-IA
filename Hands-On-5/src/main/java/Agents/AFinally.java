/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agents;

import Behaviours.BFinally;
import jade.core.Agent;

/**
 *
 * @author sensei
 */
public class AFinally extends Agent{
        public void setup(){
        System.out.println("Hola soy el agente finllayt");
        addBehaviour(new BFinally(this));
    }
}
