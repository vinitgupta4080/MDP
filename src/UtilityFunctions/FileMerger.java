/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilityFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinit
 */
public class FileMerger {
    
    private String file1name;
    private String file2name;
    
    public FileMerger(String file1, String file2)
    {
        this.file1name = file1;
        this.file2name = file2;
    }
    
    public void merger()
    {
        String line1="", line2="";
        BufferedReader reader1=null, reader2=null;
        String content="";
        File output =null;
        try {
            reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1name+"\\ValueSum.dat")));
            reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2name+"\\ValueSum.dat")));
            File directory = new File("compare Algorithm");

            if(!directory.exists())
            {
                directory.mkdir();;
            }
            
            output = new File("compare Algorithm\\comparedata.dat");
            File output2 =  new File("compare Algorithm\\compareval.jpeg");
            File ouput3 = new File("compare Algorithm\\comapre_Script.gnu");
            if(output.exists()){
                output.delete();;
            }
            output.createNewFile();
            int count=0;
            
            while ((((line1 = reader1.readLine()) != null) | ((line2 = reader2.readLine()) != null))&&count<15)
            {
                if(line1 == null ){
                    content+="  ";
                    count++;
                }
                else{
                    content+=line1+" ";
                }
                if(line2 ==  null){
                    content+=" "+"\n";
                    count++;
                }
                else{
                    content+=line2;
                    content+="\n";
                }
            }
            FileWriter fw = new FileWriter(output.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileMerger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileMerger.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    public static void main(String a[]){
        FileMerger fm = new FileMerger("Simplex\\ValueSum.dat","MixStrategy\\ValueSum.dat");
        fm.merger();
    }
}
