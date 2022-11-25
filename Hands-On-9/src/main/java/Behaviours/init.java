/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Interfaces.inputPrediction;
import Objects.Swarms;
import Objects.swarm;
import java.util.Random;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.apache.commons.math3.stat.StatUtils;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 *
 * @author sensei
 */
public class init extends SimpleBehaviour{
    
    double[] y;
    double[] x;
    
    int Maxt = 5000;
    int size = 222;
    double Wmax = 0.1;//0.9;
    double Wmin = 0.001;//0.4;
    double c1 = 2;
    double c2 = 2;
    double minerror = 96.464;
    double[] swarms = new double[size];
    JFrame frame = new JFrame("Evolucion");
    Swarms S;
    int count=0;
    
    //
    
    //Valores optimos B0: 168.3560059229 B1: 23.3288738191
    public init(Agent a, double[] xt, double[] yt){
        myAgent  = a;
        y = new double[yt.length];
        x = new double[xt.length];
        x= xt;
        y = yt;
        
        S = new Swarms(x,y,size,Wmax,Wmin);
        //InitializePopulation
        System.out.println("Process to initialize population on first iteration");
        S.initializeVaslues();
        System.out.println("---------------------------END INITIALIZING -----------------------------");
        
    }
    
    public void action(){
            System.out.println("******************************* Iteration "+Maxt+"****************************");
            swarm UpdateVelocity,UpdatePosition;
            Swarms newSwarm = new Swarms(x,y,size,Wmax,Wmin);;
            List<swarm> actPopulation,actVelocityes,actPosition;
            
            List<swarm> newPopulation,newVelocityes,newPosition;
            newPopulation = new ArrayList<>();
            newVelocityes = new ArrayList<>();
            newPosition = new ArrayList<>();
            
            actPopulation = S.getPopulation();
            actVelocityes = S.getVelocity();
            actPosition = S.getPosition();
            swarm SgBest = S.getGBest();
            swarm SgBestV = S.getGBestV();
            double v11,w,v1,r1,r2,xBest,x1,gBest;
            for(int i=0;i<size;i++){
                
                w = 0.4;//0.7;//0.01;// SgBestV.getB0();
                v1 = actVelocityes.get(i).getB0();
                r1 = (Math.random()*Wmax+Wmin);
                xBest = actPosition.get(i).getxBestB0();
                x1 = actPosition.get(i).getB0();
                r2 = (Math.random()*Wmax+Wmin);
                gBest = SgBest.getB0();
                
                v11 = w*v1 + c1*r1*(xBest - x1) + c2*r2*(gBest - x1);
                System.out.println("\n"+w+" * "+v1+" + "+c1+" * "+r1+"*("+xBest+" - "+x1+") + "+c2+" * "+r2+" *( "+gBest+" - "+x1+") = "+v11);
                UpdateVelocity = new swarm();
                UpdateVelocity.setB0(v11);
                
                //w = 0.7;//SgBestV.getB1();
                v1 = actVelocityes.get(i).getB1();
                r1 = (Math.random()*Wmax+Wmin);
                xBest = actPosition.get(i).getxBestB1();
                x1 = actPosition.get(i).getB1();
                r2 = (Math.random()*Wmax+Wmin);
                gBest = SgBest.getB1();
                
                v11 = w*v1 + c1*r1*(xBest - x1) + c2*r2*(gBest - x1);
                System.out.println(w+" * "+v1+" + "+c1+" * "+r1+"*("+xBest+" - "+x1+") + "+c2+" * "+r2+" *( "+gBest+" - "+x1+")"+" = "+v11);
                UpdateVelocity.setB1(v11);
                newVelocityes.add(UpdateVelocity);
                
                //Velocity actually calculated
                
                x1 = actPosition.get(i).getB0();
                v1 = UpdateVelocity.getB0();
                x1 += v1;
                
                UpdatePosition = new swarm();
                UpdatePosition.setB0(x1);
                
                x1 = actPosition.get(i).getB1();
                v1 = UpdateVelocity.getB1();
                x1 += v1;
                UpdatePosition.setB1(x1);
                
                UpdatePosition.setFitness(newSwarm.getFitness(UpdatePosition));
                
                swarm st = new swarm();
                st.setB0(actPosition.get(i).getxBestB0());
                st.setB1(actPosition.get(i).getxBestB1());
                st.setFitness(newSwarm.getFitness(st));
                double allFitness = st.getFitness();
                
                if(allFitness < UpdatePosition.getFitness()){
                    UpdatePosition.setxBestB0(UpdatePosition.getB0());
                    UpdatePosition.setxBestB1(UpdatePosition.getB1());
                }else{
                    UpdatePosition.setxBestB0(actPosition.get(i).getxBestB0());
                    UpdatePosition.setxBestB1(actPosition.get(i).getxBestB1());
                }
                
                newPosition.add(UpdatePosition);
                //Calculated position particles
            }
            newSwarm.setPosition(newPosition);
            newSwarm.setVelocity(newVelocityes);
            newSwarm.gBest();
            S = newSwarm;
            showPositions();
            Maxt --;
            System.out.println("****************************************************************");
            count++;
            generateGrapic();
        try {
            Thread.sleep(800);
        } catch (InterruptedException ex) {
            System.out.println("************************************Error sleep****************************");
        }
            
    }
    private void showPositions(){
        List<swarm> actPosition = S.getPosition();
        swarm st;
        st = S.getGBest();
        System.out.println("Gbest: "+st.getB0()+" "+st.getB1()+" "+st.getFitness());
        System.out.println("-----------------------------Show new positions---------------------");
        for(int i=0; i<size;i++){
            st = actPosition.get(i);
            System.out.println(st.getB0()+" | "+st.getB1()+" | "+st.getFitness());
        }
        System.out.println("---------------------------------------------------------------------");
        
        
    }
    public boolean done(){
            double fitAct = S.getGBest().getFitness();
            if(Maxt == 0 || fitAct > minerror){
                System.out.println("*******************Values*******************");
                System.out.println("Iterations: "+count);
                System.out.println("B0: "+S.getGBest().getB0()+"\nB1: "+S.getGBest().getB1()+"\nFitness: "+fitAct);
                System.out.println("*******************Values*******************");
                myAgent.doDelete();
                inputPrediction P = new inputPrediction();
                P.setValues(S.getGBest().getB0(), S.getGBest().getB1(), x, y);
                P.setVisible(true);
                
                return true;
            }
            return false;
        }
    private void generateGrapic(){
        
        List<swarm> Pos = S.getPosition();
        double[] yp = new double[Pos.size()];
        double[] xp = new double[Pos.size()];
        Plot2DPanel plot = new Plot2DPanel();
        JTextArea resultados = new JTextArea();
        double[] xdm = {0,300};
        double[] ydm = {300,0};
        int i = 0;
        for(i=0;i<Pos.size();i++){
            xp[i] = Pos.get(i).getB0() + Pos.get(i).getB1();   
            yp[i] = Pos.get(i).getFitness();
        }
        
        plot.addLegend("Pruebas");
        plot.addScatterPlot("Datos", xp,yp);
        plot.addScatterPlot("Datos", xdm,ydm);
        BaseLabel titulo = new BaseLabel("PSO Algoritm",Color.BLUE,0.5,1.1);
        plot.addPlotable(titulo);
        resultados.setBackground(Color.LIGHT_GRAY);
        resultados.append("\nValor minimo: "+StatUtils.min(y));
        resultados.append("\nValor maximo: "+StatUtils.max(y));
        resultados.append("\nValor promedio: "+StatUtils.mean(y));
        resultados.append("\nVarianza: "+StatUtils.variance(y));
        resultados.append("\nPromedio geometrico: "+StatUtils.geometricMean(y));
        resultados.append("\nsuma: "+StatUtils.sum(y));
        resultados.append("\nProduto: "+StatUtils.product(y));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,700);
        frame.add(plot,BorderLayout.CENTER);
        frame.add(resultados,BorderLayout.SOUTH);
        frame.setVisible(true);
    }
        
}