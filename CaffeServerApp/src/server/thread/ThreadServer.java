/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.thread;

import domen.Konobar;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;
import server.form.modeli.ModelTblKlijenti;

/**
 *
 * @author alekd_000
 */
public class ThreadServer extends Thread {
    
    ServerSocket serverSocket;
    List<Thread> clients;
    ModelTblKlijenti mtk;

    public ThreadServer(int port, ModelTblKlijenti mtk) throws IOException {
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
        this.mtk = mtk;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                System.out.println("Cekanje na klijenta");
                Socket socket = serverSocket.accept();
                Thread client = new ThreadClient(socket, mtk, this);
                client.start();
                clients.add(client);
            } catch (Exception ex) {
                
            }
        }
    }

    public void stopThread() throws Exception {
        for (Thread client : clients) {
            ThreadClient clientThread = (ThreadClient) client;
            clientThread.socket.close();
        }
        clients.removeAll(clients);
        serverSocket.close();
    }

    void odjavi(Konobar konobar) {
        mtk.izbaciKlijenta(konobar);
    }
    
    
}
