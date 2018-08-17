/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author alekd_000
 */
public class Util {
    
    private static Util instance;
    Properties prop;

    private Util() throws Exception {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("db.properties"));
        } catch (Exception ex) {
            throw new Exception("Greska prilikom ucitavanja fajla db.properties\n" + ex);
        }
    }

    public static Util getInstance() throws Exception {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public String getUrl() {
        return prop.getProperty("url");
    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

    public void setUrl(String value) {
        prop.setProperty("url", value);
    }

    public void setUsername(String value) {
        prop.setProperty("username", value);
    }

    public void setPassword(String value) {
        prop.setProperty("password", value);
    }
    
}
