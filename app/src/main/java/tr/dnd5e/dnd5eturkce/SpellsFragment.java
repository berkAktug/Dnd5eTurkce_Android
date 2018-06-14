package tr.dnd5e.dnd5eturkce;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tr.dnd5e.dnd5eturkce.database.SpellsDatabase;

public class SpellsFragment extends Fragment {

    private static final String TASKS_KEY = "tr.dnd5e.dnd5eturkce.spells_key";

    static List<Spells> spellsList;
    protected static SpellsAdapter spellsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spells, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fabSearch);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    SpellsDatabase.schemaInstance.addData("Fireball", "I have a really wonderful spell – Fireball. Now, if I can just remember how it goes.");
//                    SpellsDatabase.schemaInstance.addData("Magic missile", "in D&D only 2 things are guaranteed to hit. Natural 20 and magic missiles.");
//                    SpellsDatabase.schemaInstance.addData("Charm person", "Hello sweetheart. *wink*");
//                    SpellsDatabase.schemaInstance.addData("Fly", "I Believe I can fly, I believe I can touch the sky");
//                    SpellsDatabase.schemaInstance.addData("Wish", "I wish i was the most powerful creature ever existed. *wish spell gone wrong. Erasing the history..*");
//                    spellsAdapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Search Button Clicked.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (savedInstanceState == null) {
            SpellsDatabase.schemaInstance = new SpellsDatabase(getContext());
            spellsList = new ArrayList<>();
            spellsList.add(new Spells(null, null));
        } else {
            Toast.makeText(getContext(), "Else part triggered in onCreateView of Class Fragment.", Toast.LENGTH_SHORT).show();
            //Not sure about this place.
            spellsList = savedInstanceState.getParcelableArrayList(TASKS_KEY);
        }

        ListView spellListView = view.findViewById(R.id.listView_Spells);
        spellsList = new ArrayList<>();

        Cursor allSpells = SpellsDatabase.schemaInstance.getAllEntities();

        int titleCol = allSpells.getColumnIndex("title");
        int descriptionCol = allSpells.getColumnIndex("description");

        allSpells.moveToFirst();
        int tempCount = allSpells.getCount();


        if (allSpells != null && (tempCount > 0)) {

            do {
                String title = allSpells.getString(titleCol);
                String description = allSpells.getString(descriptionCol);

                spellsList.add(new Spells(title, description));
                tempCount--;
            } while (allSpells.moveToNext());
        }

        spellsAdapter = new SpellsAdapter(getActivity(), spellsList);
        spellListView.setAdapter(spellsAdapter);

        return view;
    }

}
