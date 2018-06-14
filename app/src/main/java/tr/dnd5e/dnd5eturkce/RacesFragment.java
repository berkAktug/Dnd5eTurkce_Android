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

import tr.dnd5e.dnd5eturkce.database.RacesDatabase;

public class RacesFragment extends Fragment {

    private static final String TASKS_KEY = "tr.dnd5e.dnd5eturkce.Races_key";

    static List<Races> racesList;
    protected static RacesAdapter racesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_races, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fabSearch);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    RacesDatabase.schemaInstance.addData("Dwarf", "Tough Humanoid which has racial bonuses to stone cutting and weapon smith");
//                    RacesDatabase.schemaInstance.addData("Elf", "No Legolas, i will repeat this again for the last time. YOU CANNOT FLY");
//                    RacesDatabase.schemaInstance.addData("Human", "There there buddy.. don't get upset because you don't have dark vision..");
//                    RacesDatabase.schemaInstance.addData("Dragonborn", "Creature that got magically decedent from dragon-kin. They may or may not have tails.");
//                    RacesDatabase.schemaInstance.addData("Half-elf", "Human Bastard (or Elf bastard, depends on which perspective you are looking from)");
//                    RacesDatabase.schemaInstance.addData("Half-Orc", "Tough Humanoid with rigid mindset(thanks to their orc blood) and masculine body.");
//                    racesAdapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Search Button Clicked.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (savedInstanceState == null) {
            RacesDatabase.schemaInstance = new RacesDatabase(getContext());
            racesList = new ArrayList<>();
            racesList.add(new Races(null, null));
        } else {
            Toast.makeText(getContext(), "Else part triggered in onCreateView of Class Fragment.", Toast.LENGTH_SHORT).show();
            //Not sure about this place.
            racesList = savedInstanceState.getParcelableArrayList(TASKS_KEY);
        }

        ListView racesListView = view.findViewById(R.id.listView_Races);
        racesList = new ArrayList<>();

        Cursor allRaces = RacesDatabase.schemaInstance.getAllEntities();

        int titleCol = allRaces.getColumnIndex("title");
        int descriptionCol = allRaces.getColumnIndex("description");

        allRaces.moveToFirst();
        int tempCount = allRaces.getCount();


        if (allRaces != null && (tempCount > 0)) {

            do {
                String title = allRaces.getString(titleCol);
                String description = allRaces.getString(descriptionCol);

                racesList.add(new Races(title, description));
                tempCount--;
            } while (allRaces.moveToNext());
        }

        racesAdapter = new RacesAdapter(getActivity(), racesList);
        racesListView.setAdapter(racesAdapter);

        return view;
    }

}
