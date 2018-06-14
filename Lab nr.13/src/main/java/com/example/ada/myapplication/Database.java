package com.example.klaudiusz.myapplication;
import java.util.LinkedList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "NoteDB";
    private static final String GOOGLE_DRIVE_FILE_NAME = "sqlite_db_backup";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create Note table
        String CREATE_Note_TABLE = "CREATE TABLE Notes ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "subject TEXT, "+
                "description TEXT, "+
                "photo TEXT )";

        // create Notes table
        db.execSQL(CREATE_Note_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older Notes table if existed
        db.execSQL("DROP TABLE IF EXISTS Notes");

        // create fresh Notes table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) Note + get all Notes + delete all Notes
     */

    // Notes table name
    private static final String TABLE_NoteS = "Notes";

    // Notes Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PHOTO = "photo";
    private static final String[] COLUMNS = {KEY_ID,KEY_SUBJECT,KEY_DESCRIPTION, KEY_PHOTO};

    public void addNote(Note note){
        Log.d("addNote", note.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_SUBJECT, note.getSubject()); // get subject
        values.put(KEY_DESCRIPTION, note.getDescription()); // get description
        values.put(KEY_PHOTO,note.getPhoto());
        // 3. insert
        db.insert(TABLE_NoteS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Note getNote(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_NoteS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections 
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build Note object
        Note note = new Note();
        note.setId(Integer.parseInt(cursor.getString(0)));
        note.setSubject(cursor.getString(1));
        note.setDescription(cursor.getString(2));
        note.setPhoto(cursor.getString(3));
        Log.d("getNote("+id+")", note.toString());

        // 5. return Note
        return note;
    }

    // Get All Notes
    public List<Note> getAllNotes() {
        List<Note> Notes = new LinkedList<Note>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NoteS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build Note and add it to list
        Note note = null;
        if (cursor.moveToFirst()) {
            do {
                note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setSubject(cursor.getString(1));
                note.setDescription(cursor.getString(2));
                note.setPhoto(cursor.getString(3));
                // Add Note to Notes
                Notes.add(note);
            } while (cursor.moveToNext());
        }

        Log.d("getAllNotes()", Notes.toString());

        // return Notes
        return Notes;
    }
    // Updating single Note
    public int updateNote(Note note) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("subject", note.getSubject()); // get subject
        values.put("description", note.getDescription()); // get description
        values.put("photo", note.getPhoto());
        // 3. updating row
        int i = db.update(TABLE_NoteS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(note.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single Note
    public void deleteNote(Note note) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_NoteS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(note.getId()) });

        // 3. close
        db.close();

        Log.d("deleteNote", note.toString());

    }
}