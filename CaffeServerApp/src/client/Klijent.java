/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import domen.Konobar;
import java.util.Objects;

/**
 *
 * @author alekd_000
 */
public class Klijent {
    
    private Konobar konobar;
    private Thread thread;

    public Klijent() {
    }

    public Klijent(Konobar konobar, Thread thread) {
        this.konobar = konobar;
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Konobar getKonobar() {
        return konobar;
    }

    public void setKonobar(Konobar konobar) {
        this.konobar = konobar;
    }

    @Override
    public String toString() {
        return "Klijent{" + "konobar=" + konobar + ", thread=" + thread + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.konobar);
        hash = 37 * hash + Objects.hashCode(this.thread);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.konobar, other.konobar)) {
            return false;
        }
        if (!Objects.equals(this.thread, other.thread)) {
            return false;
        }
        return true;
    }
    
    
    
}
