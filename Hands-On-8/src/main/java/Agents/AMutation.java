/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agents;

import behaviours.CMutation;
import jade.core.Agent;

/**
 *
 * @author sensei
 */
public class AMutation extends Agent{
        public void setup(){
        addBehaviour(new CMutation(this));
    }
}

