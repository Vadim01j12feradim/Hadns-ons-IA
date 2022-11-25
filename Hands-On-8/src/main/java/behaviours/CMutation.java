/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

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
    private int getGene(int i, Parent P){
                switch(i){
                        case 0:
                            return P.getA();
                       case 1:
                            return P.getB();
                       case 2:
                            return P.getC();
                       case 3:
                            return P.getD();
                       case 4:
                            return P.getE();
                       case 5:
                            return P.getF();
                       default:
                           return 0;
                    }
    }
   private Parent setGene(int i, Parent P,int value){
                switch(i){
                        case 0:
                            P.setA(value);
                       case 1:
                            P.setB(value);
                       case 2:
                            P.setC(value);
                       case 3:
                            P.setD(value);
                       case 4:
                            P.setE(value);
                       case 5:
                            P.setF(value);
                    }
                return P;
    }
    private void evaluate() throws UnreadableException, IOException{
                generation = (Generation)mensaje.getContentObject();
                System.out.println("\t***************************Agente Mutation realizando mutacion*******************************");
                int val = 0;
                int fitnes=0;
                Generation newPopulation = new Generation(generation.getpopulationSize(),generation.getelitismValue(),generation.getcrossoverRate()
                            ,generation.getmutationRate());
                newPopulation.setElitismData(generation.getElitismData());
                Parent individual;
                int mutationRate = generation.getmutationRate();
                int newGene;
                Parent pMutated;
                int a,b,c,d,e,f;
                for(int i=0;i<generation.getpopulationSize();i++){
                    individual = new Parent();
                    
                    individual.setA(generation.getParent(i).getA());
                    individual.setB(generation.getParent(i).getB());
                    individual.setC(generation.getParent(i).getC());
                    individual.setD(generation.getParent(i).getD());
                    individual.setE(generation.getParent(i).getE());
                    individual.setF(generation.getParent(i).getF());
                    individual.setFitness(generation.getParent(i).getFitness());
                    a = individual.getA();
                    b = individual.getB();
                    c = individual.getC();
                    d = individual.getD();
                    e = individual.getE();
                    f = individual.getF();
                    fitnes = individual.getFitness();

                    System.out.println("Antes: "+a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
                    pMutated = new Parent();
                    
                    for(int j=0;j<6;j++){
                        newGene = getGene(j,individual);
                        
                        int random = (int)(Math.random()*100+1);
                        if(mutationRate > random){
                            System.out.println("Generando mutacion...............................");
                            newGene = (int)(Math.random()*generation.getmaxRandom()+0);
                            while(newGene == getGene(j,individual))
                                newGene = (int)(Math.random()*generation.getmaxRandom()+0);
                        }
                        System.out.println("New gene: "+newGene);
                        pMutated = setGene(j,pMutated,newGene);
                    }
                    a = pMutated.getA();
                    b = pMutated.getB();
                    c = pMutated.getC();
                    d = pMutated.getD();
                    e = pMutated.getE();
                    f = pMutated.getF();
                    fitnes =  generation.getFitness(a, b, c, d, e, f);
                    System.out.println("Despues: "+a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
                    pMutated.setFitness(fitnes);
                    newPopulation.push(pMutated);
                }
                
                
                generation = newPopulation;
                System.out.println("********************************** Generacion final Mutada *******************************************");
                for(int i=0;i<generation.getpopulationSize();i++){
                    individual = generation.getParent(i);
                    a = individual.getA();
                    b = individual.getB();
                    c = individual.getC();
                    d = individual.getD();
                    e = individual.getE();
                    f = individual.getF();
                    fitnes = individual.getFitness();
                    System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
                    
                }
                
                respuesta = new ACLMessage(ACLMessage.INFORM);
                System.out.println("Enviando generacion a Valuate...");
                respuesta.setContentObject(generation);
                
                respuesta.addReceiver(new AID("Valuate",AID.ISLOCALNAME));
                myAgent.send(respuesta);
    }
}
