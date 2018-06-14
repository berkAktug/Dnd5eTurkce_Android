package tr.dnd5e.dnd5eturkce.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SpellsDatabase extends SQLiteOpenHelper {

    public static SpellsDatabase schemaInstance;

    private static final String TAG = SpellsDatabase.class.getSimpleName();

    private static final String TABLE_NAME = "SPELLS_TABLE";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" +
            TITLE + " TEXT PRIMARY KEY UNIQUE, " +
            DESCRIPTION + " TEXT);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SpellsDatabase(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public static SpellsDatabase getInstance(Context context) {
        if (schemaInstance == null) {
            schemaInstance = new SpellsDatabase(context.getApplicationContext());
        }
        return schemaInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
//        SpellsDatabase.schemaInstance.addData("Fireball", "I have a really wonderful spell – Fireball. Now, if I can just remember how it goes.");
//        SpellsDatabase.schemaInstance.addData("Magic missile", "in D&D only 2 things are guaranteed to hit. Natural 20 and magic missiles.");
//        SpellsDatabase.schemaInstance.addData("Charm person", "Hello sweetheart. *wink*");
//        SpellsDatabase.schemaInstance.addData("Fly", "I Believe I can fly, I believe I can touch the sky");
//        SpellsDatabase.schemaInstance.addData("Wish", "I wish i was the most powerful creature ever existed. *wish spell gone wrong. Erasing the history..*");
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
