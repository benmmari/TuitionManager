/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuitionmanager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
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
public class Spreadsheet {
    
    String fileName;
    Object data;
    FileInputStream input;
    POIFSFileSystem myFileSystem;
    HSSFWorkbook myWorkBook;
    HSSFSheet mySheet;
    Iterator rowIterator;
    Vector cellVectorHolder;
    Vector dataHolder;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> surnames = new ArrayList<String>();
    ArrayList<String> bus = new ArrayList<String>();
    ArrayList<String> location = new ArrayList<String>();
    
    double tuition=0;
    double trip=0;
    ArrayList<String> teachers = new ArrayList<String>();
    
    int length = 0;
    

        public Spreadsheet(String fileName, String sheetName) 
    {
        try 
        {
           this.fileName=fileName;
            input= new FileInputStream(this.fileName);
            myFileSystem= new POIFSFileSystem(input);
            myWorkBook =new HSSFWorkbook(myFileSystem);
            mySheet= myWorkBook.getSheet(sheetName);
            rowIterator= mySheet.rowIterator();  
            cellVectorHolder= new Vector();
            dataHolder=getData();
            convert();
            length = names.size();
        }

        catch (FileNotFoundException ex) {
            //Logger.getLogger(readExcelFile.class.getName()).log(Level.SEVERE, null, ex);
        }
       catch(IOException e)
       {
            //Logger.getLogger(readExcelFile.class.getName()).log(Level.SEVERE, null, e);
       }

    }
        
       public Vector getData()
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
       
       public ArrayList<String> getTeachers() {
           return teachers;
       }
       public void convert() {
           Vector cellStoreVector = null;
           HSSFCell theData = null;
           
           cellStoreVector = (Vector)dataHolder.elementAt(0);
           for (int count=1;count<cellStoreVector.size();count++) {
             System.out.println(count);
           
               theData= (HSSFCell)cellStoreVector.elementAt(count);
            teachers.add(theData.toString());
            System.out.println(theData.toString() + " " +count);
           }
           
           
           cellStoreVector = (Vector)dataHolder.elementAt(1); 
            theData= (HSSFCell)cellStoreVector.elementAt(1);
            tuition = theData.getNumericCellValue();
            
            //cellStoreVector = (Vector)dataHolder.elementAt(2); 
            //theData= (HSSFCell)cellStoreVector.elementAt(1);
            //trip = theData.getNumericCellValue();
           
           for (int index=4;index<cellVectorHolder.size();index++) {
           cellStoreVector = (Vector)dataHolder.elementAt(index);
            HSSFCell theName= (HSSFCell)cellStoreVector.elementAt(0);
            HSSFCell theSurname= (HSSFCell)cellStoreVector.elementAt(1);
            HSSFCell theBus= (HSSFCell)cellStoreVector.elementAt(2);
            HSSFCell theLocation= (HSSFCell)cellStoreVector.elementAt(3);
            
            names.add(theName.toString());
            surnames.add(theSurname.toString());
            bus.add(theBus.toString());
            location.add(theLocation.toString());
            
           }
       }
    
}
