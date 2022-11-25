/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Interfaces.RequestVar;
import Objects.Matrix;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author sensei
 */
public class BFinally extends OneShotBehaviour{
    ACLMessage mensaje;
    Matrix m = new Matrix();
    public BFinally(Agent a){
        
        myAgent  = a;
    }
    public void action(){
        mensaje = myAgent.blockingReceive();
        if(mensaje != null){
            try {
                Resolve();
            } catch (UnreadableException ex) {
                System.out.println("Error al caragr el archivo");
            }
            mensaje = null;
            
        }else{
            block();    
        }
    }
    private void Resolve() throws UnreadableException{
        m = (Matrix)mensaje.getContentObject();
        System.out.println("Recibi la matriz");
        
        float[][] data = new float[4][4];
        float[] solutions = new float[4];
        
        
        data = m.getData();
        
        solutions = m.getSolutions();
        System.out.println("**************************MATRIX*****************");
        
        for(int i=0;i<(data.length);i++){
            for(int j=0;j<data[i].length;j++){
                System.out.print(data[i][j]+"\t");
            }
            System.out.println(" | "+solutions[i]);
        }
        /*
       		Matriz
	| a  | n    Σx1    Σx2    Σx3    | |  Σy   |
	| b1 | Σx1  Σx²1   Σy2  Σx1x3  | |  Σx1y |
	| b2 | Σx2  Σy2  Σx²2   Σx2x3  | |  Σx2y |
	| b3 | Σx3  Σx1x3  Σx2x3  Σx²3   | |  Σx3y | 
       
        */
        
        float[] b1 = new float[3];
        float[] b0 = new float[3];
        float xy=0,n=0,xp=0,yp=0,x2=0,x2p=0,y=0;

        System.out.println("calculating solutions... ");
        n = data[0][0];
        yp = solutions[0]/n;
        for(int i=0;i<3;i++){
            xy = solutions[i+1];
            System.out.println("xy: "+xy);
            xp = data[0][i+1]/n;
            System.out.println("xp: "+xp);
            x2 = data[i+1][i+1];
            System.out.println("x2: "+x2);
            x2p = xp*xp;
            System.out.println("x2p: "+x2p);
            b1[i] = xy-(n*xp*yp);
            System.out.println("b1temp One: "+b1[i]);
            b1[i] = b1[i]/(x2-(n*x2p));
            System.out.println("b1Finally: "+b1[i]);
            b0[i] = (solutions[0]/n)-(b1[i]*xp);
            System.out.println("b0Finally: "+b0[i]);
        }
        System.out.println("computation of final solutions finished... ");
        System.out.println("Funtion by TV: "+b0[0]+" + "+b1[0]+"x");
        System.out.println("Funtion by Radio: "+b0[1]+" + "+b1[1]+"x");
        System.out.println("Funtion by Newspape: "+b0[2]+" + "+b1[2]+"x");
        
        float[][] xay = new float[4][200];
        xay = m.getXY();
        
        
        RequestVar Window = new RequestVar();
        Window.setForms(b0,b1,xay);
        Window.setVisible(true);
        myAgent.doDelete();
        
    }

}
