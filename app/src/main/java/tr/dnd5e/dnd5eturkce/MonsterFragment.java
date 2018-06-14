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

import tr.dnd5e.dnd5eturkce.database.MonstersDatabase;

public class MonsterFragment extends Fragment {

    private static final String TASKS_KEY = "tr.dnd5e.dnd5eturkce.monster_key";

    static List<Monster> monsterList;
    protected static MonsterAdapter monsterAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monsters, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fabSearch);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    MonstersDatabase.schemaInstance.addData("Goblin", "Green creature with disgusting teeth.");
//                    MonstersDatabase.schemaInstance.addData("Adult Red Dragon", "Magical beast that turns air into fire. Warning: stay away from flame of this creature");
//                    MonstersDatabase.schemaInstance.addData("Unicorn", "My little pony had it easier time with dealing this creature.");
//                    MonstersDatabase.schemaInstance.addData("Beholder", "This creature has many magical eyes which shoots different kinds of magics from each one.");
//                    MonstersDatabase.schemaInstance.addData("Assassin", "\"Hey did you see my invisibility cloa\"-- *sound of throat getting sliced open*");
//                    monsterAdapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Search Button Clicked.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (savedInstanceState == null) {
            MonstersDatabase.schemaInstance = new MonstersDatabase(getContext());
            monsterList = new ArrayList<>();
            monsterList.add(new Monster(null, null));
        } else {
            Toast.makeText(getContext(), "Else part triggered in onCreateView of Class Fragment.", Toast.LENGTH_SHORT).show();
            //Not sure about this place.
            monsterList = savedInstanceState.getParcelableArrayList(TASKS_KEY);
        }

        ListView monsterListView = view.findViewById(R.id.listView_Monsters);
        monsterList = new ArrayList<>();

        Cursor allMonsters = MonstersDatabase.schemaInstance.getAllEntities();

        int titleCol = allMonsters.getColumnIndex("title");
        int descriptionCol = allMonsters.getColumnIndex("description");

        allMonsters.moveToFirst();
        int tempCount = allMonsters.getCount();


        if (allMonsters != null && (tempCount > 0)) {

            do {
                String title = allMonsters.getString(titleCol);
                String description = allMonsters.getString(descriptionCol);

                monsterList.add(new Monster(title, description));
                tempCount--;
            } while (allMonsters.moveToNext());
        }

        monsterAdapter = new MonsterAdapter(getActivity(), monsterList);
        monsterListView.setAdapter(monsterAdapter);

        return view;
    }

}
