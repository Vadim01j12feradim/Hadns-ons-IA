/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.apache.commons.math3.stat.StatUtils;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;


/**
 *
 * @author sensei
 */
public class BehabiousFinal extends SimpleBehaviour{
        double lr = 0.01;
        int iters = 5000;
        double min_perdida = 1;
        double b1 = 4;
        double b2 = 6;
        double[][] betas = new double[iters][2];
        double[] y_gorro ;      
        double error;
        double[] errores = new double[iters];
        double[] y,x1;
        int i;
   
    public BehabiousFinal(Agent a, double[] x1t,double[] yt){
        myAgent  = a;
        y = yt;
        x1 = x1t;
        betas[0][0] = b1;
        betas[0][1] = b2;
        errores[0] = error;
        y_gorro = new double[y.length];
        i=0;
        y_gorro =  get_y_gorro(b1,b2,x1);
        error  = recm(y,y_gorro);
    }
    
       

    public void action(){    
            y_gorro = get_y_gorro(b1,b2,x1);
            error = recm(y,y_gorro);
            errores[i] = error;
            if(error>min_perdida){
                System.out.println("Eror: "+error);
                System.out.println("Beta1: "+grad(y,y_gorro,x1,true));
                System.out.println("Beta2: "+grad(y,y_gorro,x1,false));
                b1 = b1-lr*grad(y,y_gorro,x1,true);
                b2 = b2-lr*grad(y,y_gorro,x1,false);
                System.out.println("Beta1: "+b1+" Beta2: "+b2);

            }
            betas[i][0] = b1;
            betas[i][1] = b2;                   
            System.out.println("---------------Beta1: "+b1+" Beta2: "+b2+"-------------------------------------");
            System.out.println("Eror: "+error);           
            i++;
            System.out.println("Iteration: "+i);
            }

        
    public double recm(double[] y, double[] y_gorro){
        //System.out.println("\tX\tY\tY_Predict\tReciduo\tReciduo_Absoluto_Div\tReciduo_Pow");
        double residuoActual;
        double resCount=0;
        double resCountAbs=0;
        double resPowAcum=0;
        for (int j = 0; j < y_gorro.length; j++) {
            //yPredict = b_0 + b_1*trX[j];
            // es y_gorro
            //residuoActual = trY[j]-yPredict;
            residuoActual = y[j]-y_gorro[j];
            resPowAcum += Math.pow(residuoActual, 2);
            resCount += residuoActual;
            resCountAbs += Math.abs(residuoActual/y_gorro.length);
            //System.out.println(j+"\t"+y_gorro[j]+"\t"+y[j]+"\t"+y_gorro[j]+"\t"+residuoActual+"\t"+Math.abs(residuoActual/y_gorro.length)+"\t"+Math.pow(residuoActual, 2));
        }
        //System.out.println("\t\t\t\tTotal_eciduo: "+resCount+"\tTotal_reciduo_Abs: "+resCountAbs+"\tTotalSqrt: "+Math.sqrt(resPowAcum/y_gorro.length));
        return Math.sqrt(resPowAcum/y_gorro.length);
    }
    
    public double[] get_y_gorro(double b1,double b2, double x1[]) {
        double[] y_gorro = new double[x1.length];
        //b1*x1 + b2;
        //System.out.println("---------------get_y_gorro----------------------");
        for (int i = 0; i < x1.length; i++) {
            y_gorro[i] = b1 + b2*x1[i];
            //System.out.println(i+"._ "+b1+" + "+b2+" * "+x1[i]+" = "+y_gorro[i]);
            
        }
        //System.out.println("------------------------------------------------------\n\n");
        // for (double d : y_gorro) {
        //     System.out.println(d);
        // }
        return y_gorro;
    }
    
