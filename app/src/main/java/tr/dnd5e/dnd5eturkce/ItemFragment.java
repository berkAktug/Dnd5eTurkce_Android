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

import tr.dnd5e.dnd5eturkce.database.ItemsDatabase;

public class ItemFragment extends Fragment {

    private static final String TASKS_KEY = "tr.dnd5e.dnd5eturkce.item_key";

    static List<Item> itemList;
    protected static ItemAdapter itemAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fabSearch);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    ItemsDatabase.schemaInstance.addData("Bag of Holding", "Sakla sakla bitmez.");
//                    ItemsDatabase.schemaInstance.addData("Deck of Many Things", "Who knows what powers this deck holds?");
//                    ItemsDatabase.schemaInstance.addData("Sword +1 ", "The usual sword with now +1 enchantment");
//                    ItemsDatabase.schemaInstance.addData("Eye of Sauron", "The All seer eye. Always watching.. Always judging..");
//                    itemAdapter.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Search Button Clicked.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (savedInstanceState == null) {
            ItemsDatabase.schemaInstance = new ItemsDatabase(getContext());
            itemList = new ArrayList<>();
            itemList.add(new Item(null, null));
        } else {
            Toast.makeText(getContext(), "Else part triggered in onCreateView of Class Fragment.", Toast.LENGTH_SHORT).show();
            //Not sure about this place.
            itemList = savedInstanceState.getParcelableArrayList(TASKS_KEY);
        }

        ListView itemListView = view.findViewById(R.id.listView_Items);
        itemList = new ArrayList<>();

        Cursor allItems = ItemsDatabase.schemaInstance.getAllEntities();

        int titleCol = allItems.getColumnIndex("title");
        int descriptionCol = allItems.getColumnIndex("description");

        allItems.moveToFirst();
        int tempCount = allItems.getCount();


        if (allItems != null && (tempCount > 0)) {

            do {
                String title = allItems.getString(titleCol);
                String description = allItems.getString(descriptionCol);

                itemList.add(new Item(title, description));
                tempCount--;
            } while (allItems.moveToNext());
        }

        itemAdapter = new ItemAdapter(getActivity(), itemList);
        itemListView.setAdapter(itemAdapter);

        return view;
    }

}
