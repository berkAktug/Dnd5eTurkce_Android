package tr.dnd5e.dnd5eturkce.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RacesDatabase extends SQLiteOpenHelper {

    public static RacesDatabase schemaInstance;

    private static final String TAG = RacesDatabase.class.getSimpleName();

    private static final String TABLE_NAME = "RACES_TABLE";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            TITLE + " TEXT PRIMARY KEY UNIQUE, " +
            DESCRIPTION + " TEXT);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public RacesDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public static RacesDatabase getInstance(Context context) {
        if (schemaInstance == null) {
            schemaInstance = new RacesDatabase(context.getApplicationContext());
        }
        return schemaInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
//        RacesDatabase.schemaInstance.addData("Dwarf", "Tough Humanoid which has racial bonuses to stone cutting and weapon smith");
//        RacesDatabase.schemaInstance.addData("Elf", "No Legolas, i will repeat this again for the last time. YOU CANNOT FLY");
//        RacesDatabase.schemaInstance.addData("Human", "There there buddy.. don't get upset because you don't have dark vision..");
//        RacesDatabase.schemaInstance.addData("Dragonborn", "Creature that got magically decedent from dragon-kin. They may or may not have tails.");
//        RacesDatabase.schemaInstance.addData("Half-elf", "Human Bastard (or Elf bastard, depends on which perspective you are looking from)");
//        RacesDatabase.schemaInstance.addData("Half-Orc", "Tough Humanoid with rigid mindset(thanks to their orc blood) and masculine body.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean addData(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE, title);
        contentValues.put(DESCRIPTION, description);

        Log.d(TAG, "addData: Adding " + title + "\nand\n" + description + "\nto " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);
        //if date as inserted incorrectly it will return -1
        return (int) result != -1;
    }

    /**
     * Returns all the Notes from database
     *
     * @return
     */
    public Cursor getAllEntities() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    /**
     * @param title
     * @return
     */
    public String getDescription(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT " + IMAGE_PATH + " FROM " + TABLE_NAME + " WHERE " + ID + " = '" + id + "'";
//        return db.rawQuery(query, null);

        Cursor cursor = db.query(TABLE_NAME, new String[]{DESCRIPTION}, TITLE + " = ? ",
                new String[]{title},null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        String description = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
        cursor.close();
        return description;
    }

    /**
     * Updates the name field
     *
     * @param title Primary key of element (a.k.a. title)
     * @param newDescription String
     */
    public void updateDescription(String title, String newDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DESCRIPTION, newDescription);

        Log.d(TAG, "updateDescription: Setting Description to " + DESCRIPTION);

        db.update(TABLE_NAME, contentValues, TITLE + " = ? ", new String[]{title});
    }

    public int deleteByTitle(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result_code = db.delete(TABLE_NAME, TITLE + " = ? ", new String[]{TITLE});
        return result_code;
    }

}
