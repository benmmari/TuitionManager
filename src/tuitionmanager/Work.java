/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuitionmanager;

import java.util.ArrayList;

/**
 *
 * @author ben
 */
public class Work {
    
    public static String studentNumber(ArrayList<String> list,String name, String surname) {
        String answer = surname(surname)+name(name);
        System.out.println(surname(surname)+name(name));
        return verify(list, answer);}
    
    public static String verify (ArrayList<String> list, String studentNumber) {
        String similarText = "";
        int similar =  0;
        for (int index =0;index<list.size();index++) {
            if (list.get(index).substring(0, 6).equals(studentNumber)) {
                similar++;
            }
            
            similarText = similar+"";
            if (similarText.length()==0) {
                similarText= "000";
            } else {
                for (int count=similarText.length();count<3;count++) {
                    similarText = "0"+similarText;
                    
                }
            }
        }
        return studentNumber+similarText;
    }
    
    public static String name(String name) {
        String string = "";
        if (name.length() >2) {
            string = name.substring(0,3);
        } else {
            int diff = 3 - name.length();
            string = name;
            for (int x=0;x<diff;x++) {
                string+="x";
            }
        }
        
        return string.toUpperCase();
    }
    
    public static String surname(String surname) {
        String vowels = "AEIOUaeiou";
        String string = "";
        int position = 0;
        while (string.length() < 3 && position<surname.length()) {
            if (vowels.indexOf(surname.charAt(position)) == -1) {
                string+=surname.charAt(position);
            }
            position++;
        }
        
        while (string.length()< 3) {
            string+="x";
        }
        return string.toUpperCase();
        
    }
    
    
}