/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Ontology.Generation;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;

/**
 *
 * @author sensei
 */
public class CreateData extends OneShotBehaviour{
    Generation generation;
    ACLMessage mensaje, respuesta;
    public CreateData(Agent a){
        myAgent = a;
    }
    public void action(){
        mensaje = new ACLMessage(ACLMessage.INFORM);
        System.out.println("Generando primera generacion...");
        generation =  new Generation(24,2,25,25);
        generation.GenerateInitialPopulation();
        
        try {
            mensaje.setContentObject(generation);
        } catch (IOException ex) {
            System.out.println("Se produjo un error al enviar la gernacion en create data");
        }
        mensaje.addReceiver(new AID("Valuate",AID.ISLOCALNAME));
        myAgent.send(mensaje);
        myAgent.doDelete();

    }
}
