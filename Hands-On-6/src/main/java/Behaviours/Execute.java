/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 *
 * @author sensei
 */
public class Execute extends SimpleBehaviour{
    /*
    double[] x;
    double[] y;
    /*
    */
    /*
    double[] trX = {
-2.0
,
-1.96
,
-1.92
,
-1.88
,
-1.84
,
-1.8
,
-1.76
,
-1.72
,
-1.68
,
-1.6400000000000001
,
-1.6
,
-1.56
,
-1.52
,
-1.48
,
-1.44
,
-1.4
,
-1.3599999999999999
,
-1.3199999999999998
,
-1.28
,
-1.24
,
-1.2
,
-1.1600000000000001
,
-1.12
,
-1.08
,
-1.04
,
-1.0
,
-0.96
,
-0.9199999999999999
,
-0.8799999999999999
,
-0.8400000000000001
,
-0.8
,
-0.76
,
-0.72
,
-0.6799999999999999
,
-0.6399999999999999
,
-0.5999999999999999
,
-0.56
,
-0.52
,
-0.48
,
-0.43999999999999995
,
-0.3999999999999999
,
-0.3599999999999999
,
-0.32000000000000006
,
-0.28
,
-0.24
,
-0.19999999999999996
,
-0.15999999999999992
,
-0.11999999999999988
,
-0.08000000000000007
,
-0.040000000000000036
,
0.0
,
0.040000000000000036
,
0.08000000000000007
,
0.1200000000000001
,
0.16000000000000014
,
0.20000000000000018
,
0.2400000000000002
,
0.28000000000000025
,
0.31999999999999984
,
0.3599999999999999
,
0.3999999999999999
,
0.43999999999999995
,
0.48
,
0.52
,
0.56
,
0.6000000000000001
,
0.6400000000000001
,
0.6800000000000002
,
0.7200000000000002
,
0.7600000000000002
,
0.8000000000000003
,
0.8399999999999999
,
0.8799999999999999
,
0.9199999999999999
,
0.96
,
1.0
,
1.04
,
1.08
,
1.12
,
1.1600000000000001
,
1.2000000000000002
,
1.2400000000000002
,
1.2800000000000002
,
1.3200000000000003
,
1.3599999999999999
,
1.4
,
1.44
,
1.48
,
1.52
,
1.56
,
1.6
,
1.6400000000000001
,
1.6800000000000002
,
1.7200000000000002
,
1.7600000000000002
,
1.8000000000000003
,
1.8399999999999999
,
1.88
,
1.92
,
1.96
,
2.0
};
double[] trY = {
-1.0548527730353245
,
-1.3316523172428822
,
-0.941353464253974
,
-0.8042726340844153
,
-0.3565268163293275
,
0.3715457832118326
,
-0.5867412400890721
,
-0.7031838163292544
,
-0.12677039169226717
,
0.03632369371781652
,
-0.863713326647018
,
-0.6371168002115725
,
0.2861470919330594
,
0.25873911105020925
,
-0.15693487910188958
,
0.34528276812414327
,
-0.3671989915705466
,
0.14677904619017199
,
0.7756380616133847
,
0.278806180360115
,
0.985967502107628
,
0.08582967327753843
,
0.8145332393809552
,
0.5102552777439955
,
1.2674198136780819
,
1.024242061485959
,
1.118110348934968
,
1.05469153369156
,
1.4580627249898657
,
1.3837915626121016
,
1.7833583852895927
,
1.5017903602377254
,
1.6116729697402283
,
0.9179444504029074
,
1.557496253341319
,
1.5468829921902723
,
1.7347276889188905
,
1.264403512735103
,
1.5425536112899716
,
1.9156784843272379
,
2.310753907237514
,
2.261007384726187
,
2.8655699146419082
,
2.8127125920253198
,
1.7941320777584602
,
2.236745670312178
,
2.8920510591424518
,
2.8051665516021695
,
2.5738667668948483
,
3.150625199583338
,
2.954541094505058
,
3.609557490982989
,
2.931030414957841
,
2.755836935799316
,
3.42187318879078
,
3.5035045202050124
,
3.9283675189475966
,
4.155936497320369
,
3.429638126803671
,
3.6201191030816533
,
4.280203511120226
,
3.5058191077844607
,
3.948784571848798
,
5.153624457781031
,
4.1579805834016765
,
4.205355776184489
,
4.977834646395712
,
4.2814771115092185
,
4.570471362973938
,
3.8827852366222166
,
4.851827775482452
,
4.575984384462395
,
4.839706650687139
,
4.518995304869356
,
4.919088966885496
,
4.491712481661029
,
4.559033034885235
,
5.222564085316068
,
4.962494137488041
,
5.227137705933884
,
5.61839206157967
,
5.660984828103921
,
5.12780895345018
,
5.666427684753038
,
5.869096126534805
,
5.982036327294877
,
5.885018522374809
,
6.300474480468728
,
5.7448116841241115
,
6.100372460002939
,
6.346074892939939
,
6.554328224787767
,
6.360130135399448
,
6.256701025953971
,
7.304275772153559
,
6.3885790732838785
,
6.864282878042508
,
6.9733727153371055
,
6.492442806176663
,
6.504736264596289
,
7.1667952285178815
};
*/
    double[] trX;
    double[] trY;
    public Execute(Agent a, double[] xt,double[] yt){
        myAgent  = a;
        trX = new double[xt.length];
        trY = new double[yt.length];

        double aux;
        double auy;
        for(int i = 0;i < xt.length-1;i++){
                    for(int j = 0;j < xt.length-i-1;j++){
                        if(xt[j+1] >  xt[j]){    
                            aux = xt[j+1];
                            auy = yt[j+1];
                            //index.set(j+1,index.get(j));
                            xt[j+1] = xt[j];
                            yt[j+1] = yt[j];
                            xt[j]=aux;
                            yt[j]=auy;
                        }
                    }
                }
        trX = xt;
        trY = yt;
        generateGrapic(trX,trY);
    }
        double num_steps = 100;//Numero de iteraciones
        double learningRate =  0.00000005;//Velocidad de learn recomendado 0
        double criteria = 2;//Error minimo
        double b_0 = 1;//Final
        double b_1 = 1;//Final
        double b_0_gradient;//En sumatoria
        double b_1_gradient;//En sumatoria
        double N;//Cantidad de datos
        int step=0;

