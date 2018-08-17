/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so;

import domen.Artikal;
import domen.IDomainEntity;
import domen.Konobar;
import domen.Narudzbina;
import domen.StavkaNarudzbine;
import domen.Sto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alekd_000
 */
public class PronadjiSveNarudzbine extends AbstractGenericOperation {

    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide == null || !(ide instanceof Narudzbina)) {
            throw new Exception("Greska u validaciji.");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {      
        lista = db.findAll(ide);
        for (IDomainEntity iDomainEntity : lista) {
            Narudzbina narudzbina = (Narudzbina) iDomainEntity;
            narudzbina.setKonobar((Konobar) db.findById(narudzbina.getKonobar()));
            narudzbina.setSto((Sto) db.findById(narudzbina.getSto()));
            StavkaNarudzbine stavkaNarudzbine = new StavkaNarudzbine();
            stavkaNarudzbine.setNarudzbina(narudzbina);
            List<IDomainEntity> stavkeNarudzbine = db.findAllWithCond(stavkaNarudzbine);
            List<StavkaNarudzbine> stavke = new ArrayList<>();
            for (IDomainEntity iDomainEntity1 : stavkeNarudzbine) {
                stavke.add((StavkaNarudzbine) iDomainEntity1);
            }
            for (StavkaNarudzbine s : stavke) {
                s.setArtikal((Artikal) db.findById(s.getArtikal()));
            }
            narudzbina.setStavkeNarudzbine(stavke);
        }
    }
        
}
