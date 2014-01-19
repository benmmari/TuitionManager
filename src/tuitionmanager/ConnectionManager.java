package tuitionmanager;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import static tuitionmanager.TuitionManager.getConnection;

/**
 *
 * @author ben
 */
public class ConnectionManager extends SwingWorker<Integer, Object[]>{
   static ArrayList<String> studentNumbers = new ArrayList<String>();
    static Hashtable<String, Double> gradeCost = new Hashtable<String, Double>();
   static  Hashtable<String, Double> busRoute = new Hashtable<String, Double>();
   
   ProgressForm pf;
   JTable table;
   String grade;
   String operation;
   String term;
    Object[] row;

    /**
     * @param args the command line arguments
     */
    public ConnectionManager(JTable table,ProgressForm pf, String grade, String operation, String term) {
        // TODO code application logic here
    this.getStudentNumbers();
    this.table=table;
    this.grade=grade;
    this.operation=operation;
    this.pf=pf;
    this.term=term;
    }
    
        public ConnectionManager() {
        // TODO code application logic here
    this.getStudentNumbers();
    }
    
    public static Connection getConnection() {
         System.out.println("HELLO");
     
        String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=" + "C://System//Annadale.accdb";   
     //String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=" + "C://System//School//Annadale.mdb";   
     
     //anime is the database
        String username = ""; //leave blank if none
        String password = ""; //leave blank if none
        try {
      Class.forName(driver);
     } catch (ClassNotFoundException e) {
         System.out.println("nonesense");
     }
        try { 
      return DriverManager.getConnection(url, username, password);
     } catch (SQLException e) {
                 System.out.println(e.getMessage());
     }
     return null;
    }
    
    public double getCost(String key) {
        return gradeCost.get(key);
    }
    
    public double getBusCost(String key) {
        return busRoute.get(key);
    }
    
