/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import domen.Konobar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import server.form.modeli.ModelTblKlijenti;

/**
 *
 * @author alekd_000
 */
public class Session {
    
    private static Session instance;
    private Map<String, Object> map;
    static List<Klijent> klijenti;
    private ModelTblKlijenti mtk;

    public Session() {
        map = new HashMap<>();
    }
    
    public static Session getInstance() {
        if (instance == null) {
            klijenti = new ArrayList<>();
            instance = new Session();
        }
        return instance;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }

    public ModelTblKlijenti getMtk() {
        return mtk;
    }

    public void setMtk(ModelTblKlijenti mtk) {
        this.mtk = mtk;
    }

}
