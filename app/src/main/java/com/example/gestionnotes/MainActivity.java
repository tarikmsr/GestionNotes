package com.example.gestionnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;

public class MainActivity extends AppCompatActivity { //implements AdapterView.OnItemSelectedListener

    ListView listItems;
    Spinner spinner;
    TextView note , moyenne;
    Integer nbrModules = 6;
    List<Module> listOfModules = new ArrayList<Module>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Finding Views elements by ID
        listItems = findViewById(R.id.idlist);
        spinner = findViewById(R.id.idspinner);
        note = findViewById(R.id.idnote);
        note.setFilters( new InputFilter[]{ new MinMaxFilter("0.0","20.0")}) ;
        moyenne = findViewById(R.id.idmoyenne);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.modules));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void selectModule(View v) {
        // declaration vars
        String module_ = spinner.getSelectedItem().toString();
        double note_ = Double.parseDouble(note.getText().toString());
        Boolean itemAlreadyExists;
        Module newelement = new Module(module_, note_);

        //traitement
        if( listOfModules.size() != 0) {
            //test l'existance d'un module
            itemAlreadyExists = checkIfItemAlreadyExists(listOfModules , module_);
            if( !itemAlreadyExists){
                listOfModules.add(newelement);
                updateListView(listOfModules);
            }else{
                Toast toast = Toast.makeText(MainActivity.this , "Ce Module Deja Existe !" ,Toast.LENGTH_SHORT );
                toast.show();
            }
        }else{
            //saisir du premier element
            listOfModules.add(newelement);
            updateListView(listOfModules);
        }
    }
    //Toast toast = Toast.makeText(MainActivity.this , "Module Deja Existe !" ,Toast.LENGTH_SHORT );
    //Toast toast = Toast.makeText(MainActivity.this ,String.valueOf(moyenne),Toast.LENGTH_LONG );
    //toast.show();


    //check if the module's mark already added
    private Boolean checkIfItemAlreadyExists(List<Module> listOfModules, String module) {
        for (int i = 0; i < listOfModules.size() ; i++){
            if( listOfModules.get(i).toString().contains(module) ){
                return true;
            }
        }
        return false;
    }



    private void updateListView(List<Module> listOfModules) {
        ArrayList arrayMemory = new ArrayList();
        float somme = 0;
        for (int i = 0; i < listOfModules.size() ; i++){
            arrayMemory.add(
                    listOfModules.get(i).getModuleName() + " \t\t\t\t\t\t\t " + listOfModules.get(i).getNote()
            );
            somme += listOfModules.get(i).getNote();
        }
        ArrayAdapter ad = new ArrayAdapter(this , android.R.layout.simple_list_item_1 ,arrayMemory );
        listItems.setAdapter(ad);

        //Calcul moyenne
        if (listOfModules.size() == nbrModules){
             float moy = somme/nbrModules;
             moyenne.setText(Float.toString(moy));
        }

    }

}