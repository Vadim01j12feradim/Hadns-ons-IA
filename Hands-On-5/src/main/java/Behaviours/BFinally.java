/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Interfaces.Request;
import Objects.Matrix;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        float[][] data = new float[4][8];
        float[] solutions = new float[4];
        float[] row = new float[8];
        float[][] dataSecond = new float[4][8];
        data = m.getData();
        solutions = m.getSolutions();
        
        System.out.println("**************************MATRIX*****************");
        for(int i=0;i<(data.length);i++){
            for(int j=0;j<data[i].length;j++){
                dataSecond[i][j] = data[i][j];
                System.out.print(data[i][j]+"\t");
            }
            System.out.println(" | "+solutions[i]);
        }
        
        System.out.println("**************************END MATRIX*****************");

        float[] sol = new float[4];
        sol = resolve(data,solutions);
        System.out.println("\n\n\n");
        for(int i=0;i<sol.length;i++){
            System.out.println(sol[i]);
        }
        
        System.out.println(sol[0]+" + "+sol[1]+"x1 + "+sol[2]+"x2 + "+sol[3]+"x3");
        

        
        float[][] datat = new float[3][6];
        float[] solutionsT = new float[3];
        float [][] generalsSolutions = new float [3][3];
        
        for(int i=0;i<datat.length;i++){
            
            for(int j=0;j<datat[i].length;j++){
                if(j>2){
                    if((j-3)==i)
                        datat[i][j] = 1;
                    else
                        datat[i][j] = 0;
                }else{
                    datat[i][j] = dataSecond[i][j];
                }
                System.out.print(" -> "+datat[i][j]);
            }
            solutionsT[i] = solutions[i];
            System.out.println(" | "+solutionsT[i]);
        }
        generalsSolutions[0] = resolve(datat,solutionsT);
        
        datat[0][0] = dataSecond[0][0];
        datat[0][1] = dataSecond[0][1];
        datat[0][2] = dataSecond[0][3];
        
        datat[1][0] = dataSecond[1][0];
        datat[1][1] = dataSecond[1][1];
        datat[1][2] = dataSecond[1][3];
        
        datat[2][0] = dataSecond[3][0];
        datat[2][1] = dataSecond[3][1];
        datat[2][2] = dataSecond[3][3];
        
        solutionsT[0] = solutions[0];
        solutionsT[1] = solutions[1];
        solutionsT[2] = solutions[3];

        datat = completeMatrix(datat);
        generalsSolutions[1] = resolve(datat,solutionsT);

        datat[0][0] = dataSecond[0][0];
        datat[0][1] = dataSecond[0][2];
        datat[0][2] = dataSecond[0][3];
        
        datat[1][0] = dataSecond[2][0];
        datat[1][1] = dataSecond[2][2];
        datat[1][2] = dataSecond[2][3];
        
        datat[2][0] = dataSecond[3][0];
        datat[2][1] = dataSecond[3][2];
        datat[2][2] = dataSecond[3][3];
        
        solutionsT[0] = solutions[0];
        solutionsT[1] = solutions[2];
        solutionsT[2] = solutions[3];

        datat = completeMatrix(datat);
        generalsSolutions[2] = resolve(datat,solutionsT);
        
        
        System.out.println(generalsSolutions[0][0]+" + "+generalsSolutions[0][1]+"x1 + "+
        generalsSolutions[0][2]+"x2");
        System.out.println(generalsSolutions[1][0]+" + "+generalsSolutions[1][1]+"x1 + "+
        generalsSolutions[1][2]+"x2");
        System.out.println(generalsSolutions[2][0]+" + "+generalsSolutions[2][1]+"x1 + "+
        generalsSolutions[2][2]+"x2");
        
        
        
        
        //System.out.println(solT[0]+" + "+solT[1]+"x1 + "+solT[2]+"x2");
        System.out.println("\tOne solution end");
        
        Request R = new Request();
        R.setFunction(sol,generalsSolutions);
        R.setVisible(true);
        
        System.out.println("\t\tMatriz");
        System.out.println("\t| a  | n    Σx1    Σx2    Σx3    | |  Σy   |");
        System.out.println("\t| b1 | Σx1  Σx²1   Σx1x2  Σx1x3  | |  Σx1y |");
        System.out.println("\t| b2 | Σx2  Σx1x2  Σx²2   Σx2x3  | |  Σx2y |");
        System.out.println("\t| b3 | Σx3  Σx1x3  Σx2x3  Σx²3   | |  Σx3y |");
        
        //end nuevas funci0ones
        
        myAgent.doDelete();
      
    }

    private float[][] completeMatrix(float[][] solutionsT){
        for (int i = 0; i < solutionsT.length; i++) {
            for (int j = solutionsT.length; j < solutionsT[i].length; j++) {
                if ((j-solutionsT.length)==i) {
                    solutionsT[i][j] = 1;
                } else {
                    solutionsT[i][j] = 0;
                }

            }
        }
        return solutionsT;
    }
    private float[] Pivote(float[] piv,int index){
        float[] pivote = new float[piv.length];
        float div = piv[index];
        System.out.println("**************NUEVOI PIVOTE*************");
        System.out.print(div+" -> ");
        for(int i=0;i<piv.length;i++){
            pivote[i] = (piv[i]/div);
            System.out.print(pivote[i]+" ");
        }
        System.out.println("");
        System.out.println("\n**************END PIVOTE*************");
        return pivote;
    }
    private float[] resolve(float[][] data, float[] solutions){
        float[] row = new float[data[0].length];
        
        for(int i=0;i<(data.length);i++){
            
            System.out.print("R"+(i+1)+"/");
            row = Pivote(data[i],i);
            System.out.print("R"+(i+1)+" | ");
            data[i] = row;
            
            /*Print drop*/
            
            for(int t=0;t<data[i].length;t++){
                    System.out.print(data[i][t]+" ");
                }
            System.out.println();
            /*End print*/
            for(int j=0;j<data.length;j++){
                
                if(j!=i){
                    data[j] = Rezize(row,data[j],i);
                    System.out.print("*R"+(i+1)+"+R"+(j+1)+"\t");
                    for(int t=0;t<data[j].length;t++){
                            System.out.print(data[j][t]+" ");
                        }
                }
                
                System.out.println();
            }
             System.out.println("\n\n");
        }
        
        float[][] mf = new float[data.length][data.length];
        for(int i=0;i<(data.length);i++){
            for(int j=0;j<data.length;j++){
                mf[j][i] =  solutions[i] * data[j][i+data.length];
            }
        }
        
        System.out.println("\n\n\n");
        float solT;
        float[] sol = new float[data.length];
        for(int i=0;i<mf.length;i++){
            solT = 0;
            for(int j=0;j<mf.length;j++){
                solT += mf[i][j];
                System.out.print(mf[i][j]+" ");
            }
            sol[i] = solT;
            System.out.println(" | "+solutions[i]);
        }
        return sol;
    }
    private float[] Rezize(float[] pivote,float[] row,int index){
        float div = - row[index];
        
        System.out.println("**************NUEVO REZIZE*************");
        System.out.print(div);
        for(int i=0;i<pivote.length;i++){
            row[i] = div*pivote[i]+row[i];
            
            System.out.print(row[i]+" ");
            
            System.out.print(div+" * "+pivote[i]+" + "+row[i]);
        }
        System.out.println("");
        System.out.println("\n**************END REZIZE*************");
        return row;
    }
}
