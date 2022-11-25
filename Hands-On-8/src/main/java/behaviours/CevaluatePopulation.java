/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import Ontology.Generation;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sensei
 */
public class CevaluatePopulation extends CyclicBehaviour{
    ACLMessage mensaje, respuesta;
    Generation generation;
    public CevaluatePopulation(Agent a){
        myAgent  = a;
    }
    public void action(){
        mensaje = myAgent.blockingReceive();
        if(mensaje != null){
            evaluate();
            mensaje = null;
            
        }else{
            block();    
        }
    }
    private void evaluate(){
        try {
                System.out.println("-------------------------------------------------------------Evaluando--------------------------------");
                generation = (Generation)mensaje.getContentObject();
                System.out.println("\tAgente Value evaluando los habitantes de la genracion...");
                int fitnes=0;
                String representation = "";
                generation.HallValues();
                int a,b,c,d,e,f;
                for(int i=0;i<generation.getpopulationSize();i++){
                    a = generation.getParent(i).getA();
                    b = generation.getParent(i).getB();
                    c = generation.getParent(i).getC();
                    d = generation.getParent(i).getD();
                    e = generation.getParent(i).getE();
                    f = generation.getParent(i).getF();
                    
                    fitnes = generation.getParent(i).getFitness();
                    
                    System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
                                    
                    if(fitnes==100){
                       JOptionPane.showMessageDialog(null,"SOLUCION: "+ a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+
                               (a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes+"\na = "+a+"\nb = "+b+"\nc = "+c+"\nd = "+d+"\ne = "+e+"\nf = "+f);
                               break;
                    }
                }
                generation.Sort();
                
                respuesta = new ACLMessage(ACLMessage.INFORM);
                System.out.println("Enviando generacion a Crossover...");
                try {
                    respuesta.setContentObject(generation);
                } catch (IOException ex) {
                    System.out.println("Se produjo un error al enviar la gernacion en evaluacion popuklation");
                }
                respuesta.addReceiver(new AID("Crossover",AID.ISLOCALNAME));
                myAgent.send(respuesta);
                
            } catch (UnreadableException ex) {
                Logger.getLogger(CevaluatePopulation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    
}
