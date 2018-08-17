/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so;

import dbb.DatabaseRepository;
import domen.IDomainEntity;
import java.util.List;

/**
 *
 * @author alekd_000
 */
public abstract class AbstractGenericOperation {
    
    DatabaseRepository db;
    List<IDomainEntity> lista;
    IDomainEntity objekat;
    
    public List<IDomainEntity> getLista() {
        return lista;
    }

    public IDomainEntity getObjekat() {
        return objekat;
    }

    public AbstractGenericOperation() {
        db = new DatabaseRepository();
        objekat = null;
    }

    public void templateExecute(IDomainEntity ide) throws Exception {
        try {
          //  validate(ide);
            try {
                validate(ide);
                startTransaction();
                execute(ide);
                commitTransaction();
            } catch (Exception e) {
                rollbackTransaction();
                throw new Exception("Greska: " + e.getMessage());
            }
        } catch (Exception e) {
            throw e;
        }

    }

    protected abstract void validate(IDomainEntity ide) throws Exception;

    private void startTransaction() throws Exception {
        db.startTransaction();
    }

    protected abstract void execute(IDomainEntity ide) throws Exception;

    private void commitTransaction() throws Exception {
        db.commit();
    }

    private void rollbackTransaction() throws Exception {
        db.rollback();
    }
    
}
