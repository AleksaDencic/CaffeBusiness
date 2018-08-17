/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so;

import domen.IDomainEntity;
import domen.Konobar;

/**
 *
 * @author alekd_000
 */
public class SacuvajKonobara extends AbstractGenericOperation {

    @Override
    protected void validate(IDomainEntity ide) throws Exception {
        if (ide == null || !(ide instanceof Konobar)) {
            throw new Exception("Greska u validaciji.");
        }
    }

    @Override
    protected void execute(IDomainEntity ide) throws Exception {
        Konobar konobar = (Konobar) ide;
        db.save(konobar);
    }
    
}
