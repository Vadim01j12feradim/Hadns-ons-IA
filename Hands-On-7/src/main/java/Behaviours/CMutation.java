/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Ontology.Generation;
import Ontology.Parent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;

/**
 *
 * @author sensei
 */
public class CMutation extends CyclicBehaviour{
    
    ACLMessage mensaje, respuesta;
    Generation generation;
    public CMutation(Agent a){
        myAgent  = a;
    }
    public void action(){
        mensaje = myAgent.blockingReceive();
        if(mensaje != null){
            try {
                evaluate();
            } catch (UnreadableException ex) {
                System.out.println("Se produjo un error en la mutacion en mutation");
            } catch (IOException ex) {
                System.out.println("Se produjo un error en la mutacion en mutation");
            }
            mensaje = null;
            
        }else{
            block();    
        }
    }
    private double getGene(int i, Parent P){
                switch(i){
                        case 0:
                            return P.getB0();
                       case 1:
                            return P.getB1();
                       default:
                           return 0;
                    }
    }
   private Parent setGene(int i, Parent P,double value){
                value += Math.random()*0.5+0;
                switch(i){
                        case 0:
                            P.setB0(value);
                       case 1:
                            P.setB1(value);
                    }
                return P;
    }
    private void evaluate() throws UnreadableException, IOException{
                generation = (Generation)mensaje.getContentObject();
                System.out.println("\t***************************Agente Mutation realizando mutacion*******************************");
                int val = 0;
                double fitnes=0;
                Generation newPopulation = new Generation(generation.getpopulationSize(),generation.getelitismValue(),generation.getcrossoverRate()
                            ,generation.getmutationRate());
                newPopulation.setElitismData(generation.getElitismData());
                Parent individual;
                int mutationRate = generation.getmutationRate();
                double newGene;
                Parent pMutated;
                double B0,B1;
                String representation = "";
                String newGeneIndividual;
                String gen;
                for(int i=0;i<generation.getpopulationSize();i++){//Recorrido general
                    individual = new Parent();
                    
                    individual.setB0(generation.getParent(i).getB0());
                    individual.setB1(generation.getParent(i).getB1());
                    individual.setFitness(generation.getParent(i).getFitness());
                    B0 = individual.getB0();
                    B1 = individual.getB1();
                    fitnes = individual.getFitness();

                    System.out.println(B0+" "+B1+"x -> "+fitnes);
                    pMutated = new Parent();
                    
                    for(int j=0;j<2;j++){
                        newGene = getGene(j,individual);
                        gen = "";
                        representation = String.format("%10s", Integer.toBinaryString((int)newGene)).replaceAll(" ", "0");
                        //Opcional
                        
                        if(representation.length()>8){
                            representation = representation.substring(representation.length()-8, representation.length());   
                            System.out.println("<<<<<<<<<<<<<<<<<<<<<<< "+representation+" >>>>>>>>>>>>>>>>>>>>>>>>>>");
                            }
                                       
                        //Opcional
                        for (int j2 = 0; j2 < representation.length(); j2++) {
                            newGeneIndividual = String.valueOf(representation.charAt(j2));
                            int random = (int)(Math.random()*100+1);
                            if(mutationRate > random){
                                System.out.println("Se genera mutacion");
                                newGeneIndividual = "1";
                                if(representation.charAt(j)=='1'){
                                    newGeneIndividual = "0";
                                    }
                            }
                            gen += String.valueOf(newGeneIndividual);
                            //gen += Math.random()*0.5+0;
                        }
                        System.out.println("New gen: "+gen);
                        pMutated = setGene(j,pMutated,Integer.parseInt(gen, 2));
                    }
                    
                    B0 = pMutated.getB0();
                    B1 = pMutated.getB1();
                    fitnes =  generation.getFitness(pMutated);
                    System.out.println(B0+" "+B1+"x -> "+fitnes);
                    pMutated.setFitness(fitnes);
                    newPopulation.push(pMutated);
                }
                
                
                generation = newPopulation;
                System.out.println("********************************** Generacion final Mutada *******************************************");
                for(int i=0;i<generation.getpopulationSize();i++){
                    individual = generation.getParent(i);
                    B0 = individual.getB0();
                    B1 = individual.getB1();
                    fitnes = individual.getFitness();
                    System.out.println(B0+" "+B1+"x -> "+fitnes);
                    
                }
                
                respuesta = new ACLMessage(ACLMessage.INFORM);
                System.out.println("Enviando generacion a Valuate...");
                respuesta.setContentObject(generation);
                
                respuesta.addReceiver(new AID("Valuate",AID.ISLOCALNAME));
                myAgent.send(respuesta);
    }
}
