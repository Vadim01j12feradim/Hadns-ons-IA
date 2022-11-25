/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Ontology.Generation;
import Ontology.Parent;
import Operations.Rulete;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sensei
 */
public class CCrossover extends CyclicBehaviour{
    ACLMessage mensaje, respuesta;
    Generation generation;
    public CCrossover(Agent a){
        myAgent  = a;
    }
    public void action(){
        mensaje = myAgent.blockingReceive();
        if(mensaje != null){
            try {
                evaluate();
                //JOptionPane.showMessageDialog(null, "Sali");
            } catch (UnreadableException ex) {
                System.out.println("Se produjo un error al enviar la gernacion en crossover");
            }
            mensaje = null;
            
        }else{
            block();    
        }
    }
    private void evaluate() throws UnreadableException{
                generation = (Generation)mensaje.getContentObject();
                System.out.println("\t**********************Agente Crossover realizando Crosshover*****************************");
                int val = 0;
                double fitnes=0;
                String representation = "";
                Generation newPopulation = new Generation(generation.getpopulationSize(),generation.getelitismValue(),generation.getcrossoverRate()
                            ,generation.getmutationRate());
                newPopulation.setElitismData(generation.getElitismData());
                
                int crossoverRate = generation.getcrossoverRate();
                
                
                Generation Parents = new Generation(generation.getpopulationSize(),generation.getelitismValue(),generation.getcrossoverRate()
                            ,generation.getmutationRate());
                
                Rulete R = new Rulete();
                R.setGeneration(generation);
                for(int i=0;i<generation.getpopulationSize();i++){
                        Parent parent = R.selectParent();
                        Parents.push(parent);
                    }
                double B0,B1;
                R.setGeneration(Parents);
                for(int i=0;i<Parents.getpopulationSize();i++){
                    B0 = Parents.getParent(i).getB0();
                    B1 = Parents.getParent(i).getB1();                   
                    fitnes = Parents.getParent(i).getFitness();
                    
                    System.out.println("B0: "+B0+" B1"+B1+" Futness: "+fitnes);
                    if(crossoverRate > (int)(Math.random()*100+1) && i < (Parents.getpopulationSize()- 3)){
                        System.out.println("Se genera crosshover");
                        
                        Parent secondParent = R.selectParent();
                        List<Parent> offspring = R.crossover(Parents.getParent(i), secondParent);
                        newPopulation.push(offspring.get(0));
                        newPopulation.push(offspring.get(1));
                        
                        i++;
                    }
                    else{
                        System.out.println("\tPasa igual");
                        //JOptionPane.showMessageDialog(null, "Is ok: "+Parents.getpopulationSize());
                        newPopulation.push((Parent)Parents.getParent(i));
                        //JOptionPane.showMessageDialog(null, "Is ok: "+Parents.getpopulationSize());
                    }
                    
                    
                }
                 System.out.println("\t******************************");
                
                generation = newPopulation;
                //JOptionPane.showMessageDialog(null, "Elitism size: "+generation.getSizeElitism()+" data: "+generation.getpopulationSize());
                System.out.println("\t****************************** Generacion con Crossover terminada *******************************");
                for(int i=0;i<generation.getpopulationSize();i++){
                    B0 = Parents.getParent(i).getB0();
                    B1 = Parents.getParent(i).getB1();                   
                    fitnes = Parents.getParent(i).getFitness();
                    System.out.println("B0: "+B0+" B1: "+B1+" Futness: "+fitnes);
                }
               
                respuesta = new ACLMessage(ACLMessage.INFORM);
                System.out.println("Enviando generacion a Mutation...");
                try {
                    respuesta.setContentObject(generation);
                } catch (IOException ex) {
                    System.out.println("Se produjo un error al enviar la gernacion en crossover");
                }
                respuesta.addReceiver(new AID("Mutation",AID.ISLOCALNAME));
                myAgent.send(respuesta);
                
            
    }
    /*
    private List<Parent> crossover(Parent p1t, Parent p2t){
        System.out.println("-------------------------Before Crossover-------------------");
        Parent p1 = new Parent();
        Parent p2 = new Parent();
        
        p1.setB0(p1t.getB0());
        p1.setB1(p1t.getB1());
        
        p1.setFitness(p1t.getFitness());
        
        p2.setB0(p2t.getB0());
        p2.setB1(p2t.getB1());
        
        p2.setFitness(p2t.getFitness());
        
        double B0,B1,fitnes;
        B0 = p1.getB0();
        B1 = p1.getB1();
        fitnes = p1.getFitness();
        System.out.println(B0+" "+B1+"x -> "+fitnes);
        
        B0 = p2.getB0();
        B1 = p2.getB1();
        fitnes = p2.getFitness();
        System.out.println(B0+" "+B1+"x -> "+fitnes);
        
        
        p2.setB1(p1.getB1());
        
        p1.setB1(B1);
        
        System.out.println("--------------------------After Crossover--------------------");
        
        B0 = p1.getB0();
        B1 = p1.getB1();
        
        fitnes = generation.getFitness(p1);
        p1.setFitness(fitnes);
        System.out.println(B0+" "+B1+"x -> "+fitnes);
        
        B0 = p2.getB0();
        B1 = p2.getB1();
        fitnes = generation.getFitness(p2);
        p2.setFitness(fitnes);
        System.out.println(B0+" "+B1+"x -> "+fitnes);
        
        List<Parent> retp =  new ArrayList<>();
        retp.add(p1);
        retp.add(p2);
        return retp;
    }
    private Parent selectParent(Generation g){
        Parent ret = new Parent();
        double totals = 0 ;
        double fitnes=0;
        
        System.out.println("\tNo. \tChromosomes\tFitness");
        double B0,B1;
        for(int i=0;i<g.getpopulationSize();i++){
            fitnes = g.getParent(i).getFitness();
            B0 = g.getParent(i).getB0();
            B1 = g.getParent(i).getB1();
            System.out.println(B0+" "+B1+"x -> "+fitnes);
            totals += fitnes;
            
        }
        System.out.println("\tPopulation total fitness: "+totals);
        
        double random = Math.random()*totals+0;
        System.out.println("\tRandom: "+random);
        totals = 0;
        for(int i=0;i<g.getpopulationSize();i++){
            fitnes = g.getParent(i).getFitness();
            totals += fitnes;
            if(totals>=random){
                B0 = g.getParent(i).getB0();
                B1 = g.getParent(i).getB1();
                System.out.println("Selected: "+B0+" "+B1+"x -> "+fitnes);
                ret.setB0(B0);
                ret.setB1(B1);

                ret.setFitness(fitnes);
                return ret;
            }
                
        }
        
        
        return ret;
    }*/
}
