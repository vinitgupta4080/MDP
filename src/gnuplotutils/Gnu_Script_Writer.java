/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gnuplotutils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinit
 */
public class Gnu_Script_Writer {
    //Folder in which gnu script files should be produced
    String path;
    
    public Gnu_Script_Writer(){
        path = "Result";
        init();
    }
    
    private void init()
    {
        try
        {        
            String currentDir = new File(".").getCanonicalPath();
            this.path = currentDir + "\\" + path + "\\";
            File file = new File(path);
            if(!file.exists())
            {
                file.mkdirs();
            }
            file.createNewFile();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }        
    }
    
    public Gnu_Script_Writer(int no_Of_State,int No_Action_StateWise[], String path)
    {
        this.path = path;
        generateGnuScriptFiles(no_Of_State, No_Action_StateWise);
        generateImages(no_Of_State);
    }
    
    public void generateGnuScriptFiles(int no_Of_State, int No_Action_StateWise[])
    {
        generateGnuScriptFilesForProbDist(No_Action_StateWise);        
        Value_Script(no_Of_State,No_Action_StateWise);
    }
    
    public void generateGnuScriptFilesForProbDist(int No_Action_StateWise[])
    {
        for(int k=1;k<=No_Action_StateWise.length;k++){
            String temp="S"+k;
            prob_Script(No_Action_StateWise[k-1],temp);
        }        
    }
    
    public void generateImagesForProbDist(int no_Of_State)
    {
        for(int i=1;i<=no_Of_State;i++){
            String tempPath = path + "\\S"+i+"_Script.gnu";
            generate_Images(tempPath);
        }
    }
    
    public void generateImagesForValueDist(int no_Of_State)
    {
        for(int i=1;i<=no_Of_State;i++){
            String tempPath = path + "\\S"+i+"_Script.gnu";
            generate_Images(tempPath);
        }
    }
    
    public void generateImages(int no_Of_State)
    {
        for(int i=1;i<=no_Of_State;i++){
            String tempProbPath = path + "\\S"+i+"_Script.gnu";
            String tempValPath = path + "\\Value_"+i+"_Script.gnu";
            generate_Images(tempProbPath);
            generate_Images(tempValPath);
        }
        generate_Images(path + "\\Value_Script.gnu");
    }
    
    
    public void generate_Images(String path){
     //String params[] = {"C:\\Program Files (x86)\\gnuplot\\bin\\gnuplot.exe",path};
    String params[]={"C:\\Users\\vinit\\Downloads\\Desktop\\Ass3\\gp426win32\\gnuplot\\bin\\wgnuplot.exe",path};
    ProcessBuilder pb = new ProcessBuilder(params);
        try {
            Process proc = pb.start();
        } catch (IOException ex) {
            Logger.getLogger(Gnu_Script_Writer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void Value_Script(int no_Of_State, int []No_Action_StateWise)
    {
        Value_Script_All_In_One();
        for(int i=0; i<no_Of_State; i++)
        {
            Value_Script_Per_State(i,No_Action_StateWise[i]);
        }
    }
    
    public void Value_Script_All_In_One(){
        PrintWriter writer = null; 
        try {
            writer = new PrintWriter(path+"\\Value_Script.gnu", "UTF-8");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Mix_Strategy_Algo1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(Mix_Strategy_Algo1.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.println("set terminal png size 320,320");
        writer.println("set output '"+path+"/"+"value_Plot.jpeg'");        
        writer.print("plot ");
        int i=1;
        writer.print("\""+ path+ "/ValueSum.dat\" using "+1+" title \"(Total Value)"+""+"\" with lines");
        writer.close();

    }
    
    
    public void Value_Script_Per_State(int state_index, int number_of_action){
        PrintWriter writer = null; 
        try {
            writer = new PrintWriter(path+"\\Value_"+(state_index+1)+"_Script.gnu", "UTF-8");
        } catch (FileNotFoundException ex) {
        } catch (UnsupportedEncodingException ex) {
        }
        writer.println("set terminal png size 320,320");
        writer.println("set output '"+path+"/value_"+"state_"+(state_index+1)+"_Plot.jpeg'");
        writer.print("plot ");
        writer.print("\""+path+"/State"+(state_index+1)+".dat\" using "+(number_of_action+1)+" title \""+"(State"+(state_index+1)+"Value)"+"\" with lines");            
        writer.close();
    }

    
    
    
    public void prob_Script(int action, String file ){
        PrintWriter writer = null;        
        try {
            writer = new PrintWriter(path+"/"+file+"_Script.gnu", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gnu_Script_Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Gnu_Script_Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i;
        writer.println("set terminal png size 320,320");
        //writer.println("set output 'C:/Users/vinit/Downloads/Desktop/Result/"+file+".jpeg'");
        writer.println("set output '"+path+"\\"+file+".jpeg'");
        writer.print("plot ");
        for(i = 1; i<action; i++){
            writer.print("\""+path+"/State"+file.substring(1)+".dat\" using "+i+" title \"A"+i+"\" with lines,");            
        }
            writer.print("\""+path+"/State"+file.substring(1)+".dat\" using "+i+" title \"A"+i+"\" with lines");            
            writer.close();
    }
    
    public void compareScript(String f1, String f2)
    {
        PrintWriter writer = null;        
        try {
            writer = new PrintWriter("compare Algorithm/"+"comapre_Script.gnu", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gnu_Script_Writer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Gnu_Script_Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.println("set terminal png size 320,320");
        writer.println("set output 'compare Algorithm\\compareval.jpeg'");        
        writer.print("plot \"compare Algorithm/comparedata.dat\" using 1 title \""+f1+"\" with lines,");
         writer.print("\"compare Algorithm/comparedata.dat\" using 2 title \""+f2+"\" with lines");            
            writer.close();        
    }
    
    
}
