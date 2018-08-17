/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author alekd_000
 */
public class DatabaseConnection {
    
    private final Connection connection;
    private static DatabaseConnection instance;
    
    private DatabaseConnection() throws Exception{
        connection = DriverManager.getConnection(Util.getInstance().getUrl(), Util.getInstance().getUsername(), Util.getInstance().getPassword());
    }

    public Connection getConnection() {
        return connection;
    }
    
    public static DatabaseConnection getInstance() throws Exception{
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    
}
