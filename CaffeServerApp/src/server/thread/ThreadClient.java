/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.thread;

import client.Klijent;
import client.Session;
import domen.Artikal;
import domen.Konobar;
import domen.Narudzbina;
import domen.Sto;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.form.modeli.ModelTblKlijenti;
import server.so.AbstractGenericOperation;
import server.so.IzmeniArtikal;
import server.so.IzmeniKonobara;
import server.so.IzmeniNarudzbinu;
import server.so.ObrisiArtikal;
import server.so.ObrisiKonobara;
import server.so.ObrisiNarudzbinu;
import server.so.PronadjiSveArtikle;
import server.so.PronadjiSveKonobare;
import server.so.PronadjiSveNarudzbine;
import server.so.PronadjiSveStolove;
import server.so.SacuvajArtikal;
import server.so.SacuvajKonobara;
import server.so.SacuvajNarudzbinu;
import server.so.StornirajNarudzbinu;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;
import transfer.util.IOperation;
import transfer.util.IStatus;

/**
 *
 * @author alekd_000
 */
public class ThreadClient extends Thread {
    
    Socket socket;
    ModelTblKlijenti mtk;
    ThreadServer server;

    public ThreadClient(Socket socket, ModelTblKlijenti mtk, ThreadServer server) {
        this.socket = socket;
        this.mtk = mtk;
        this.server = server;
    }

    @Override
    public void run() {
        
        while (!isInterrupted()) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                RequestObject requestObject = (RequestObject) in.readObject();
                ResponseObject responseObject = new ResponseObject();
                switch (requestObject.getOperation()) {
                    case IOperation.VRATI_KONOBARE:
                        Konobar konobar = (Konobar) requestObject.getData();
                        try {
                            AbstractGenericOperation vratiKonobare = new PronadjiSveKonobare();
                            vratiKonobare.templateExecute(konobar);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(vratiKonobare.getLista());
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.POSTAVI_KLIJENTA:
                        konobar = (Konobar) requestObject.getData();
                        try {
                            Klijent klijent = new Klijent(konobar, this);
                            Session.getInstance().getKlijenti().add(klijent);
                            Session.getInstance().getMtk().dodajKlijenta(klijent);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(null);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;  
                    case IOperation.KREIRAJ_KONOBARA:
                        konobar = (Konobar) requestObject.getData();
                        try {
                            AbstractGenericOperation sacuvajKonobara = new SacuvajKonobara();
                            sacuvajKonobara.templateExecute(konobar);
                            responseObject.setCode(IStatus.OK);
                            //responseObject.setData(konobar);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.IZMENI_KONOBARA:
                        konobar = (Konobar) requestObject.getData();
                        try {
                            AbstractGenericOperation izmeniMaterijal = new IzmeniKonobara();
                            izmeniMaterijal.templateExecute(konobar);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(izmeniMaterijal.getObjekat());
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_ARTIKLE:
                        Artikal artikal = (Artikal) requestObject.getData();
                        try {
                            AbstractGenericOperation vratiArtikle = new PronadjiSveArtikle();
                            vratiArtikle.templateExecute(artikal);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(vratiArtikle.getLista());
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.KREIRAJ_ARTIKAL:
                        artikal = (Artikal) requestObject.getData();
                        try {
                            AbstractGenericOperation sacuvajArtikal = new SacuvajArtikal();
                            sacuvajArtikal.templateExecute(artikal);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(artikal);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.IZMENI_ARTIKAL:
                        artikal = (Artikal) requestObject.getData();
                        try {
                            AbstractGenericOperation izmeniArtikal = new IzmeniArtikal();
                            izmeniArtikal.templateExecute(artikal);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(izmeniArtikal.getObjekat());
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;  
                    case IOperation.OBRISI_ARTIKAL:
                        artikal = (Artikal) requestObject.getData();
                        try {
                            AbstractGenericOperation obrisiArtikal = new ObrisiArtikal();
                            obrisiArtikal.templateExecute(artikal);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break; 
                    case IOperation.OBRISI_KONOBARA:
                        konobar = (Konobar) requestObject.getData();
                        try {
                            AbstractGenericOperation obrisiKonobara = new ObrisiKonobara();
                            obrisiKonobara.templateExecute(konobar);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break; 
                    case IOperation.VRATI_NARUDZBINE:
                        Narudzbina narudzbina = (Narudzbina) requestObject.getData();
                        try {
                            AbstractGenericOperation vratiNarudzbine = new PronadjiSveNarudzbine();
                            vratiNarudzbine.templateExecute(narudzbina);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(vratiNarudzbine.getLista());
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;                         
                    case IOperation.VRATI_STOLOVE:
                        Sto sto = (Sto) requestObject.getData();
                        try {
                            AbstractGenericOperation vratiStolove = new PronadjiSveStolove();
                            vratiStolove.templateExecute(sto);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(vratiStolove.getLista());
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.OBRISI_NARUDZBINU:
                        narudzbina = (Narudzbina) requestObject.getData();
                        try {
                            AbstractGenericOperation obrisiNarudzbinu = new ObrisiNarudzbinu();
                            obrisiNarudzbinu.templateExecute(narudzbina);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.KREIRAJ_NARUDZBINU:
                        narudzbina = (Narudzbina) requestObject.getData();
                        try {
                            AbstractGenericOperation sacuvajNarudzbinu = new SacuvajNarudzbinu();
                            sacuvajNarudzbinu.templateExecute(narudzbina);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.STORNIRAJ_NARUDZBINU:
                        narudzbina = (Narudzbina) requestObject.getData();
                        try {
                            AbstractGenericOperation stornirajNarudzbinu = new StornirajNarudzbinu();
                            stornirajNarudzbinu.templateExecute(narudzbina);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.IZMENI_NARUDZBINU:
                        narudzbina = (Narudzbina) requestObject.getData();
                        try {
                            AbstractGenericOperation izmeniNarudzbinu = new IzmeniNarudzbinu();
                            izmeniNarudzbinu.templateExecute(narudzbina);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(narudzbina);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.ODJAVI_KONOBARA:
                        konobar = (Konobar) requestObject.getData();
                        try {
                            server.odjavi(konobar);
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(konobar);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                }
                
                out.writeObject(responseObject);
                out.flush();
            } catch (SocketException ex) {
                break;
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    public void stopThread() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
