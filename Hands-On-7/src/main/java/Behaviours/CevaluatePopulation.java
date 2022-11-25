/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Interfaces.inputPrediction;
import Ontology.Generation;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CevaluatePopulation extends CyclicBehaviour{
    ACLMessage mensaje, respuesta;
    Generation generation;
    double[] y = {651,762,856,1063,1190,1298,1421,1440,1518};
    double[] x = {23,26,30,34,43,48,52,57,58};
    public CevaluatePopulation(Agent a){
        myAgent  = a;
    }
    public void action(){
        mensaje = myAgent.blockingReceive();
        if(mensaje != null){
            evaluate();
            mensaje = null;
            
        }else{
            block();    
        }
    }
    private void evaluate(){
        try {
                System.out.println("-------------------------------------------------------------Evaluando--------------------------------");
                generation = (Generation)mensaje.getContentObject();
                System.out.println("\tAgente Value evaluando los habitantes de la genracion...");
                double fitnes=0;
                generation.HallValues();
                double B0,B1;
                double idealValue = 192;//192 ideal calculado por los errores negativos y positgivos mayores a 100
                for(int i=0;i<generation.getpopulationSize();i++){
                    B0 = generation.getParent(i).getB0();
                    B1 = generation.getParent(i).getB1();                  
                    fitnes = generation.getParent(i).getFitness();
                    System.out.println("B0: "+B0+" B1: "+B1+" Fitness: "+fitnes+" Real: "+(fitnes*100));
                    if((fitnes*100) >= idealValue){
                        
                        inputPrediction p = new inputPrediction();
                        p.setValues(B0, B1, x, y);
                        p.setVisible(true);
                        myAgent.doDelete();
                        break;
                    }
                }
                
                generation.Sort();
               
                respuesta = new ACLMessage(ACLMessage.INFORM);
                System.out.println("Enviando generacion a Crossover...");
                try {
                    respuesta.setContentObject(generation);
                } catch (IOException ex) {
                    System.out.println("Se produjo un error al enviar la gernacion en evaluacion popuklation");
                }
                respuesta.addReceiver(new AID("Crossover",AID.ISLOCALNAME));
                myAgent.send(respuesta);
                
            } catch (UnreadableException ex) {
                Logger.getLogger(CevaluatePopulation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void generateGrapic(float b0, float b1,String title,double predict){
        float[] xt = {23,26,30,34,43,48,52,57,58};
        float[] yt = {651,762,856,1063,1190,1298,1421,1440,1518};
        JFrame frame = new JFrame("grafico");
                
        Plot2DPanel plot = new Plot2DPanel();
        JTextArea resultados = new JTextArea();
        
        
        
        double[] yc = new double[xt.length+1];
        double[] x,y;
        x = new double[xt.length+1];
        y = new double[yt.length+1];
        
        for(int i=0;i<yt.length;i++){
            x[i] = xt[i];
            y[i] = yt[i];
            
        }
        int i = 0;
        for(i=0;i<yt.length;i++){
            yc[i] = b0 + (b1*x[i]);
            
        }
        x[i] = predict;
        yc[i] = b0 + (b1*predict);
        y[i] = yc[i];
        double[] px = new double[1];
        px[0] = predict;
        double[] py = new double[1];
        py[0] = yc[i];
        
        plot.addLegend(title);
        plot.addScatterPlot("Datos", x,y);
        plot.addLinePlot("Regresion",x,yc);
        
        plot.addScatterPlot("Punto", px,py);
        BaseLabel titulo = new BaseLabel("Algortimo genetico",Color.BLUE,0.5,1.1);
        plot.addPlotable(titulo);
        resultados.setBackground(Color.LIGHT_GRAY);
        resultados.append("\nValor minimo: "+StatUtils.min(y));
        resultados.append("\nValor maximo: "+StatUtils.max(y));
        resultados.append("\nValor promedio: "+StatUtils.mean(y));
        resultados.append("\nVarianza: "+StatUtils.variance(y));
        resultados.append("\nromedio geometrico: "+StatUtils.geometricMean(y));
        resultados.append("\nsuma: "+StatUtils.sum(y));
        resultados.append("\nProduto: "+StatUtils.product(y));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,700);
        frame.add(plot,BorderLayout.CENTER);
        frame.add(resultados,BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    
}
