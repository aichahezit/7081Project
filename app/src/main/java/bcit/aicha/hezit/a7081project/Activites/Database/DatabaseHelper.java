package bcit.aicha.hezit.a7081project.Activites.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import bcit.aicha.hezit.a7081project.Activites.Models.Doctor;
import bcit.aicha.hezit.a7081project.Activites.Models.User;
import bcit.aicha.hezit.a7081project.Activites.Models.Visit;

/**
 * Created by Aicha on 2016-11-30.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Version
    private static final int DATABASE_VERSION = 2;

    // Database Name.
    private static final String DATABASE_NAME = "MyHealth";

    // Table Name.
    private static final String TABLE_USER = "User";
    private static final String TABLE_DOCTOR = "Doctors";
    private static final String TABLE_VISIT = "Visits";

    //Users table - Column name
    private static final String COLUMN_USER_NAME ="User_Name";
    private static final String COLUMN_USER_DOB ="User_DOB";
    private static final String COLUMN_USER_GENDER ="User_Gender";
    private static final String COLUMN_USER_ADDRESS ="User_Address";

    //Doctor table - column name
    private static final String COLUMN_DOCTOR_NAME ="Doctor_Name";
    private static final String COLUMN_DOCTOR_SPECIALTY ="Doctor_Specialty";
    private static final String COLUMN_DOCTOR_ADDRESS ="Doctor_Address";
    private static final String COLUMN_DOCTOR_COMMENTS ="Doctor_Comments";

    //Visit table - column name
    private static final String COLUMN_VISIT_ID="Visit_ID";
    private static final String COLUMN_VISIT_REASON ="Visit_Reason";
    private static final String COLUMN_VISIT_DATE ="Visit_Date";
    private static final String COLUMN_VISIT_DOCTOR ="Visit_Doctor";
    private static final String COLUMN_VISIT_COMMENTS ="Visit_Comments";

    //Create table user string
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_NAME + " TEXT PRIMARY KEY NOT NULL,"
            + COLUMN_USER_DOB + " TEXT NOT NULL,"
            + COLUMN_USER_GENDER + " TEXT, "
            + COLUMN_USER_ADDRESS + " TEXT"
            + ")";

    //Create table doctor string
    private static final String CREATE_TABLE_DOCTOR = "CREATE TABLE " + TABLE_DOCTOR + "("
            + COLUMN_DOCTOR_NAME + " TEXT PRIMARY KEY, "
            + COLUMN_DOCTOR_SPECIALTY + " TEXT, "
            + COLUMN_DOCTOR_ADDRESS + " TEXT,"
            + COLUMN_DOCTOR_COMMENTS + " TEXT"
            + ")";

    //Create table test type string
    private static final String CREATE_TABLE_VISIT = "CREATE TABLE " + TABLE_VISIT + "("
            + COLUMN_VISIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_VISIT_REASON + " TEXT,"
            + COLUMN_VISIT_DATE + " TEXT,"
            + COLUMN_VISIT_DOCTOR + " TEXT,"
            + COLUMN_VISIT_COMMENTS + " TEXT"
            + ")";

    public DatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table.
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_DOCTOR);
        db.execSQL(CREATE_TABLE_VISIT);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Drop exist table.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISIT);

        // Create table again.
        onCreate(db);
    }

    public void addUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.addUser ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_DOB, user.getDOB());
        values.put(COLUMN_USER_GENDER, user.getGender());
        values.put(COLUMN_USER_ADDRESS, user.getAddress());

        // Add user to table.
        db.insert(TABLE_USER, null, values);

        // Close database connection.
        db.close();
    }

//    /**
//     * log in a user base on the user medical ID and password
//     * also remember that user as current log in
//     * @param user
//     * @return true or false
//     */
//    public boolean login(User user){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE Medical_Id=? AND Password=?", new String[]{user.getMedicalId(), user.getPassword()});
//        if (mCursor != null) {
//            if(mCursor.getCount() > 0)
//            {
//                Log.i(TAG, "MyDatabaseHelper.loginUser ... ");
//                mCursor.close();
//                return true;
//            }
//        }
//        mCursor.close();
//        return false;
//    }

    // Updating a user info
    public int updateUserInfo(User user) {
        Log.i(TAG, "MyDatabaseHelper.updateUserInfo ... ");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_DOB, user.getDOB());
        values.put(COLUMN_USER_GENDER, user.getGender());
        values.put(COLUMN_USER_ADDRESS, user.getAddress());
        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_NAME + " = ?",
                new String[]{String.valueOf(user.getName())});
        db.close();
        return 1;
    }

    // Getting user info
    public User getUser(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, "MyDatabaseHelper.getUser ... " + name);
        //if(medicalId != null) {
        Cursor cursor = db.query(TABLE_USER, new String[]{
                        COLUMN_USER_NAME,
                        COLUMN_USER_DOB,
                        COLUMN_USER_GENDER,
                        COLUMN_USER_ADDRESS,}, COLUMN_USER_NAME + "=?",
                new String[]{name}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        // return user
        return user;
    }

    public String[] checkForUser () {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, "MyDatabaseHelper.checkForUser ... ");

        Cursor  cursor = db.rawQuery("select * from " + TABLE_USER, null);

        String[] users = new String[cursor.getCount()];

        int i = 0;
        //iterate through results and add to array list
        if (cursor != null){
            while (cursor.moveToNext()) {
                User user = new User(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );

                //add doctor to array list
                users[i] = (user.getName());

                i++;
            }
        }
        return users;
    }

    //delete doctor
    public void deleteUser(String name) {
        Log.i(TAG, "MyDatabaseHelper.deleteUser ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER, COLUMN_USER_NAME + "=?",new String[]{name});

        // Close database connection.
        db.close();
    }


    public void addDoctor(String name, String specialty, String address, String comments) {
        Log.i(TAG, "MyDatabaseHelper.addDoctor ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTOR_NAME, name);
        values.put(COLUMN_DOCTOR_SPECIALTY, specialty);
        values.put(COLUMN_DOCTOR_ADDRESS, address);
        values.put(COLUMN_DOCTOR_COMMENTS, comments);

        // Add doctor to table.
        db.insert(TABLE_DOCTOR, null, values);

        // Close database connection.
        db.close();
    }

    // Getting user info
    public Doctor getDoctor(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, "MyDatabaseHelper.getDoctor ... " + name);
        //if(medicalId != null) {
        Cursor cursor = db.query(TABLE_DOCTOR, new String[]{
                        COLUMN_DOCTOR_NAME,
                        COLUMN_DOCTOR_SPECIALTY,
                        COLUMN_DOCTOR_ADDRESS,
                        COLUMN_DOCTOR_COMMENTS,}, COLUMN_DOCTOR_NAME + "=?",
                new String[]{name}, null, null, null);

        if (cursor != null && cursor.getCount() > 0)
            cursor.moveToFirst();
        else
            return null;

        Doctor doctor = new Doctor(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        // return user
        return doctor;
    }

    // Updating a user info
    public int updateDoctor(Doctor doctor) {
        Log.i(TAG, "MyDatabaseHelper.updateDoctor ... ");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTOR_NAME, doctor.getName());
        values.put(COLUMN_DOCTOR_SPECIALTY, doctor.getSpecialty());
        values.put(COLUMN_DOCTOR_ADDRESS, doctor.getAddress());
        values.put(COLUMN_DOCTOR_COMMENTS, doctor.getComments());
        // updating row
        db.update(TABLE_DOCTOR, values, COLUMN_DOCTOR_NAME + " = ?",
                new String[]{String.valueOf(doctor.getName())});
        db.close();
        return 1;
    }

    //delete doctor
    public void deleteDoctor(String name) {
        Log.i(TAG, "MyDatabaseHelper.deleteDoctor ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_DOCTOR, COLUMN_DOCTOR_NAME + "=?",new String[]{name});

        // Close database connection.
        db.close();
    }

    // Get array of doctor names to display in listview
    public String[] getDoctorNames () {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, "MyDatabaseHelper.getDoctors ... ");

        Cursor  cursor = db.rawQuery("select * from " + TABLE_DOCTOR, null);

        String[] doctors = new String[cursor.getCount()];

        int i = 0;
        //iterate through results and add to array list
        if (cursor != null){
            while (cursor.moveToNext()) {
                Doctor doctor = new Doctor(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );

                //add doctor to array list
                doctors[i] = (doctor.getName());

                i++;
            }
        }
        return doctors;
    }


// !!!!!!!!!!!!!!!!!!!!!!!!!

    public void addVisit(String reason, String date, String doctor, String comments) {
        Log.i(TAG, "MyDatabaseHelper.addVisit ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_VISIT_REASON, reason);
        values.put(COLUMN_VISIT_DATE, date);
        values.put(COLUMN_VISIT_DOCTOR, doctor);
        values.put(COLUMN_VISIT_COMMENTS, comments);

        // Add doctor to table.
        db.insert(TABLE_VISIT, null, values);

        // Close database connection.
        db.close();
    }

    // Getting user info
    public Visit getVisit(String reason) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, "MyDatabaseHelper.getVisit ... ");
        //if(medicalId != null) {
        Cursor cursor = db.query(TABLE_VISIT, new String[]{
                        COLUMN_VISIT_REASON,
                        COLUMN_VISIT_DATE,
                        COLUMN_VISIT_DOCTOR,
                        COLUMN_VISIT_COMMENTS,}, COLUMN_VISIT_REASON + "=?",
                new String[]{reason}, null, null, null);

        if (cursor != null && cursor.getCount() > 0)
            cursor.moveToFirst();
        else
            return null;

        Visit visit = new Visit(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        // return user
        return visit;
    }

    // Updating a user info
    public int updateVisit(Visit visit) {
        Log.i(TAG, "MyDatabaseHelper.updateVisit ... ");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VISIT_REASON, visit.getReason());
        values.put(COLUMN_VISIT_DATE, visit.getDate());
        values.put(COLUMN_VISIT_DOCTOR, visit.getDoctor());
        values.put(COLUMN_VISIT_COMMENTS, visit.getComments());
        // updating row
        db.update(TABLE_VISIT, values, COLUMN_VISIT_REASON + " = ?",
                new String[]{String.valueOf(visit.getReason())});
        db.close();
        return 1;
    }

    //delete doctor
    public void deleteVisit(String reason) {
        Log.i(TAG, "MyDatabaseHelper.deleteVisit ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_VISIT, COLUMN_VISIT_REASON + "=?",new String[]{reason});

        // Close database connection.
        db.close();
    }

    // Get array of doctor names to display in listview
    public String[] getVisitNames () {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i(TAG, "MyDatabaseHelper.getVisits ... ");

        Cursor  cursor = db.rawQuery("select * from " + TABLE_VISIT, null);

        String[] visits = new String[cursor.getCount()];

        int i = 0;
        //iterate through results and add to array list
        if (cursor != null){
            while (cursor.moveToNext()) {
                Visit visit = new Visit(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                //add doctor to array list
                visits[i] = (visit.getReason());

                i++;
            }
        }
        return visits;
    }
}

