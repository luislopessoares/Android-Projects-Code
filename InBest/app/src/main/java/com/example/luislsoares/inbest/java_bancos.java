package com.example.luislsoares.inbest;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luislsoares on 2014-10-23.
 */
public class java_bancos extends Activity {


    SimpleAdapter simpleAdpt=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bancos);

        // carrega lista de dados
        initList();

        // procura listview
        ListView lv = (ListView) findViewById(R.id.listview_Bancos);

        // ## USER INTERACTION ##
        // React to user clicks on item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {

                // We know the View is a TextView so we can cast it
                TextView clickedView = (TextView) view;

                Toast.makeText(java_bancos.this, "Item with id [" + id + "] - Position [" + position + "] - Planet [" + clickedView.getText() + "]", Toast.LENGTH_SHORT).show();

            }
        });
        // we register for the contextmneu
        registerForContextMenu(lv);






        // This is a simple adapter that accepts as parameter
        // Context
        // Data list
        // The row layout that is used during the row creation
        // The keys used to retrieve the data
        // The View id used to show the data. The key number and the view id must match
        simpleAdpt = new SimpleAdapter(this, planetsList, android.R.layout.simple_list_item_1, new String[] {"planet"}, new int[] {android.R.id.text1});
        lv.setAdapter(simpleAdpt);
    }



    // We want to create a context Menu when the user long click on an item
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo aInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // We know that each row in the adapter is a Map
        HashMap map =  (HashMap) simpleAdpt.getItem(aInfo.position);

        menu.setHeaderTitle("Options for " + map.get("planet"));
        menu.add(1, 1, 1, R.string.detalhes);
        menu.add(1, 2, 2, R.string.eliminar);

    }









    // Dados
    List<Map<String, String>> planetsList = new ArrayList<Map<String,String>>();
    private void initList() {
        // We populate the planets

        planetsList.add(createPlanet("planet", "Mercury"));
        planetsList.add(createPlanet("planet", "Venus"));
        planetsList.add(createPlanet("planet", "Mars"));
        planetsList.add(createPlanet("planet", "Jupiter"));
        planetsList.add(createPlanet("planet", "Saturn"));
        planetsList.add(createPlanet("planet", "Uranus"));
        planetsList.add(createPlanet("planet", "Neptune"));

    }

    private HashMap<String, String> createPlanet(String key, String name) {
        HashMap<String, String> planet = new HashMap<String, String>();
        planet.put(key, name);

        return planet;
    }
}
