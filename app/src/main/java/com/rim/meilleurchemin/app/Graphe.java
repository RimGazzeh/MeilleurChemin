package com.rim.meilleurchemin.app;

import java.util.List;

/**
 * Created by Asus on 29/12/2015.
 */
public class Graphe {
    public final List<Noeud> noeuds;
    public final List<Arc> arcs;

    public Graphe(List<Noeud> noeuds, List<Arc> arcs) {
        this.noeuds = noeuds;
        this.arcs = arcs;
    }

    public List<Noeud> getNoeuds() {
        return noeuds;
    }

    public List<Arc> getArcs() {
        return arcs;
    }
}
