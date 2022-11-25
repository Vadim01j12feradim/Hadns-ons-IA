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
public class opMutation {
    Generation generation = new Generation(0,0,0,0);
    public void setGeneration(Generation Gt){
        generation = Gt;
    }
    public List<Parent> crossover(Parent p1t, Parent p2t){
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
    public Parent selectParent(){
        Generation g = generation;
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
    }
}
