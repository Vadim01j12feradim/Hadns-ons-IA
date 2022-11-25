/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Objects.Dataset;
import Objects.Expresion;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sensei
 */
public class BLoadDataset extends SimpleBehaviour{
    ACLMessage mensaje, respuesta;
    Dataset data = new Dataset();
    float[][] xy = new float[4][200];
    double[] xTV;
    double[] xRadio;
    double[] xNewspape;
    double[] y;
    Expresion exp;

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
            if(i<y.length)
                readFile();
            i++;
}
    public void readFile(){
        Expresion exp = new Expresion();
        exp.setTV((float) xTV[i]);
        exp.setRadio((float) xRadio[i]);
        exp.setNewspaper((float) xNewspape[i]);
        exp.setSales((float) y[i]);
        data.setExpresion(exp);
        
        xy[0][i] = (float) xTV[i];
        xy[1][i] = (float) xRadio[i];
        xy[2][i] = (float) xNewspape[i];
        xy[3][i] = (float) y[i];
        data.setExpresion(exp);
        System.out.println(xTV[i]+" "+xRadio[i]+" "+xNewspape[i]+" "+y[i]);

        }
        public boolean done(){
            if(i==(y.length+1)){
                data.setXY(xy);
                myAgent.addBehaviour(new BAdd(myAgent,data));
                return true;
            }
                   
            return false;
           }
}
