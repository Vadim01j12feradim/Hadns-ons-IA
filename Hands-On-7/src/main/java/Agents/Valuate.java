/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agents;

import Behaviours.CevaluatePopulation;
import jade.core.Agent;

/**
 *
 * @author sensei
 */
public class Valuate extends Agent{
    public void setup(){
        addBehaviour(new CevaluatePopulation(this));
    }
}
