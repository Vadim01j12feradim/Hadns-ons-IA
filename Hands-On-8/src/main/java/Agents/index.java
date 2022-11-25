/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agents;

import Ontology.Generation;
import behaviours.CreateData;
import jade.core.Agent;

/**
 *
 * @author sensei
 */
public class index extends Agent{
    Generation generation;
      protected void setup() {
        addBehaviour(new CreateData(this));
      }
    
      
}