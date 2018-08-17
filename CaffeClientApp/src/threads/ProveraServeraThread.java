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
import logic.Kontroler;

/**
 *
 * @author alekd_000
 */
public class ProveraServeraThread extends Thread {

    public ProveraServeraThread() {
    }

    @Override
    public void run() {
        try {
            boolean working = true;
            while (working) {
                sleep(1000);
                Kontroler.getInstance().javiIskljucenje();
            }
            JOptionPane.showMessageDialog(null, "Server nije u dostupan.");
        } catch(SocketException ex) {
            JOptionPane.showMessageDialog(null, "Server nije u dostupan.");
        }
        catch (IOException ex) {
            Logger.getLogger(ProveraServeraThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProveraServeraThread.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (Exception ex) {
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
