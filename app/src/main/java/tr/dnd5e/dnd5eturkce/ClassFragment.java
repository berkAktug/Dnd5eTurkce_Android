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

import tr.dnd5e.dnd5eturkce.database.ClassDatabase;

public class ClassFragment extends Fragment {

    private static final String TASKS_KEY = "tr.dnd5e.dnd5eturkce.class_key";

    static List<Class> classList;
    protected static ClassAdapter classAdapter;
    private int rowCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fabSearch);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ClassDatabase.schemaInstance.addData("Fighter", "Then I took arrow to the knee");
//                    ClassDatabase.schemaInstance.addData("Sorcerer", "Master of magic.");
//                    ClassDatabase.schemaInstance.addData("Wizard", "of Coast");
//                    ClassDatabase.schemaInstance.addData("Cleric", "Religious Man");
//                    ClassDatabase.schemaInstance.addData("Barbarian", "Infamous Rage Monster.");
//                    ClassDatabase.schemaInstance.addData("Paladin", "Blade on one hand, Holy symbol on other.");
//                    ClassDatabase.schemaInstance.addData("Druid", "Wild magician (beware Bears)");
//                    ClassDatabase.schemaInstance.addData("Ranger", "Legolas, What does your elf eyes see?");
//                    classList.notifyDataSetChanged();

                    Toast.makeText(getContext(), "Search Button Clicked.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (savedInstanceState == null) {
            ClassDatabase.schemaInstance = new ClassDatabase(getContext());
            classList = new ArrayList<>();
            classList.add(new Class(null, null));
        } else {
            Toast.makeText(getContext(), "Else part triggered in onCreateView of Class Fragment.", Toast.LENGTH_SHORT).show();
            //Not sure about this place.
            classList = savedInstanceState.getParcelableArrayList(TASKS_KEY);
        }

        ListView classListView = view.findViewById(R.id.listView_Class);
        rowCount = 0;
        classList = new ArrayList<>();

        Cursor allClasses = ClassDatabase.schemaInstance.getAllEntities();

        int titleCol = allClasses.getColumnIndex("title");
        int descriptionCol = allClasses.getColumnIndex("description");

        allClasses.moveToFirst();
        rowCount = allClasses.getCount();
        int tempCount = rowCount;


        if (allClasses != null && (tempCount > 0)) {

            do {
                String title = allClasses.getString(titleCol);
                String description = allClasses.getString(descriptionCol);

                classList.add(new Class(title, description));
                tempCount--;
            } while (allClasses.moveToNext());
        }

        classAdapter = new ClassAdapter(getActivity(), classList);
        classListView.setAdapter(classAdapter);

        return view;
    }

}
//
//
//
//public class NoteFragment extends Fragment {
//
//    private static final String TASKS_KEY = "com.notifellow.su.notifellow.notes_key";
//
//    static List<Note> noteList;
//    // protected static NoteAdapter noteAdapter;
//    private SwipeRefreshLayout swipeRefreshLayout;
//    private SharedPreferences shared;
//    private int rowCount;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_notes, container, false);
//        FloatingActionButton fab = view.findViewById(R.id.fabAddNote);
//        if (fab != null) {
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent myIntent = new Intent(NoteFragment.this.getActivity(), NoteCreateActivity.class);
//                    myIntent.putExtra("email", shared.getString("email", "null"));
//                    NoteFragment.this.startActivity(myIntent);
//                }
//            });
//        }
//
//        if (savedInstanceState == null) {
//            NoteCreateActivity.schema = new NoteDBSchema(getContext());
//            noteList = new ArrayList<>();
//            noteList.add(new Note(null, null, null, null, null));
//        } else {
//            noteList = savedInstanceState.getParcelableArrayList(TASKS_KEY);
//        }
//
//        ListView noteListView = view.findViewById(R.id.listView_Notes);
//        rowCount = 0;
//        noteList = new ArrayList<>();
//
//        Cursor allNotes = NoteCreateActivity.schema.getAllNotes();
//
//        int idCol = allNotes.getColumnIndex("ID");
//        int titleCol = allNotes.getColumnIndex("title");
//        int noteCol = allNotes.getColumnIndex("note");
//        int imageCol = allNotes.getColumnIndex("image_path");
//        int emailCol = allNotes.getColumnIndex("email");
//
//        allNotes.moveToFirst();
//        rowCount = allNotes.getCount();
//        int tempCount = rowCount;
//
//        shared = getContext().getSharedPreferences("shared", MODE_PRIVATE);
//        String email = shared.getString("email", "null");//GET EMAIL FROM SHARED
//
//        if (allNotes != null && (tempCount > 0)) {
//
//            do {
//                if (email.equals(allNotes.getString(emailCol))) {
//                    String id = allNotes.getString(idCol);
//                    String title = allNotes.getString(titleCol);
//                    String note = allNotes.getString(noteCol);
//                    String image = allNotes.getString(imageCol);
//
////                    noteList.add(new Note(id, title, note, image, email));
//                }
//                tempCount--;
//            } while (allNotes.moveToNext());
//        }
//
//        noteAdapter = new NoteAdapter(getActivity(), noteList);
//        noteListView.setAdapter(noteAdapter);
//        return view;
//    }
//
//}
