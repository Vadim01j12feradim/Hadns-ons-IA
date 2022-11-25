/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agents;

import behaviours.CCrossover;
import jade.core.Agent;

/**
 *
 * @author sensei
 */
public class ACrossover extends Agent{
    public void setup(){
        addBehaviour(new CCrossover(this));
    }
}
