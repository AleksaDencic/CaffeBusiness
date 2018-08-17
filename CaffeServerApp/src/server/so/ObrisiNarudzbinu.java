/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so;

import domen.IDomainEntity;
import domen.Narudzbina;
import domen.StavkaNarudzbine;

/**
 *
 * @author alekd_000
 */
public class ObrisiNarudzbinu extends AbstractGenericOperation {

    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide == null || !(ide instanceof Narudzbina)) {
            throw new Exception("Greska u validaciji.");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {
        Narudzbina narudzbina = (Narudzbina) ide;
        for (StavkaNarudzbine stavkaNarudzbine : narudzbina.getStavkeNarudzbine()) {
            db.delete(stavkaNarudzbine);
        }
        db.delete(ide);
    }
    
}
