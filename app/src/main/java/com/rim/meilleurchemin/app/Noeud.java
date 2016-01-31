package com.rim.meilleurchemin.app;

/**
 * Created by Asus on 29/12/2015.
 */
public class Noeud {
    final private String id;
    final private String nom;

    public Noeud(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Noeud autre = ( Noeud) obj;
        if (id == null) {
            if (autre.id != null)
                return false;
        } else if (!id.equals(autre.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
}
