/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Database;

import java.sql.Connection;
/**
 *
 * @author Dell
 */
interface DbConnection {
        Connection openConnection();
    void closeConnection(Connection conn);  
}
