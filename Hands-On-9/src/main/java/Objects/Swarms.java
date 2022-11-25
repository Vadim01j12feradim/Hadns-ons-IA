/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sensei
 */
public class Swarms {
    int swarmSize;
    List<swarm> population = new ArrayList<>();
    List<swarm> Velocity = new ArrayList<>();
    List<swarm> position = new ArrayList<>();
    
    double minV = 0.1;
    double maxV = 0.5;
    double[] y;
    double[] x;
    double Wmax;
    double Wmin;
    swarm GBest;
    swarm GBestV;
    public Swarms(double[] xt, double[] yt, int size,double WmaxT, double WminT){
        Wmax = WmaxT;
        Wmin = WminT;
        swarmSize = size;
        y = new double[yt.length];
        x = new double[xt.length];
        x= xt;
        y = yt;
    }
   public void setPopulation(List<swarm> pt){
        population = pt;
    }
    public void setVelocity(List<swarm> vt){
        Velocity = vt;
    }
    public void setPosition(List<swarm> pt){
        position = pt;
    }
    
    public swarm getGBest(){
        return GBest;
    }
    public swarm getGBestV(){
        return GBestV;
    }
    public List<swarm> getPopulation(){
        return population;
    }
    public List<swarm> getVelocity(){
        return Velocity;
    }
    public List<swarm> getPosition(){
        return position;
    }
    
    public void initializeVaslues(){
        int i=0;
        double temp;
        swarm swT,swT2;
        while(i < swarmSize){
            //Initial population
            swT = new swarm();
            temp = (Math.random()*220+1);
            swT.setB0(temp);
            temp = (Math.random()*50+1);
            swT.setB1(temp);
            population.add(swT);
            
            //Initial Velocity
            swT2 = new swarm();
            temp = (Math.random()*maxV+minV);
            System.out.println("\nv(x1) = "+temp+" * "+ swT.getB0()+" = "+temp * swT.getB0());
            
            swT2.setB0(temp * swT.getB0());
              
            //temp = (Math.random()*maxV+minV);
            System.out.println("v(x2) = "+temp+" * "+ swT.getB1()+" = "+temp * swT.getB1());
            swT2.setB1(temp * swT.getB1());
            Velocity.add(swT2);
           
            i++;
        }
        System.out.println("\tx1\tx2 ");
        for(i=0;i<swarmSize;i++){
            swT = Velocity.get(i);
            System.out.println((i+1)+"Particle: "+swT.getB0()+" | "+swT.getB1());
        }
        System.out.println("Initial Velocity");
        
        
        System.out.println("\tx1\tx2 ");
        for(i=0;i<swarmSize;i++){
            swT = population.get(i);
            System.out.println((i+1)+"Particle: "+swT.getB0()+" | "+swT.getB1());
        }
        System.out.println("Initial population");
        

        
        double Bt1,Bt2;
        swarm sTemp;
        for (i = 0; i < swarmSize; i++) {
            swT = population.get(i);
            swT2 = Velocity.get(i);
            Bt1 = swT.getB0();
            Bt2 = swT2.getB0();
            System.out.println("\nx1 = "+Bt1+" + "+Bt2+" = "+(Bt1+Bt2));
            Bt1 += Bt2;
            sTemp = new swarm();
            sTemp.setB0(Bt1);
            sTemp.setxBestB0(Bt1);

            Bt1 = swT.getB1();
            Bt2 = swT2.getB1();
            System.out.println("x2 = "+Bt1+" + "+Bt2+" = "+(Bt1+Bt2));
            Bt1 += Bt2;
            sTemp.setB1(Bt1);
            sTemp.setxBestB1(Bt1);

            position.add(sTemp);
        }
        for (i = 0; i < swarmSize; i++) {
            swT = position.get(i);
            System.out.println((i+1)+"Particle: "+swT.getB0()+" | "+swT.getB1());
        }
        
        System.out.println("Initial Position");
        for (int j = 0; j < swarmSize; j++) {
            swT = position.get(0);
            Bt1 = getFitness(swT);
            swT.setFitness(Bt1);
            position.remove(0);
            position.add(swT);          
        }
        
        for (i = 0; i < swarmSize; i++) {
            swT = position.get(i);
            System.out.println((i+1)+"Particle: "+swT.getB0()+" | "+swT.getB1()+" | "+swT.getFitness());
        }
        gBest();
        System.out.println("Gbest: "+GBest.getB0()+" "+GBest.getB1()+" "+GBest.getFitness());
        System.out.println("GbestV: "+GBestV.getB0()+" "+GBestV.getB1());
    }
    public void gBest(){
        swarm max = new swarm();
        swarm maxVelo = new swarm();
        swarm temp = new swarm();
        max = position.get(0);
        maxVelo = Velocity.get(0);
        for (int i = 1; i < swarmSize; i++) {
            temp = position.get(i);
            
            if(temp.getFitness() > max.getFitness()){
                maxVelo = Velocity.get(i);
                max = temp;
            }
                
        }
        GBestV = maxVelo;
        GBest = max;
    } 
        public double getFitness(swarm P){
        double B0 = P.getB0();
        double B1 = P.getB1();
        double[] y = {651,762,856,1063,1190,1298,1421,1440,1518};
        double[] x = {23,26,30,34,43,48,52,57,58};
        double acum=0;
        for (int i = 0; i < x.length; i++) {
            acum += Math.pow((y[i]-(B0+(B1*x[i]))),2);
        }
        //System.out.println("An div: "+acum);
        acum = acum/(x.length-2);
        acum = Math.sqrt(acum);
        //System.out.println("ErrorAnt: "+acum);
        
        double error = 100/Math.abs(acum);
        error = (error *100)/2;
        //System.out.println("Error: "+error);
        //double mul = (double)1/x.length;
        /*
        double error = 100/Math.abs(acum);
        System.out.println("Fitnes obtenido: "+error);
        return error;*/
        return error;
    }

}
