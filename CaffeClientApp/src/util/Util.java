/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author alekd_000
 */
public class Util {
    
    private static Util instance;
    Properties prop;
    
    private Util() throws Exception{
        prop = new Properties();
        try {
            prop.load(new FileInputStream("connect.properties"));
        } catch (Exception ex) {
            throw new Exception("Greska prilikom ucitavanja fajla connect.properties" + ex);
        }
    }
    
    public static Util getInstance() throws Exception{
        if(instance == null)
            instance = new Util();
        return instance;
    }
    
    public String getPort() {
        return prop.getProperty("port");
    }

    public String getIP() {
        return prop.getProperty("ip");
    }

    public void setPort(String value) {
        prop.setProperty("port", value);
    }

    public void setIP(String value) {
        prop.setProperty("ip", value);
    }
}