    public void action(){    
            iteration();
            step++;
            }
    public void iteration(){

        //System.out.println("N = "+(double)trX.length);
        b_0_gradient = 0;
        b_1_gradient = 0;
        N = (float)trX.length;
        for (int j1 = 0; j1 < trX.length; j1++) {
            b_0_gradient -= (2/N)*(trY[j1]-(b_0 + b_1 * trX[j1]));
            b_1_gradient -= (2/N)*(trY[j1]-(b_0 + b_1 * trX[j1]))*trX[j1];
        }
        b_0 = b_0 - (learningRate * b_0_gradient);
        b_1 = b_1 - (learningRate * b_1_gradient);
        
        System.out.println("b_0 = "+b_0);
        System.out.println("b_1 = "+b_1);
        
        System.out.println("Error: "+Math.max(Math.abs(learningRate * b_0_gradient),Math.abs(learningRate * b_1_gradient)));
        }

        public boolean done(){
            if (step == num_steps) {
                System.out.println("Los valores que se obtienen son: "+b_0+" y "+b_1 +" en pasos" + step);
                System.out.println("Error: "+Math.max(Math.abs(learningRate * b_0_gradient),Math.abs(learningRate * b_1_gradient)));
                return true;
            }
            return false;
        }
        public void generateGrapic(double[] x,double[] y){
        
        
        Plot2DPanel plot = new Plot2DPanel();
        JTextArea resultados = new JTextArea();
        
        
        
        
        plot.addLegend("Regresion");
        plot.addLinePlot("Datos", x,y);
        
        BaseLabel titulo = new BaseLabel("Regrsion Lineal",Color.BLUE,0.5,1.1);
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
    }

}
