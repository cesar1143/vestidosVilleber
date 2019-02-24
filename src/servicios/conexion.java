package servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conexion {

//    private static String ip = "vestidosAry.mssql.somee.com";
//    private static String db = "vestidosAry";
//    private static String usuario = "csar1143_SQLLogin_1";
//    private static String contrasena = "312agkee9m";

  /*  private static String ip = "localhost";
    private static String db = "vestidosaryam2";
    private static String usuario = "sa";
    private static String contrasena = "princesaazteka"; 
     
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://" + ip + ":1433;databaseName=" + db;
      
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driver);
            System.out.println("se conecto por driver");
             
        } catch (Exception e) {
            System.out.println("no conecto por el driver");
            e.getMessage();
        }
        try {
            con = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("conectado por datos bd");
       
        } catch (Exception e) {
            e.getMessage();
            System.out.println("no conecta por datos de db");
           

        }
        return con;
    }
    */
    
    private static Connection cnx = null;
   public static Connection getConnection()  {
      if (cnx == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/bd_aryam", "root", "");
        System.out.println("se conecto por driver");
         } catch (Exception ex) {
             System.out.println("eee " + ex.getMessage());
            throw new ClassCastException(ex.getMessage());
         }
      }else{
          System.out.println("esto pasa");
      }
      return cnx;
   }
   public static void cerrar() throws SQLException {
      if (cnx != null) {
         cnx.close();
      }
   }
}