    public void getGradeCosts() {
        if (gradeCost.isEmpty()) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql = "";
        try {
            conn = getConnection();
            sql = "SELECT * FROM Classes";
            st = conn.prepareStatement(sql);
            ResultSet rs  = st.executeQuery();
            while (rs.next() ) {
                gradeCost.put(rs.getString(1), rs.getDouble(2));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException e) {
               e.getMessage();
            }
        }
        }
    }
    
    public void getBusCosts() {
        if (busRoute.isEmpty()) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql = "";
        try {
            conn = getConnection();
            sql = "SELECT * FROM Bus_Routes";
            st = conn.prepareStatement(sql);
            ResultSet rs  = st.executeQuery();
            while (rs.next() ) {
                busRoute.put(rs.getString(1), rs.getDouble(2));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException e) {
               e.getMessage();
            }
        }
        }
    }
    
    public void getStudents(JTable table, String grade) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql = "";
        ((DefaultTableModel)table.getModel()).setRowCount(0);
        try {
            conn = getConnection();
            if (grade.equals("All")) {
            sql = "SELECT * FROM Students ORDER BY Grade, STU_ID ";    
            
            }else {
            sql = "SELECT * FROM Students WHERE Grade = '"+grade+"' ORDER BY STU_ID";
            }
            st = conn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs  = st.executeQuery();
            while (rs!=null && rs.next()) {
                if (grade.equals("All")) {
              Object [] rowData  = {rs.getString(1).trim(),rs.getString(2).trim(), rs.getString(3).trim(),0,0,0, rs.getString(4).trim()}; 
              //rowObjects.add(rowData);
             ((DefaultTableModel)table.getModel()).addRow(rowData);
              
            }else {
                      Object [] rowData  = {rs.getString(1).trim(),rs.getString(2).trim(), rs.getString(3).trim(),0 ,0 ,0}; 
                //        rowObjects.add(rowData);
             
                    ((DefaultTableModel)table.getModel()).addRow(rowData);
              
                }
                
            }
            System.out.println("AWE");
        
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
                conn.close();
            }
            catch(SQLException e) {
               e.getMessage();
            }
        }
    }
    
    public double getAmountTotal(String number) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql = "";
        double total= 0;
        try {
            conn = getConnection();
            sql = "SELECT SUM(Amount) FROM Payments WHERE STU_ID = '"+number+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs  = st.executeQuery();
            rs.next();
            total = rs.getDouble(1);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException e) {
               e.getMessage();
            }
        }
        return total;
    }
    
    public void getAllPayments(JTable table) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql = "";
        ((DefaultTableModel)table.getModel()).setRowCount(0);
        try {
            conn = getConnection();
            sql = "SELECT * FROM Payments WHERE Account <> 'Tuition Fees' AND Account <> 'Bus Fees'  ORDER BY Date";
            st = conn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs  = st.executeQuery();
            while (rs!=null && rs.next()) {
                Object [] rowData  = {rs.getDate(3),rs.getString(2).trim(), rs.getString(4).trim(), rs.getString(5).trim()}; 
                ((DefaultTableModel)table.getModel()).addRow(rowData);
            }
            System.out.println("AWE");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
                conn.close();
            }
            catch(SQLException e) {
               e.getMessage();
            }
        }
    }
    
    public void getStudentPayments(JTable table, String number) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql = "";
        ((DefaultTableModel)table.getModel()).setRowCount(0);
        try {
            conn = getConnection();
            sql = "SELECT * FROM Payments WHERE STU_ID = '"+number+"' ORDER BY ID";
            st = conn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs  = st.executeQuery();
            while (rs!=null && rs.next()) {
                Object [] rowData  = {rs.getString(1).trim(),rs.getDate(3).toString(), rs.getString(4).trim(), rs.getString(5).trim()}; 
                ((DefaultTableModel)table.getModel()).addRow(rowData);
            }
            System.out.println("AWE");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
                conn.close();
            }
            catch(SQLException e) {
               e.getMessage();
            }
        }
    }
    
    public String getTeacher(String name) {
        Connection conn =null;
        PreparedStatement st = null;
        String teacher= " ";
        String sql ="";
        try {
            conn = getConnection();
            sql = "SELECT Name FROM Teachers WHERE Grade = '"+name+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            teacher = rs.getString(1).trim();
        }
        catch(SQLException e) {
           e.getMessage();
        }
            return teacher;
    }
    
    public double getBalance(String number) {
        Connection conn =null;
        PreparedStatement st = null;
        double balance= 0;
        String sql ="";
        try {
            conn = getConnection();
            sql = "SELECT SUM(Amount) FROM Payments WHERE STU_ID = '"+number+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            balance = rs.getDouble(1);
        }
        catch(SQLException e) {
           e.getMessage();
        }
            return balance;
    }
    
    public double getPaid(String number) {
        Connection conn =null;
        PreparedStatement st = null;
        double balance= 0;
        String sql ="";
        try {
            conn = getConnection();
            sql = "SELECT SUM(Amount) FROM Payments WHERE STU_ID = '"+number+"' AND Amount < 0";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            balance = rs.getDouble(1);
        }
        catch(SQLException e) {
           e.getMessage();
        }
        
            finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        }
            return balance;
    }
    
    public double getTotalDue(String number) {
        Connection conn =null;
        PreparedStatement st = null;
        double balance= 0;
        String sql ="";
        try {
            conn = getConnection();
            sql = "SELECT SUM(Amount) FROM Payments WHERE STU_ID = '"+number+"' AND Amount > 0";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            balance = rs.getDouble(1);
        }
        catch(SQLException e) {
           e.getMessage();
        }
        
            finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        }
            return balance;
    }
    
    public double getGradeTotal(String grade) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql ="";
        double total = 0;
        try {
            conn = getConnection();
            sql = "SELECT Tuition FROM Classes WHERE ID = '"+grade+"'";
            System.out.println(sql);
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            total = rs.getDouble(1);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        }
         return total;
       
    }
    
    public void addPayment(String number, double amount, String account, String date) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql ="";
        try {
            conn = getConnection();
            sql = "INSERT INTO Payments (STU_ID, [Date], Amount, Account) VALUES(?, ?, ?, ?) ";
            st = conn.prepareStatement(sql);
            st.setString(1, number);
            st.setDate(2, getDate(date));
            st.setDouble(3,amount) ;
            st.setString(4,account+" Payment: Thank you!") ;           
            
            st.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void addPayment(String number, double amount, String account) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql ="";
        try {
            conn = getConnection();
            sql = "INSERT INTO Payments (STU_ID, [Date], Amount, Account) VALUES(?, ?, ?, ?) ";
            st = conn.prepareStatement(sql);
            st.setString(1, number);
            st.setDate(2, getDate());
            st.setDouble(3,amount) ;
            st.setString(4,account) ;
           
            
            st.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public double getTotalPaid(String studentNumber) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql ="";
        double total = 0;
        
        try {
            conn = getConnection();
            sql = "SELECT SUM(Amount) FROM Payments WHERE STU_ID like '"+studentNumber+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            total = rs.getDouble(1);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return total;
    }
    
    public String getLocation(String studentNumber) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql ="";
        String location="";
                
        try {
            conn = getConnection();
            sql = "SELECT Location FROM Students WHERE STU_ID like '"+studentNumber+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            location = rs.getString(1);
            
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return location;
    }
    
        public boolean getBusChoice(String studentNumber) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql ="";
        double total = 0;
        
        try {
            conn = getConnection();
            sql = "SELECT Bus FROM Students WHERE STU_ID like '"+studentNumber+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            String choice = rs.getString(1);
            if (choice.equals("YES")) {
                return true;
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    
    public void fillCosts(JTable table,String grade) {
        String innerGrade = grade;
        int rowCount= ((DefaultTableModel)table.getModel()).getRowCount();
        double overallPaid =0, overallCost =0, overallBal =0;
        for (int index=0;index<rowCount;index++) {
            System.out.println(index);
            if (grade.equals("All")) {
            innerGrade = ((DefaultTableModel)table.getModel()).getValueAt(index, 6).toString();
            }
            double bus=0;
            if (this.getBusChoice(((DefaultTableModel)table.getModel()).getValueAt(index, 0).toString())) {
                bus = this.getBusCost(getLocation(((DefaultTableModel)table.getModel()).getValueAt(index, 0).toString()));
            }
            double cost = getCost(innerGrade);
            overallCost+=cost+bus;
            double totalCost = cost+bus;
            
            ((DefaultTableModel)table.getModel()).setValueAt(totalCost, index, 3);
            double totalPaid = getTotalPaid(((DefaultTableModel)table.getModel()).getValueAt(index, 0).toString());
            overallPaid+=totalPaid;
            ((DefaultTableModel)table.getModel()).setValueAt(totalPaid, index, 4);
            double diff = totalCost - totalPaid;
            overallBal+=diff;
            ((DefaultTableModel)table.getModel()).setValueAt(diff, index, 5);
            
        }
        Object[] row = {rowCount+" Students", "-","-",overallCost, overallPaid, overallBal}; 
        ((DefaultTableModel)table.getModel()).addRow(row);
        
    }
    
    public void fillStudentValues(String stuNumber, JTextField number, JTextField name, JTextField surname, JTextField grade, JTextField totalDue, JTextField paid, JTextField balance) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql ="";
        try {
            conn = getConnection();
            sql = "SELECT * FROM Students WHERE STU_ID = '"+stuNumber+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            number.setText(rs.getString(1));
            name.setText(rs.getString(2));
            surname.setText(rs.getString(3));
            grade.setText(rs.getString(4));
             
        
        }
        catch(SQLException e) {
            e.getMessage();
        }
        
        finally{
            try {
            conn.close();
            }
            
            catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
        
         public void getDetails(String stuNumber, JTextField name, JTextField surname,JTextField grade ) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql ="";
        try {
            conn = getConnection();
            sql = "SELECT * FROM Students WHERE STU_ID = '"+stuNumber+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            name.setText(rs.getString(2));
            surname.setText(rs.getString(3));
            grade.setText(rs.getString(4));
        }
        catch(SQLException e) {
            e.getMessage();
        }
        
        finally{
            try {
            conn.close();
            }
            
            catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        
        
    }
    
    public void getTeachers(JTable table) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql = "";
        ((DefaultTableModel)table.getModel()).setRowCount(0);
        try {
            conn = getConnection();
            sql = "SELECT * FROM Teachers ORDER BY Grade";   
            st = conn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs  = st.executeQuery();
            while (rs!=null && rs.next()) {
                Object [] rowData  = {rs.getString(1).trim(),rs.getString(2).trim(),rs.getString(3).trim()}; 
                ((DefaultTableModel)table.getModel()).addRow(rowData);
                
            }
            System.out.println("AWE");
        
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
                conn.close();
            }
            catch(SQLException e) {
               e.getMessage();
            }
        }
    }
    
    public void getStudentNumbers() {
        Connection conn =null;
        Statement st = null;
        studentNumbers.clear();
        try {
            conn = getConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM Students";
            ResultSet rs  = st.executeQuery(sql);
            while (rs!=null && rs.next()) {
                studentNumbers.add(rs.getString(1));
            }
            System.out.println("Student numbers retrieved :" +studentNumbers.size());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally {
            try {
            conn.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        
    }
    
    public void addToTeachers(String grade, String name) {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO Teachers(Grade, Name) VALUES(?, ?)";
            st = conn.prepareStatement(sql);
            st.setString(1, grade);
            st.setString(2, name);
            st.executeUpdate();
            
            
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        finally  {
            try {
            conn.close();
            }
            
            catch(SQLException e) {
                e.getMessage();
            }
        }
    }
    public void addToClasses(String grade, double tuition, double trip) { 
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO Classes VALUES(?, ?)";
            st = conn.prepareStatement(sql);
            st.setString(1, grade);
            st.setDouble(2,tuition);
            st.executeUpdate();
            
        }
        catch(SQLException e) {
           System.out.println(e.getMessage());
        }
        
        finally {
            try {
            conn.close();
            }
            catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            }
    }
    
    
    public void addToAccount(String number, double cost, String detail) {
    
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
           String sql = "INSERT INTO Payments (STU_ID, [Date], Amount, Account) VALUES(?, ?, ?, ?) ";
            st = conn.prepareStatement(sql);
            st.setString(1, number);
            st.setDate(2, getDate());
            st.setDouble(3,cost) ;
            st.setString(4,detail);
            st.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            //addToStudents( student_ID,name,surname,grade, number++);
            
        }
        
       finally {
            try {
          conn.close();
          this.getStudentNumbers();
            }
            catch (SQLException e) {
                    System.out.println(e.getMessage());
            }
            }
    }
    
    
    public boolean addToStudents(String name, String surname, String grade,double amount) {
        Connection conn = null;
        Statement st = null;
        String number = "";
        try {
            conn = getConnection();
             st = conn.createStatement();
             number  = Work.studentNumber(studentNumbers,name, surname);
            String sql = "INSERT INTO Students(STU_ID, Name, Surname, Grade) VALUES('"+number+"','"+name+"', '"+surname+"', '"+grade+"')";
            st.executeUpdate(sql);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            //addToStudents( student_ID,name,surname,grade, number++);
            
        }
        
       finally {
            try {
          conn.close();
          this.getStudentNumbers();
          this.addToAccount(number, amount, "Tuition Fees");
            }
            catch (SQLException e) {
                    System.out.println(e.getMessage());
            }
            }
        
        return true;
    }
    
     public boolean addToStudents(String name, String surname, String grade,double amount, double fees) {
        Connection conn = null;
        Statement st = null;
        String number = "";
        try {
            conn = getConnection();
             st = conn.createStatement();
             number  = Work.studentNumber(studentNumbers,name, surname);
            String sql = "INSERT INTO Students(STU_ID, Name, Surname, Grade) VALUES('"+number+"','"+name+"', '"+surname+"', '"+grade+"')";
            st.executeUpdate(sql);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            //addToStudents( student_ID,name,surname,grade, number++);
            
        }
        
       finally {
            try {
          conn.close();
          this.getStudentNumbers();
          this.addToAccount(number, fees, "Tuition Fees");
            }
            catch (SQLException e) {
                    System.out.println(e.getMessage());
            }
            }
        
        return true;
    }
    
    public java.sql.Date getDate(String date) {
    java.util.Date today = new java.util.Date();
    java.sql.Date d = java.sql.Date.valueOf(date);    
    return d;
}
    
        public java.sql.Date getDate() {
    java.util.Date today = new java.util.Date();
    java.sql.Date d = new java.sql.Date(today.getTime());
    return d;
}
            public boolean isNumeric(String str)
{
  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
}
            
    public double termBalance (String text, double amount) {
         double newAmount =0;
         double calcAmount = amount/3;
         //newAmount = amount/3;   
         //System.out.println(calcAmount);
         switch(text.charAt(text.length()-1)) {
            case '1': newAmount=amount - (calcAmount*2);
            break;
            case '2': newAmount= amount - (calcAmount*1);
            break;
            case '3': newAmount=amount;
            break;    
        }
         return newAmount;
     }
    
    public void deleteStudent(String number) {
        Connection conn =null;
        PreparedStatement st = null;
        String sql = "";
        double total= 0;
        try {
            conn = getConnection();
            sql = "DELETE FROM Students WHERE STU_ID = '"+number+"'";
            st = conn.prepareStatement(sql);
            ResultSet rs  = st.executeQuery();
            rs.next();
            total = rs.getDouble(1);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException e) {
               e.getMessage();
            }
        }
    }

    @Override
    protected Integer doInBackground() throws Exception { //To change body of generated methods, choose Tools | Templates.

           if (operation.equals("fc")) {
               
        String innerGrade = grade;
        int rowCount= ((DefaultTableModel)table.getModel()).getRowCount();
        double overallPaid =0, overallCost =0, overallBal =0, overallTerm=0;
        for (int index=0;index<rowCount;index++) {
            System.out.println(index);
            if (grade.equals("All")) {
            innerGrade = ((DefaultTableModel)table.getModel()).getValueAt(index, 6).toString();
            }
        //    double bus=0;
          //  if (this.getBusChoice(((DefaultTableModel)table.getModel()).getValueAt(index, 0).toString())) {
            //    bus = this.getBusCost(getLocation(((DefaultTableModel)table.getModel()).getValueAt(index, 0).toString()));
            //}
            
            String student = ((DefaultTableModel)table.getModel()).getValueAt(index, 0).toString();
            double cost = getTotalDue(student);
                    //= getCost(innerGrade);
            overallCost+=cost;
            double totalCost = cost;
            double termCost =termBalance(term,totalCost); 
            overallTerm+=termCost;
            
            Object[] data = {termCost, index, 3};
            publish(data);
            
            double totalPaid=getPaid(student);;
            if (totalPaid!=0){
            totalPaid = -1 * totalPaid;
            }
            overallPaid+=totalPaid;
            Object[] data1 = {totalPaid, index, 4};
            publish(data1);
            
            double diff = termCost - totalPaid;
            overallBal+=diff;
            Object[] data2 = {diff, index, 5};
            publish(data2);
            
        }
        Object[] row = {rowCount+" Students", "-","-",overallTerm, overallPaid, overallBal}; 
        this.row=row;
           }
       
        return 0;
    }
    
    @Override    
    protected void process(final List<Object[]> list) {
        
        for (final Object[] object:list) {
        ((DefaultTableModel)table.getModel()).setValueAt(object[0], (Integer)object[1], (Integer)object[2]);
             }    
    }
    
        @Override    
    protected void done() {
            pf.close();
            ((DefaultTableModel)table.getModel()).addRow(row);
            
    }


}