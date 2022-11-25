/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ontology;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sensei
 */
public class Generation implements Serializable{
    int populationSize;
    int elitismValue;
    int crossoverRate;
    int mutationRate;
    
    int maxRandom = 20;
    public int getmaxRandom(){
        return maxRandom;
    }
    
    private List<Parent> index = new ArrayList<>();
    private List<Parent> indexElitism = new ArrayList<>();
    public Generation(int po,int el,int cr,int mu){
        populationSize = po;
        elitismValue = el;
        crossoverRate = cr;
        mutationRate = mu;
        
    }
    
    public void setElitismData(List<Parent> temnpE){
        indexElitism = temnpE;
    }
    public List<Parent>  getElitismData(){
        return indexElitism;
    }
    public int getSizeElitism(){
        return indexElitism.size();
    }
    public void Sort(){
        Parent aux;
        for(int i = 0;i < index.size()-1;i++){
            for(int j = 0;j < index.size()-i-1;j++){
                if(index.get(j+1).getFitness() >  index.get(j).getFitness()){    
                    aux = index.get(j+1);
                    index.set(j+1,index.get(j));
                    index.set(j,aux);
                }
            }
        }
        System.out.println("-------------------------------------------------- ORDENADO");

        System.out.println("-------------------------------------------------- NO ORDENADO");
        
        System.out.println("--------------------------------------------------Valores de elitismo");
        for(int i = 0;i < elitismValue;i++){
            aux = index.get(0); 
            indexElitism.add(aux);
            index.remove(0);
            //System.out.println(indexElitism.get(i).getValue());
        }
        System.out.println("Elitismo: "+indexElitism.size());
        System.out.println("--------------------------------------------------Valores de index");

        System.out.println("Index: "+index.size());

        
    }
    public void HallValues(){
        
        System.out.println("----------------------------------------------------------Reconfigurando");
        for(int i = 0;i < indexElitism.size();i++){
            System.out.println(indexElitism.get(i).getFitness());
            index.add(indexElitism.get(i));
        }
        System.out.println("----------------------------------------------------------Reconfigurando END");
        
        indexElitism.clear();

    }
    public int getmutationRate(){
        return mutationRate;
    }
    public int getelitismValue(){
        return elitismValue;
    }
    public int getcrossoverRate(){
        return crossoverRate;
    }
    public void push(Parent p){
        index.add(p);
    }
    public void GenerateInitialPopulation(){
        int i = 0;
        Parent Insert = new Parent();
        int V=0;
        
  	int fitness=0;
        while(i<populationSize){
            Insert  = new Parent();
            V = (int)(Math.random()*maxRandom+0);
            Insert.setA(V);
            V = (int)(Math.random()*maxRandom+0);
            Insert.setB(V);
            V = (int)(Math.random()*maxRandom+0);
            Insert.setC(V);
            V = (int)(Math.random()*maxRandom+0);
            Insert.setD(V);
            V = (int)(Math.random()*maxRandom+0);
            Insert.setE(V);
            V = (int)(Math.random()*maxRandom+0);
            Insert.setF(V);
            fitness = getFitness(Insert.getA(),Insert.getB(),Insert.getC(),Insert.getD(),Insert.getE(),Insert.getF());
            Insert.setFitness(fitness);
            index.add(Insert);
            i++;
        }
    }
    public Parent getParent(int i){
        return index.get(i);
    }
    public int getFitness(int a,int b, int c, int d, int e, int f){
        int fitness=0;
        fitness = a+(2*b)-(3*c)+d+(4*e)+f;
        System.out.print(a+"+(2*"+b+")-(3*"+c+")+"+d+"+(4*"+e+")+"+f+" = "+fitness);
        fitness = 100 - Math.abs(30-fitness);
        System.out.println(" -> "+fitness);
        return fitness;
    }
    public int getpopulationSize(){
        return index.size();
    }
    
}
