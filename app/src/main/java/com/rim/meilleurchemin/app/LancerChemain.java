package com.rim.meilleurchemin.app;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.rim.meilleurchemain.app.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LancerChemain extends AppCompatActivity {
    ImageView NOEUD0, NOEUD1, NOEUD2, NOEUD3, NOEUD4, NOEUD5, NOEUD6, NOEUD7, NOEUD8, NOEUD9, NOEUD10;
    ImageView[] tab = {NOEUD0, NOEUD1, NOEUD2, NOEUD3, NOEUD4, NOEUD5, NOEUD6, NOEUD7, NOEUD8, NOEUD9, NOEUD10};

    private List<Noeud> noeuds;
    private List<Arc> arcs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chemain_layout);
        noeuds = new ArrayList<Noeud>();
        arcs = new ArrayList<Arc>();

        int[] locations = new int[2];
        NOEUD0 = (ImageView) findViewById(R.id.emetteur);
        NOEUD1 = (ImageView) findViewById(R.id.n1);
        NOEUD2 = (ImageView) findViewById(R.id.n2);
        NOEUD3 = (ImageView) findViewById(R.id.n3);
        NOEUD4 = (ImageView) findViewById(R.id.n4);
        NOEUD5 = (ImageView) findViewById(R.id.n5);
        NOEUD6 = (ImageView) findViewById(R.id.n6);
        NOEUD7 = (ImageView) findViewById(R.id.n7);
        NOEUD8 = (ImageView) findViewById(R.id.n8);
        NOEUD9 = (ImageView) findViewById(R.id.n9);
        NOEUD10 = (ImageView) findViewById(R.id.emetteur);
        attribuerNoed();
        NOEUD0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Creation();
            }
        });

    }

    public void attribuerNoed() {
        tab[0] = NOEUD0;
        tab[1] = NOEUD1;
        tab[2] = NOEUD2;
        tab[3] = NOEUD3;
        tab[4] = NOEUD4;
        tab[5] = NOEUD5;
        tab[6] = NOEUD6;
        tab[7] = NOEUD7;
        tab[8] = NOEUD8;
        tab[9] = NOEUD9;
        tab[10] = NOEUD10;


    }

    public void Creation() {
        for (int i = 0; i < 11; i++) {
            Noeud location = new Noeud(Integer.toString(i), "noueudNum" + i);
            noeuds.add(location);
        }

        AjouterChemain("0", 0, 1, 75);
        AjouterChemain("1", 0, 2, 187);
        AjouterChemain("2", 2, 3, 273);
        AjouterChemain("3", 1, 4, 386);
        AjouterChemain("4", 1, 5, 153);
        AjouterChemain("5", 2, 5, 193);
        AjouterChemain("6", 2, 6, 841);
        AjouterChemain("7", 3, 6, 187);
        AjouterChemain("8", 4, 8, 140);
        AjouterChemain("9", 5, 7, 102);
        AjouterChemain("10", 6, 7, 100);
        AjouterChemain("11", 8, 10, 600);
        AjouterChemain("12", 9, 10, 600);


        Graphe graph = new Graphe(noeuds, arcs);
        AlgoDijkstra dijkstra = new AlgoDijkstra(graph);
        dijkstra.AppliquerAlgorithme(noeuds.get(0));
        LinkedList<Noeud> path = dijkstra.getPath(noeuds.get(10));

        assert (path != null);
        assert (path.size() > 0);

        for (Noeud noeud : path) {
            String noeudVis = noeud.getId();

            int num = Integer.parseInt(noeudVis);
            if (num != 0 && num != 10)
                tab[num].setBackgroundResource(R.drawable.bts);
            Log.v("mon Chaemain", noeud.toString());

        }

    }


    private void AjouterChemain(String IdChemain, int sourceLocNo, int destLocNo,
                                int duration) {
        Arc Chemain = new Arc(IdChemain, noeuds.get(sourceLocNo), noeuds.get(destLocNo), duration);
        arcs.add(Chemain);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.setStrokeWidth(10);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lancer_recherche, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
