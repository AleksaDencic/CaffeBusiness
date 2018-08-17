/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domen.Artikal;
import domen.Konobar;
import domen.Narudzbina;
import domen.Sto;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import session.Session;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;
import transfer.util.IOperation;
import transfer.util.IStatus;

/**
 *
 * @author alekd_000
 */
public class Kontroler {
    
    private static Kontroler instance;
    
    private Kontroler(){
    }
    
    public static Kontroler getInstance(){
        if(instance == null)
            instance = new Kontroler();
        return instance;
    }
       
    public List<Konobar> vratiKonobare() throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.VRATI_KONOBARE);
        Konobar konobar = new Konobar();
        request.setData(konobar);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return (List<Konobar>) response.getData();
        } else {
            throw new Exception("Greska: Nastala u toku pronalazenja zaposlenih." + response.getMessage());
        }
    }
    
    public void postaviKlijenta(Konobar konobar) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.POSTAVI_KLIJENTA);
        request.setData(konobar);

        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku postavljanja klijenta." + response.getMessage());
        }

    }
    
    
    public void javiIskljucenje() throws IOException, Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.ISKLJUCI);
        request.setData("Iskljucenje");
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku pronalazenja narudzbina." + response.getMessage());
        }
    }

    public void kreirajKonobara(Konobar konobar) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.KREIRAJ_KONOBARA);
        request.setData(konobar);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku kreiranja konobara." + response.getMessage());
        }
    }

    public Konobar izmeniKonobara(Konobar konobar) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.IZMENI_KONOBARA);
        request.setData(konobar);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return (Konobar) response.getData();
        } else {
            throw new Exception("Greska: Nastala u toku izmene konobara." + response.getMessage());
        }
    }

    public Artikal kreirajArtikal(Artikal artikal) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.KREIRAJ_ARTIKAL);
        request.setData(artikal);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return (Artikal) response.getData();
        } else {
            throw new Exception("Greska: Nastala u toku kreiranja artikla." + response.getMessage());
        }
    }

    public Artikal izmeniArtikal(Artikal artikal) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.IZMENI_ARTIKAL);
        request.setData(artikal);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return (Artikal) response.getData();
        } else {
            throw new Exception("Greska: Nastala u toku izmene artikla." + response.getMessage());
        }
    }

    public List<Artikal> vratiArtikle() throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.VRATI_ARTIKLE);
        Artikal artikal = new Artikal();
        request.setData(artikal);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return (List<Artikal>) response.getData();
        } else {
            throw new Exception("Greska: Nastala u toku pronalazenja artikala." + response.getMessage());
        }
    }

    public void obrisiArtikal(Artikal artikal) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.OBRISI_ARTIKAL);
        request.setData(artikal);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku brisanja artikala." + response.getMessage());
        }
    }

    public void obrisiKonobara(Konobar konobar) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.OBRISI_KONOBARA);
        request.setData(konobar);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku brisanja konobara." + response.getMessage());
        }
    }

    public List<Narudzbina> vratiNarudzbine() throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.VRATI_NARUDZBINE);
        Narudzbina narudzbina = new Narudzbina();
        request.setData(narudzbina);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return (List<Narudzbina>) response.getData();
        } else {
            throw new Exception("Greska: Nastala u toku pronalazenja narudzbina." + response.getMessage());
        }
    }

    public List<Sto> vratiStolove() throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.VRATI_STOLOVE);
        Sto sto = new Sto();
        request.setData(sto);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return (List<Sto>) response.getData();
        } else {
            throw new Exception("Greska: Nastala u toku pronalazenja stolova." + response.getMessage());
        }
    }

    public void obrisiNarudzbinu(Narudzbina narudzbina) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.OBRISI_NARUDZBINU);
        request.setData(narudzbina);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku pronalazenja stolova." + response.getMessage());
        }
    }

    public void kreirajNarudzbinu(Narudzbina narudzbina) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.KREIRAJ_NARUDZBINU);
        request.setData(narudzbina);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku kreiranja narudzbine." + response.getMessage());
        }
    }

    public void stornirajNarudzbinu(Narudzbina narudzbina) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.STORNIRAJ_NARUDZBINU);
        request.setData(narudzbina);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku storniranja narudzbine." + response.getMessage());
        }
    }

    public Narudzbina izmeniNarudzbinu(Narudzbina narudzbina) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.IZMENI_NARUDZBINU);
        request.setData(narudzbina);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return (Narudzbina) response.getData();
        } else {
            throw new Exception("Greska: Nastala u toku izmene narudzbiine." + response.getMessage());
        }
    }

    public void odjaviKonobara(Konobar konobar) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.ODJAVI_KONOBARA);
        request.setData(konobar);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();
        if (code == IStatus.OK) {
            return;
        } else {
            throw new Exception("Greska: Nastala u toku odjave konobara." + response.getMessage());
        }
    }

    
    
}
