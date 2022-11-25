
package Behaviours;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.apache.commons.math3.stat.StatUtils;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 *
 * @author sensei
 */
public class Correjido extends SimpleBehaviour{
    double[] trX;
    double[] trY;


    public Correjido(Agent a, double[] xt,double[] yt){
        myAgent  = a;
        trX = new double[xt.length];
        trY = new double[yt.length];
        trX = xt;
        trY = yt;

    }
        double num_steps = 900000;//Numero de iteraciones

        double[] yPredict = new double[(int)num_steps];
        double learningRate =  0.000008;//0.000005
        double criterial = 9;//Error minimo
        double b_0 = 1;//6.9747753;//Final
        double b_1 = 1;//6.9747753;//Final
        double b_0_error = 6;//0.05546506;//Final
        double b_1_error= 1;//0.05546506;//Final
        double b_0_gradient;//En sumatoria
        double b_1_gradient;//En sumatoria
        double N;//Cantidad de datos
        double f_error= 0;
        double error= 0;
        int step=0;

    public void action(){
            iteration();
            step++;
            }
    public void iteration(){

        //System.out.println("N = "+(double)trX.length);
        b_0_gradient = 0;
        b_1_gradient = 0;
        f_error = 0 ;
        N = trX.length;
        for (int i = 0; i < trX.length; i++) {
            b_0_gradient += (trY[i]-(b_0 + b_1 * trX[i]));
            f_error += ((trY[i]-(b_0 + b_1 * trX[i]))*(trY[i]-(b_0 + b_1 * trX[i])));
            b_1_gradient += trX[i]*(trY[i]-(b_0 + b_1 * trX[i]));
        }
        b_0_error = (((double)-2/N)*b_0_gradient) ;
        b_1_error = (((double)-2/N)*b_1_gradient) ;
        error = (f_error/N) ;
        System.out.println("b_0 = "+b_0);
        System.out.println("b_1 = "+b_1);

        System.out.println("Error: "+error);
        }

        public boolean done(){
            if (error > criterial || step == num_steps) {
                System.out.println("Los valores que se obtienen son: "+b_0+" y "+b_1 +" en pasos: " + step);
                System.out.println("Error: "+error);
                

                return true;
            }
              b_0= b_0 - (b_0_error* learningRate);
              b_1= b_1 - (b_1_error* learningRate);
              return false;
        }

}