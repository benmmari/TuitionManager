/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuitionmanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ben
 */
public class reportClassTest {
    
    public static void method() {
        try {
       String student = "MHPSEB000";
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:odbc:TEST_iReport", "administrator", "");
        HashMap map = new HashMap();
        map.put("student_number", student);
        JasperCompileManager.compileReportToFile("C:/src/TuitionManager/src/resources/provisional.jrxml", "C:/src/TuitionManager/src/resources/provisional.jasper");
        System.out.println("begin wait");
        Thread.sleep(5000);
        JasperPrint print = JasperFillManager.fillReport("C:/src/TuitionManager/src/resources/provisional.jasper", map, con);
        OutputStream output = new FileOutputStream(new File("C:/System/"+student+".pdf"));
        JasperExportManager.exportReportToPdfStream(print, output);
        
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            
        }
    }
    
    public static void main(String [] args) {
        method();
    }
    
}
