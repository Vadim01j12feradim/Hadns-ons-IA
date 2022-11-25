/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import java.util.Random;

/**
 *
 * @author sensei
 */
public class BLoadDataset extends SimpleBehaviour{
    int n=0;
    boolean finall=false;
    
    double[] x;
    double[] y;
    double[] w;//Pesos aleatorios
    double b = 0;//sesgo
    double yUp = 0;
    double h = 0.01; 
    double E=2;//error
    double m=0;
    double c=0;
    public BLoadDataset(Agent a, double[] xt,double[] yt){
        myAgent  = a;
        x = new double[xt.length];
        y = new double[yt.length];
        w = new double[yt.length];
        Random rnd = new Random();
        for (int i = 0; i < yt.length; i++) {
            boolean flag = true;
            double trd = (rnd.nextDouble() * 2 -1);
            while(flag){
                if (trd == 0) {
                    trd = (rnd.nextDouble() * 2 -1);
                } else {
                    flag = false;
                }
            }
            w[i] = trd;
            //System.out.println("Dimencion de x: "+w[i]);
        }
        
        System.out.println("Dimencion de x: "+x.length);
        System.out.println("Dimencion de y: "+y.length);
        x = xt;
        y = yt;
    }

    // E = mse
    // f = sigmoid
    public void action(){    
            System.out.println("Initiliting epoca "+n+" ...");
            iteration();
            System.out.println("Epoca "+(n-1)+" dead :(");
            }
    public void iteration(){
        double fh;
        double fp;//&
        double Wi;
        double hl;

        //1. Calcular la salida/ forward pass
        hl = getH(w,x,b);
        System.out.println("h = "+hl);
        fh = getSigmoid(hl);//Yipred
        System.out.println("f(h) = "+fh);
        //2. Determinar el error / Loss
        E = Math.pow((y[n]-fh), 2);
        System.out.println("E = "+E);
        //3. Modificar los pesos
            //3.1 Termino de error
            fp = (y[n]-fh)*fh*(1-fh);
            System.out.println("& = "+fp);
            //3.2 Calcular incremento
            getWeight(fp);//3.3 Actualizar
            
            //4. Estimar nueva salida
        n++;
        }
    public void getWeight(double fpt) {
        //3.3 Actualizar
        System.out.println("Actualizar");
        double wCurrentT;
        double temp=0;
        double tempy = 0;
        for (int i = 0; i < x.length; i++) {
            wCurrentT = h*fpt*x[i];
            temp += wCurrentT;
            tempy += h*fpt;
            //System.out.println("W"+i+" = "+h+"("+fpt+")("+x[i]+")");
           // System.out.println("W"+i+" = "+wCurrentT);
            w[i] = w[i]+ wCurrentT;
            //System.out.println("W"+i+" = "+w[i]);
            
        }
        m = m-( (-2/Double.parseDouble(String.valueOf(x.length)))*temp);
        System.out.println("m = "+m);
        c = c-((-2/Double.parseDouble(String.valueOf(x.length)))*tempy);
        System.out.println("c = "+c);
        
    }
    public double getSigmoid(double a) {
        double e = Math.E;
        return(1/(1+Math.pow(e, -a)));
    }
    public double getH(double[] wt, double[] xt, double bt) {
        System.out.print("h = ");
        double temp= 0;
        for (int i = 0; i < xt.length; i++) {
            temp += wt[i]*xt[i];
            System.out.print(wt[i] + "("+xt[i]+") + ");
        }
        temp +=  bt;
        System.out.print(bt+"\n");
        return temp;
    }
        public boolean done(){
            System.out.println("------------------Error: "+E);
            System.out.println("------------------m: "+m+" c: "+c);
            if(E<10){
                System.out.println("Finalizando");
                finall = true;
            }
            if(n>199){
                n=0;
            }
            return finall;
        }
}
