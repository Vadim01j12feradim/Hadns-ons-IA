/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operations;

import Ontology.Generation;
import Ontology.Parent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sensei
 */
public class Rulete {
    Generation generation = new Generation(0,0,0,0);
    public void setGeneration(Generation Gt){
        generation = Gt;
    }
    public List<Parent> crossover(Parent p1t, Parent p2t){
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
    public Parent selectParent(){
        Generation g = generation;
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
    }
}
