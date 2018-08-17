/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alekd_000
 */
public class ProveraIzbacen extends Thread {

    public ProveraIzbacen() {
    }

    @Override
    public void run() {
        try {
            while (!session.Session.getInstance().getSocket().isClosed()) {
                sleep(1000);
            }
            JOptionPane.showMessageDialog(null, "Server nije dostupan.");
        } catch (InterruptedException ex) {
            Logger.getLogger(ProveraServeraThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean availablePort() throws IOException {
        // Assume port is available.
        boolean result = true;

        try {
            (new Socket("127.0.0.1", 9009)).close();

            // Successful connection means the port is taken.
            result = false;
        } catch (SocketException e) {
            // Could not connect.
        }

        return result;
    }
    
    
    
}
