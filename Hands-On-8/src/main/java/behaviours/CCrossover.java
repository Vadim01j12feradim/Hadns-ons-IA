/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package behaviours;

import Ontology.Generation;
import Ontology.Parent;
import Operations.opMutation;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
                int fitnes=0;
                String representation = "";
                Generation newPopulation = new Generation(generation.getpopulationSize(),generation.getelitismValue(),generation.getcrossoverRate()
                            ,generation.getmutationRate());
                newPopulation.setElitismData(generation.getElitismData());
                
                int crossoverRate = generation.getcrossoverRate();
                
                
                Generation Parents = new Generation(generation.getpopulationSize(),generation.getelitismValue(),generation.getcrossoverRate()
                            ,generation.getmutationRate());
                
                opMutation M = new opMutation();
                M.setGeneration(generation);
                for(int i=0;i<generation.getpopulationSize();i++){
                        Parent parent = M.selectParent();
                        Parents.push(parent);
                    }
                int a,b,c,d,e,f;
                M.setGeneration(Parents);
                for(int i=0;i<Parents.getpopulationSize();i++){
                    a = Parents.getParent(i).getA();
                    b = Parents.getParent(i).getB();
                    c = Parents.getParent(i).getC();
                    d = Parents.getParent(i).getD();
                    e = Parents.getParent(i).getE();
                    f = Parents.getParent(i).getF();
                    fitnes = Parents.getParent(i).getFitness();
                    System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
                    if(crossoverRate > (int)(Math.random()*100+1) && i < (Parents.getpopulationSize()- 3)){
                        System.out.println("Se genera crosshover");
                        
                        Parent secondParent = M.selectParent();
                        List<Parent> offspring = M.crossover(Parents.getParent(i), secondParent);
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
                    a = generation.getParent(i).getA();
                    b = generation.getParent(i).getB();
                    c = generation.getParent(i).getC();
                    d = generation.getParent(i).getD();
                    e = generation.getParent(i).getE();
                    f = generation.getParent(i).getF();
                    fitnes = generation.getParent(i).getFitness();
                    System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
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
        System.out.println("Before Crossover");
        Parent p1 = new Parent();
        Parent p2 = new Parent();
        
        p1.setA(p1t.getA());
        p1.setB(p1t.getB());
        p1.setC(p1t.getC());
        p1.setD(p1t.getD());
        p1.setE(p1t.getE());
        p1.setF(p1t.getF());
        
        p1.setFitness(p1t.getFitness());
        
        p2.setA(p2t.getA());
        p2.setB(p2t.getB());
        p2.setC(p2t.getC());
        p2.setD(p2t.getD());
        p2.setE(p2t.getE());
        p2.setF(p2t.getF());
        
        p2.setFitness(p2t.getFitness());
        
        int a,b,c,d,e,f,fitnes;
        a = p1.getA();
        b = p1.getB();
        c = p1.getC();
        d = p1.getD();
        e = p1.getE();
        f = p1.getF();
        fitnes = p1.getFitness();
        System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
        
        a = p2.getA();
        b = p2.getB();
        c = p2.getC();
        d = p2.getD();
        e = p2.getE();
        f = p2.getF();
        fitnes = p2.getFitness();
        System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
        
        
        p2.setD(p1.getD());
        p2.setE(p1.getE());
        p2.setF(p1.getF());
        
        p1.setD(d);
        p1.setE(e);
        p1.setF(f);
        
        System.out.println("After Crossover");
        
        a = p1.getA();
        b = p1.getB();
        c = p1.getC();
        d = p1.getD();
        e = p1.getE();
        f = p1.getF();
        
        fitnes = generation.getFitness(a,b,c,d,e,f);
        p1.setFitness(fitnes);
        System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
        
        a = p2.getA();
        b = p2.getB();
        c = p2.getC();
        d = p2.getD();
        e = p2.getE();
        f = p2.getF();
        fitnes = generation.getFitness(a,b,c,d,e,f);
        p2.setFitness(fitnes);
        System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
        
        List<Parent> retp =  new ArrayList<>();
        retp.add(p1);
        retp.add(p2);
        return retp;
    }
    private Parent selectParent(Generation g){
        Parent ret = new Parent();
        int totals = 0 ;
        int fitnes=0;
        
        System.out.println("\tNo. \tChromosomes\tFitness");
        int a,b,c,d,e,f;
        for(int i=0;i<g.getpopulationSize();i++){
            fitnes = g.getParent(i).getFitness();
            a = g.getParent(i).getA();
            b = g.getParent(i).getB();
            c = g.getParent(i).getC();
            d = g.getParent(i).getD();
            e = g.getParent(i).getE();
            f = g.getParent(i).getF();
            System.out.println(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
            totals += fitnes;
            
        }
        System.out.println("\tPopulation total fitness: "+totals);
        int random = (int)(Math.random()*totals+1);
        totals = 0;
        for(int i=0;i<g.getpopulationSize();i++){
            fitnes = g.getParent(i).getFitness();
            totals += fitnes;
            if(totals>=random){
                a = g.getParent(i).getA();
                b = g.getParent(i).getB();
                c = g.getParent(i).getC();
                d = g.getParent(i).getD();
                e = g.getParent(i).getE();
                f = g.getParent(i).getF();
                System.out.println("random: "+random+"Value: "+totals+"\n"+a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+(a+(2*b)-(3*c)+d+(4*e)+f)+" -> "+fitnes);
                ret.setA(a);
                ret.setB(b);
                ret.setC(c);
                ret.setD(d);
                ret.setE(e);
                ret.setF(f);

                ret.setFitness(fitnes);
                return ret;
            }
                
        }
        
        
        return ret;
    }*/
}

