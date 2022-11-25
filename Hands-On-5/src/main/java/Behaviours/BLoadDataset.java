/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;
import Objects.Dataset;
import Objects.Expresion;
import Objects.Matrix;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author sensei
 */
public class BLoadDataset extends SimpleBehaviour{
    ACLMessage mensaje, respuesta;
    double[] xTV;
    double[] xRadio;
    double[] xNewspape;
    double[] y;
    Dataset data = new Dataset();
    
    public BLoadDataset(Agent a,double[] xTVt,double[] xRadiot,double[] xNewspapet,double[] yt){
        myAgent  = a;
        xTV = new double[xTVt.length];
        xRadio = new double[xRadiot.length];
        xNewspape = new double[xNewspapet.length];;
        y = new double[yt.length];
        xTV = xTVt;
        xRadio = xRadiot;
        xNewspape = xNewspapet;
        y = yt;
        
        
    }
    int i=0;
    
    public void action(){       
            //mensaje = new ACLMessage(ACLMessage.INFORM);
            //System.out.println("Loading Dataset...");
            if(i<y.length)
               readFile();
            i++;

}
    private void readFile(){   
        Expresion exp = new Expresion();
        exp.setTV((float) xTV[i]);
        exp.setRadio((float) xRadio[i]);
        exp.setNewspaper((float) xNewspape[i]);
        exp.setSales((float) y[i]);
        data.setExpresion(exp);
        System.out.println(xTV[i]+" "+xRadio[i]+" "+xNewspape[i]+" "+y[i]);
        }
     public boolean done(){
         if(i==(y.length+1)){
             myAgent.addBehaviour(new BAdd(myAgent,data));
             return true;
         }
                
         return false;
        }
     
}
