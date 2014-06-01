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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author ben
 */
public class JasperReportPrint {
    
     final String folderPath = "C:/System/reports/" ;
     final String jasperXml = "C:/src/TuitionManager/src/resources/provisional.jrxml";
     final String jasperJasper = "C:/src/TuitionManager/src/resources/provisional.jasper";
     
     Connection con;
     
     public JasperReportPrint() {
         try {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             con = DriverManager.getConnection("jdbc:odbc:TEST_iReport", "administrator", "");   
              JasperCompileManager.compileReportToFile(jasperXml, jasperJasper);
         }
         
         catch(Exception e) {
             
         }
     }
    
    public void printGrade(String grade, ArrayList<String> students) throws JRException {
        try {
        String newPath = folderPath+grade;
        String currentStudent = "";
        int count = students.size();
        HashMap map = new HashMap();
        ArrayList reports = new ArrayList();
        JasperPrint print = null;
        for (int index=0;index<count;index++) {
            currentStudent=students.get(index);
            map.put("student_number", currentStudent);
            reports.add(JasperFillManager.fillReport(jasperJasper, map, con));
            map.clear();
        }
        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, reports);
        pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, newPath+"/"+grade+".pdf");
        pdfExporter.exportReport();           
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void printStudent(String grade, String student) {
        try {
            String newPath = folderPath+grade;
            HashMap map = new HashMap();
            map.put("student_number", student);
            JasperPrint print = JasperFillManager.fillReport(jasperJasper, map, con);

            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, newPath+"/"+student+".pdf");
            pdfExporter.exportReport(); 
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
