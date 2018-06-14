package tr.dnd5e.dnd5eturkce;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import tr.dnd5e.dnd5eturkce.database.ClassDatabase;
import tr.dnd5e.dnd5eturkce.database.ItemsDatabase;
import tr.dnd5e.dnd5eturkce.database.MonstersDatabase;
import tr.dnd5e.dnd5eturkce.database.RacesDatabase;
import tr.dnd5e.dnd5eturkce.database.SpellsDatabase;


public class MainActivity extends AppCompatActivity {
//        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navBot; //bottom bar
    private FrameLayout mainFrame;

    private ItemFragment    itemFragment;
    private MonsterFragment monsterFragment;
    private RacesFragment   racesFragment;
    private ClassFragment   classFragment;
    private SpellsFragment  spellsFragment;

    private static Context context;

    @Override
    protected void onStart() {
        super.onStart();

        ClassDatabase.schemaInstance.addData("Fighter", "Then I took arrow to the knee");
        ClassDatabase.schemaInstance.addData("Sorcerer", "Master of magic.");
        ClassDatabase.schemaInstance.addData("Wizard", "of Coast");
        ClassDatabase.schemaInstance.addData("Cleric", "Religious Man");
        ClassDatabase.schemaInstance.addData("Barbarian", "Infamous Rage Monster.");
        ClassDatabase.schemaInstance.addData("Paladin", "Blade on one hand, Holy symbol on other.");
        ClassDatabase.schemaInstance.addData("Druid", "Wild magician (beware Bears)");
        ClassDatabase.schemaInstance.addData("Ranger", "Legolas, What does your elf eyes see?");

        ItemsDatabase.schemaInstance.addData("Bag of Holding", "Sakla sakla bitmez.");
        ItemsDatabase.schemaInstance.addData("Deck of Many Things", "Who knows what powers this deck holds?");
        ItemsDatabase.schemaInstance.addData("Sword +1 ", "The usual sword with now +1 enchantment");
        ItemsDatabase.schemaInstance.addData("Eye of Sauron", "The All seer eye. Always watching.. Always judging..");

        MonstersDatabase.schemaInstance.addData("Goblin", "Green creature with disgusting teeth.");
        MonstersDatabase.schemaInstance.addData("Adult Red Dragon", "Magical beast that turns air into fire. Warning: stay away from flame of this creature");
        MonstersDatabase.schemaInstance.addData("Unicorn", "My little pony had it easier time with dealing this creature.");
        MonstersDatabase.schemaInstance.addData("Beholder", "This creature has many magical eyes which shoots different kinds of magics from each one.");
        MonstersDatabase.schemaInstance.addData("Assassin", "\"Hey did you see my invisibility cloa\"-- *sound of throat getting sliced open*");

        RacesDatabase.schemaInstance.addData("Dwarf", "Tough Humanoid which has racial bonuses to stone cutting and weapon smith");
        RacesDatabase.schemaInstance.addData("Elf", "No Legolas, i will repeat this again for the last time. YOU CANNOT FLY");
        RacesDatabase.schemaInstance.addData("Human", "There there buddy.. don't get upset because you don't have dark vision..");
        RacesDatabase.schemaInstance.addData("Dragonborn", "Creature that got magically decedent from dragon-kin. They may or may not have tails.");
        RacesDatabase.schemaInstance.addData("Half-elf", "Human Bastard (or Elf bastard, depends on which perspective you are looking from)");
        RacesDatabase.schemaInstance.addData("Half-Orc", "Tough Humanoid with rigid mindset(thanks to their orc blood) and masculine body.");

        SpellsDatabase.schemaInstance.addData("Fireball", "I have a really wonderful spell – Fireball. Now, if I can just remember how it goes.");
        SpellsDatabase.schemaInstance.addData("Magic missile", "in D&D only 2 things are guaranteed to hit. Natural 20 and magic missiles.");
        SpellsDatabase.schemaInstance.addData("Charm person", "Hello sweetheart. *wink*");
        SpellsDatabase.schemaInstance.addData("Fly", "I Believe I can fly, I believe I can touch the sky");
        SpellsDatabase.schemaInstance.addData("Wish", "I wish i was the most powerful creature ever existed. *wish spell gone wrong. Erasing the history..*");

        MonsterFragment.monsterAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrame = findViewById(R.id.main_frame);
        navBot = findViewById(R.id.NavBot);


        itemFragment = new ItemFragment();
        monsterFragment = new MonsterFragment();
        racesFragment = new RacesFragment();
        classFragment = new ClassFragment();
        spellsFragment = new SpellsFragment();


//        final Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Schedule");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlue));
//        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorBlue));
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
//        setSupportActionBar(toolbar);

//        TextView textView = findViewById(R.id.fragment_home_text_view_placeholder);

        navBot.getMenu().getItem(2).setChecked(true);

        ItemsDatabase.schemaInstance = ItemsDatabase.getInstance(getApplicationContext());
        ClassDatabase.schemaInstance = ClassDatabase.getInstance(getApplicationContext());
        RacesDatabase.schemaInstance = RacesDatabase.getInstance(getApplicationContext());
        SpellsDatabase.schemaInstance = SpellsDatabase.getInstance(getApplicationContext());
        MonstersDatabase.schemaInstance = MonstersDatabase.getInstance(getApplicationContext());

        setFragment(monsterFragment);

        navBot.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.navigation_items:
//                        toolbar.setTitle("Items");
                        setFragment(itemFragment);
                        return true;
                    case R.id.navigation_races:
//                        toolbar.setTitle("Races");
                        setFragment(racesFragment);
                        return true;
                    case R.id.navigation_monsters:
//                        toolbar.setTitle("Monsters");
                        setFragment(monsterFragment);
                        return true;
                    case R.id.navigation_spells:
//                        toolbar.setTitle("Spells");
                        setFragment(spellsFragment);
                        return true;
                    case R.id.navigation_class:
//                        toolbar.setTitle("Class");
                        setFragment(classFragment);
                        return true;
                }

                return false;
            }
        });

//        NavigationView navigationView = findViewById(R.id.);
//        navigationView.setNavigationItemSelectedListener(this);


        MainActivity.context = getApplicationContext();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }


    private void setFragment(Fragment current) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, current).commit();
    }

}
