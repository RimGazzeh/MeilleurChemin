package com.rim.meilleurchemin.app;

/**
 * Created by Asus on 29/12/2015.
 */
public class Arc {
    private final String id;
    private final Noeud source;
    private final Noeud destination;
    private final int longeur;

    public Arc(String id, Noeud source, Noeud destination, int longeur) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.longeur = longeur;
    }
    public String getId() {
        return id;
    }
    public Noeud getDestination() {
        return destination;
    }

    public Noeud getSource() {
        return source;
    }
    public int getLongeur() {
        return longeur;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
