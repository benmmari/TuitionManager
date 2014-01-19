/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuitionmanager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author ben
 */
public class Adhoc {
    
    static String fileName;
   static Object data;
  static  FileInputStream input;
    static POIFSFileSystem myFileSystem;
    static HSSFWorkbook myWorkBook;
    static HSSFSheet mySheet;
   static Iterator rowIterator;
   static Vector cellVectorHolder;
   static Vector dataHolder;
   static ArrayList<String> names = new ArrayList<String>();
   static ArrayList<String> surnames = new ArrayList<String>();
    static int length = 0;
    
        public static void main (String [] args) 
    {
        fileName="C://Volt//Work//School//students.xls";
        try 
        {
            
            input= new FileInputStream(fileName);
            myFileSystem= new POIFSFileSystem(input);
            myWorkBook =new HSSFWorkbook(myFileSystem);
            mySheet= myWorkBook.getSheetAt(0);
            rowIterator= mySheet.rowIterator();  
            cellVectorHolder= new Vector();
            dataHolder=getData();
           fix();
        
        }

        catch (FileNotFoundException ex) {
            //Logger.getLogger(readExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        }
       catch(IOException e)
       {
            //Logger.getLogger(readExcelFile.class.getName()).log(Level.SEVERE, null, e);
       }

    }
        
       public static Vector getData()
       {     
            try
            {
            HSSFRow myRow;
            Iterator cellIterator;
            Vector cellStoreVector;
            while (rowIterator.hasNext())
            {    
            myRow=(HSSFRow)rowIterator.next();
            cellIterator = myRow.cellIterator();
            cellStoreVector = new Vector();
            HSSFCell myCell;    
                while (cellIterator.hasNext())
                {
                    myCell=(HSSFCell)cellIterator.next();
                    cellStoreVector.addElement(myCell);
                    System.out.println("next");
                }
             cellVectorHolder.addElement(cellStoreVector);
            }
            }
            
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return cellVectorHolder;
        }
       
       public static void fix() throws FileNotFoundException, IOException {
           String newName = "";
           String newSurname = "";
           
           for (int counter = 4;counter<getData().size();counter++) {
               newName = "";
               newSurname = "";
               
            Vector cellStoreVector = (Vector)dataHolder.elementAt(counter);
            HSSFCell theName= (HSSFCell)cellStoreVector.elementAt(0);
            StringTokenizer st = new StringTokenizer(theName.toString()," ");
            int length = st.countTokens() -1;
            System.out.println(length);
            for (int nameInt =0;nameInt<length;nameInt++) {
                newName += st.nextToken() + " ";
            }
             newSurname += st.nextToken();
            mySheet.getRow(counter).getCell(0).setCellValue(newName);
            HSSFCell cell= mySheet.getRow(counter).createCell(1);
            cell.setCellValue(newSurname);
                    System.out.println(newName);
                    System.out.println(newSurname);
            
           }
           FileOutputStream fos = new FileOutputStream(fileName);
            myWorkBook.write(fos);
            fos.close();
       }
       
    
}

           