/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dell
 */
public class MySqlConnection implements DbConnection{

    @Override
    public Connection openConnection() {
            String username="root";
            String password="newpassword";
            String database="vroom";
    try{
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn;
    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+
            database,username,password);
        return conn;
    }catch(Exception e){
        return null;
    }
    }

    @Override
    public void closeConnection(Connection conn) {
                       try{
           if(conn!=null &&!conn.isClosed()){
            conn.close();
       }
       }catch(Exception e){
       }
    
    }
    
}
