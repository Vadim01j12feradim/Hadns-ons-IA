/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Behaviours;

import Objects.Dataset;
import Objects.Expresion;
import Objects.Matrix;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sensei
 */
public class BAdd extends SimpleBehaviour{
    ACLMessage mensaje, respuesta;
    Dataset data;
    float TV,Radio,Newspape,Sales,x21,x22,x23,x1x2,x1x3,x2x3,x1y,x2y,x3y;
    
    Expresion actually;
    int i=0;
    public BAdd(Agent a,Dataset datat){
        myAgent  = a;
        data = datat;
                System.out.println("\t\tMatriz");
        System.out.println("\t| a  | n    Σx1    Σx2    Σx3    | |  Σy   |");
        System.out.println("\t| b1 | Σx1  Σx²1   Σx1x2  Σx1x3  | |  Σx1y |");
        System.out.println("\t| b2 | Σx2  Σx1x2  Σx²2   Σx2x3  | |  Σx2y |");
        System.out.println("\t| b3 | Σx3  Σx1x3  Σx2x3  Σx²3   | |  Σx3y |");
        

        TV=0;
        Radio=0;
        Newspape=0;
        Sales=0;
        x21=0;
        x22=0;
        x23=0;
        x1x2=0;
        x1x3=0;
        x2x3=0;
        x1y=0;
        x2y=0;
        x3y=0;
        System.out.println("generating summation...");
        System.out.println("Muestra\tTV\tRadio\tNewspape\tSales\tx²1\tx²2\tx²3\tx1x2\tx1x3\tx2x3\tx1y\tx2y\tx3y");
        
    }
    public void action(){
        if(i<data.getSize())
            sumatorie();
        i++;
    }
    private void sumatorie(){
        //data = (Dataset)mensaje.getContentObject();
            actually = data.getExpresion(i);
            
            TV += actually.getTV();
            Radio += actually.getRadio();
            Newspape += actually.getNewspaper();
            Sales += actually.getSales();
            x21 += (actually.getTV()*actually.getTV());
            x22 += (actually.getRadio()*actually.getRadio());
            x23 += (actually.getNewspaper()*actually.getNewspaper());
            x1x2 += (actually.getTV()*actually.getRadio());
            x1x3 += (actually.getTV()*actually.getNewspaper());
            x2x3 += (actually.getRadio()*actually.getNewspaper());
            x1y += (actually.getTV()*actually.getSales());
            x2y += (actually.getRadio()*actually.getSales());
            x3y += (actually.getNewspaper()*actually.getSales());
        
            System.out.println((i+1)+" \t"+
                    actually.getTV()+" \t"+//TV
                    actually.getRadio()+" \t"+//Radio
                    actually.getNewspaper()+" \t"+//Newspape
                    actually.getSales()+" \t"+//Sales
                    (actually.getTV()*actually.getTV())+" \t"+//x²1
                    (actually.getRadio()*actually.getRadio())+" \t"+//x²2
                    (actually.getNewspaper()*actually.getNewspaper())+" \t"+//x²3
                    (actually.getTV()*actually.getRadio())+" \t"+//x1x2
                    (actually.getTV()*actually.getNewspaper())+" \t"+//x1x3
                    (actually.getRadio()*actually.getNewspaper())+" \t"+//x2x3
                    (actually.getTV()*actually.getSales())+" \t"+//x1y
                    (actually.getRadio()*actually.getSales())+" \t"+//x2y
                    (actually.getNewspaper()*actually.getSales()));//x3y
                
        //System.out.println("ΣTV\tΣRadio\tΣNewspape\tΣSales\tΣx²1\tΣx²2\tΣx²3\tΣx1x2\tΣx1x3\tΣx2x3\tΣx1y\tΣx2y\tΣx3y");

    }
    private void sendMessage() throws IOException{
        System.out.println("\n\nΣn="+i+"\tΣTV="+TV+"\tΣRadio="+Radio+"\tΣNewspape="+Newspape+"\tΣSales="+Sales+"\tΣx²1="+x21+"\tΣx²2="+x22+
                "\tΣx²3="+x23+"\tΣx1x2="+x1x2+"\tΣx1x3="+x1x3+"\tΣx2x3="+x2x3+"\tΣx1y="+x1y+"\tΣx2y="+x2y+"\tΣx3y="+x3y);
        
        System.out.println("\n\n\t\t\t\tMatriz");
        System.out.println("\t| a  | "+i+"    "+TV+"    "+Radio+"    "+Newspape+"    | |  "+Sales+"   |");
        System.out.println("\t| b1 | "+TV+"  "+x21+"   "+x1x2+"  "+x1x3+"  | |  "+x1y+" |");
        System.out.println("\t| b2 | "+Radio+"  "+x1x2+"  "+x22+"   "+x2x3+"  | |  "+x2y+" |");
        System.out.println("\t| b3 | "+Newspape+"  "+x1x3+"  "+x2x3+"  "+x23+"   | |  "+x3y+" |");
        
        Matrix m = new Matrix();
        float[][] data = new float[4][8];
        float[] val = new float[8];
                
        data[0][0] = i;
        data[0][1] = TV;
        data[0][2] = Radio;
        data[0][3] = Newspape;
        data[0][4] = 1;
        data[0][5] = 0;
        data[0][6] = 0;
        data[0][7] = 0;
        
        data[1][0] = TV;
        data[1][1] = x21;
        data[1][2] = x1x2;
        data[1][3] = x1x3;
        data[1][4] = 0;
        data[1][5] = 1;
        data[1][6] = 0;
        data[1][7] = 0;
        
        data[2][0] = Radio;
        data[2][1] = x1x2;
        data[2][2] = x22;
        data[2][3] = x2x3;
        data[2][4] = 0;
        data[2][5] = 0;
        data[2][6] = 1;
        data[2][7] = 0;
        
        data[3][0] = Newspape;
        data[3][1] = x1x3;
        data[3][2] = x2x3;
        data[3][3] = x23;
        data[3][4] = 0;
        data[3][5] = 0;
        data[3][6] = 0;
        data[3][7] = 1;
        
        val[0] = Sales;
        val[1] = x1y;
        val[2] = x2y;
        val[3] = x3y;
        
        m.setData(data);
        m.setSolutions(val);
        
        mensaje = new ACLMessage(ACLMessage.INFORM);
        System.out.println("Enciando datos a Agente Finally...");
        mensaje.setContentObject(m);
        mensaje.addReceiver(new AID("Finally",AID.ISLOCALNAME));
        myAgent.send(mensaje);
    }
    public boolean done(){
         if(i==(data.getSize())){
             try {
                 sendMessage();
                 myAgent.doDelete();
             } catch (IOException ex) {
                 Logger.getLogger(BAdd.class.getName()).log(Level.SEVERE, null, ex);
             }
                return true;
         }
         return false;
        }

}
