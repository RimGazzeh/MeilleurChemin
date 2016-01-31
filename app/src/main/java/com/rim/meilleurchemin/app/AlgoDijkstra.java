package com.rim.meilleurchemin.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Asus on 30/12/2015.
 */
public class AlgoDijkstra {
    private final List<Noeud> noeuds;
    private final List<Arc> arcs;
    private Map<Noeud, Noeud> predecesseurs;
    private Map<Noeud, Integer> distance;
    private Set<Noeud> noeudsVisiter;//settledNodes
    private Set<Noeud> noeudsNonVisiter;


    public AlgoDijkstra(Graphe graph) {
        this.noeuds = new ArrayList<Noeud>(graph.getNoeuds());
        this.arcs = new ArrayList<Arc>(graph.getArcs());
    }

    private int GetCourteDistance(Noeud destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private Noeud GetMinimum(Set<Noeud> noeuds) {
        Noeud minimum = null;
        for (Noeud noeud : noeuds) {
            if (minimum == null) {
                minimum = noeud;
            } else {
                if (GetCourteDistance(noeud) < GetCourteDistance(minimum)) {
                    minimum = noeud;
                }
            }
        }
        return minimum;
    }

    private boolean EstVisiter(Noeud noeud) {
        return noeudsVisiter.contains(noeud);
    }

    private List<Noeud> GetVoisins(Noeud noeud) {
        List<Noeud> voisins = new ArrayList<Noeud>();
        for (Arc arc : arcs) {
            if (arc.getSource().equals(noeud)
                    && !EstVisiter(arc.getDestination())) {
                voisins.add(arc.getDestination());
            }
        }
        return voisins;
    }

    private int getDistance(Noeud noeud, Noeud destination) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(noeud)
                    && arc.getDestination().equals(destination)) {
                return arc.getLongeur();
            }
        }
        throw new RuntimeException("Erreur!!!!!");
    }

    private void TrouverMinDistances(Noeud noeud) {
        List<Noeud> NoeudAdjacents = GetVoisins(noeud);
        for (Noeud noeudCible : NoeudAdjacents) {
            if (GetCourteDistance(noeudCible) > GetCourteDistance(noeud)
                    + getDistance(noeud, noeudCible)) {
                distance.put(noeudCible, GetCourteDistance(noeud)
                        + getDistance(noeud, noeudCible));
                predecesseurs.put(noeudCible, noeud);
                noeudsNonVisiter.add(noeudCible);
            }
        }
    }


    public void AppliquerAlgorithme(Noeud source) {
        noeudsVisiter = new HashSet<Noeud>();
        noeudsNonVisiter = new HashSet<Noeud>();
        distance = new HashMap<Noeud, Integer>();
        predecesseurs = new HashMap<Noeud, Noeud>();
        distance.put(source, 0);
        noeudsNonVisiter.add(source);

        while (noeudsNonVisiter.size() > 0) {
            Noeud noeud = GetMinimum(noeudsNonVisiter);
            noeudsVisiter.add(noeud);
            noeudsNonVisiter.remove(noeud);
            TrouverMinDistances(noeud);
        }
    }

    public LinkedList<Noeud> getPath(Noeud noeudCible) {
        LinkedList<Noeud> path = new LinkedList<Noeud>();
        Noeud saut = noeudCible;
        if (predecesseurs.get(saut) == null) {
            return null;
        }
        path.add(saut);
        while (predecesseurs.get(saut) != null) {
            saut = predecesseurs.get(saut);
            path.add(saut);
        }

        Collections.reverse(path);
        return path;
    }

}
