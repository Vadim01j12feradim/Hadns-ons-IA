/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ontology;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author sensei
 */
public class Generation implements Serializable{
    int populationSize;
    int elitismValue;
    int crossoverRate;
    int mutationRate;
    int maxRandom=100;
    public int getMaxRandom(){
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
        double V=0;
        System.out.println("-----------------------------------Generign initial population ------------------------------------------");
  	double fitness=0;
        while(i<populationSize){
            Insert = new Parent();
            V = (Math.random()*maxRandom+1);
            System.out.println("B0: "+V);
            Insert.setB0(V);
            
            V = (Math.random()*maxRandom+1);
            System.out.println("B1: "+V);
            Insert.setB1(V);
            System.out.println();
            fitness = getFitness(Insert);
            Insert.setFitness(fitness);
            index.add(Insert);
            i++;
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }
    public Parent getParent(int i){
        return index.get(i);
    }
    public double getFitness(Parent P){
         //211.18934343557603 B1: 23.359854302567342
        double B0 = P.getB0();//168.683; //211.18934343557603;//P.getB0();//568.683; //509.43841733655313;//P.getB0();//168.683;
        double B1 = P.getB1();//23.423; //23.359854302567342;//P.getB1();//23.423;//15.12400581418697;//P.getB1();//23.423;
        double[] y = {651,762,856,1063,1190,1298,1421,1440,1518};
        double[] x = {23,26,30,34,43,48,52,57,58};
        double acum=0;
        for (int i = 0; i < x.length; i++) {
            acum += Math.pow((y[i]-(B0+(B1*x[i]))),2);
        }
        //System.out.println("An div: "+acum);
        acum = acum/(x.length-2);
        acum = Math.sqrt(acum);
        System.out.println("ErrorAnt: "+acum);
        
        double error = 100/Math.abs(acum);
        System.out.println("Error: "+error);
        //double mul = (double)1/x.length;
        /*
        double error = 100/Math.abs(acum);
        System.out.println("Fitnes obtenido: "+error);
        return error;*/
        return error;
    }
    public int getpopulationSize(){
        return index.size();
    }
    
}
