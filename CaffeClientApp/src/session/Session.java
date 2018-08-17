/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domen.Konobar;
import java.net.Socket;

/**
 *
 * @author alekd_000
 */
public class Session {
    
    private static Session instance;
    private Socket socket;
    private Konobar konobar;

    public Session() {
    }
    
    public static Session getInstance(){
        if (instance == null) instance = new Session();
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Konobar getKonobar() {
        return konobar;
    }

    public void setKonobar(Konobar konobar) {
        this.konobar = konobar;
    }
    
    
    
    
    
}
