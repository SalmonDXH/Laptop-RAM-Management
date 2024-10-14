package io;


import java.util.ArrayList;

import model.RAM;

// read library
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;

public class IO {
    /*
     * @Param String file is path to let FileReader read
     */
    public static ArrayList<String> read(String file){
        ArrayList<String> ar = null;
        
        try{
            ar = new ArrayList<>();
            File f = new File(file);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine())!= null){
                ar.add(line);
            }

            br.close();
            fr.close();
            
        } catch (IOException ioe){
            
        }
        
        return ar;
    }

    public static void save(ArrayList<RAM> ar,String file){
        try{
            String s = "";
            for (RAM ram : ar){
                s+= ram.toString() + "\n";
            }
            File f = new File(file);
            FileWriter fw = new FileWriter(f);
            fw.write(s);
            System.out.println("Successfully save");
            fw.close();
        } catch (IOException ioe){

        }
    }
}