    public double grad(double y[], double y_gorro[], double[] x,boolean ind) {//posivbel error
        double npSum = 0;
        //System.out.println("------------------Grad------------------------");
        for (int j = 0; j < x.length; j++) {
            if(!ind){
                npSum += (y[j] - y_gorro[j])*-x[j];
                //System.out.println("("+y[j]+" - "+y_gorro[j]+") *- "+x[j]+" = "+(y[j] - y_gorro[j])*-x[j]);
                //System.out.println("Suma: "+npSum);
            }
            else
                npSum += (y[j] - y_gorro[j]);
            //System.out.println("Calc "+npSum);
        }
        double smb = npSum/Math.abs(npSum);
        npSum = Math.sqrt(Math.abs(npSum))/x.length;
        npSum = npSum*smb;
        //ystem.out.println("Total: "+npSum+" length: "+x.length);
        //System.out.println("-----------------------------------------------");4.009787740499003
        return npSum;
    }
        
        public void generateGrapic(double[][] betas,double[] x1t,double[] errors){
        
        
            Plot2DPanel plot = new Plot2DPanel();
            JTextArea resultados = new JTextArea();
            
            for (int i = 0; i < betas.length; i++) {
                double[] y = new double[x1t.length];
                for (int j = 0; j < x1t.length; j++) {
                    y[j] = betas[i][0] +  betas[i][1]*x1[j];
                }
                plot.addLinePlot("Datos"+i, x1t,y);
            }
            plot.addScatterPlot("Datos plot", x1,y);
            plot.addLegend("Regresion");
            
            BaseLabel titulo = new BaseLabel("Gradiente descendiente",Color.BLUE,0.5,1.1);
            plot.addPlotable(titulo);
            resultados.setBackground(Color.LIGHT_GRAY);
            resultados.append("\nValor minimo: "+StatUtils.min(y));
            resultados.append("\nValor maximo: "+StatUtils.max(y));
            resultados.append("\nValor promedio: "+StatUtils.mean(y));
            resultados.append("\nVarianza: "+StatUtils.variance(y));
            resultados.append("\nromedio geometrico: "+StatUtils.geometricMean(y));
            resultados.append("\nsuma: "+StatUtils.sum(y));
            resultados.append("\nProduto: "+StatUtils.product(y));
            
            JFrame frame = new JFrame("Frame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900,700);
            frame.add(plot,BorderLayout.CENTER);
            frame.add(resultados,BorderLayout.SOUTH);
            frame.setVisible(true);
            ////////////////////////////////////////////////////////////////////////////
            Plot2DPanel plot1 = new Plot2DPanel();
            JTextArea resultados1 = new JTextArea();
            double[] xs = new double[errors.length];
            for (int i = 0; i < errors.length; i++) {
                xs[i] = i;
            }
            plot1.addLinePlot("Line", xs,errors);
            plot1.addScatterPlot("Datos",xs,errors);
            plot1.addLegend("Regresion");
            
            BaseLabel titulo1 = new BaseLabel("Gradiente descendiente",Color.BLUE,0.5,1.1);
            plot1.addPlotable(titulo1);
            resultados1.setBackground(Color.LIGHT_GRAY);
            resultados1.append("\nValor minimo: "+StatUtils.min(errors));
            resultados1.append("\nValor maximo: "+StatUtils.max(errors));
            resultados1.append("\nValor promedio: "+StatUtils.mean(errors));
            resultados1.append("\nVarianza: "+StatUtils.variance(errors));
            resultados1.append("\nromedio geometrico: "+StatUtils.geometricMean(errors));
            resultados1.append("\nsuma: "+StatUtils.sum(errors));
            resultados1.append("\nProduto: "+StatUtils.product(errors));
            
            JFrame frame1 = new JFrame("Frame");
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setSize(900,700);
            frame1.add(plot1,BorderLayout.CENTER);
            frame1.add(resultados1,BorderLayout.SOUTH);
            frame1.setVisible(true);
        }
        public boolean done(){
            if (i == iters || error<=min_perdida){
                //generateGrapic(betas,x1,errores);
                return true;
            }
                
            return false;
        }
        
}

