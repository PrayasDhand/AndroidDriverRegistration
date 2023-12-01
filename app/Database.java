import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DriverDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseHelper.TABLE_NAME + " (" +
                    DatabaseHelper.COLUMN_ID + " INTEGER PRIMARY KEY," +
                    DatabaseHelper.COLUMN_FIRST_NAME + " TEXT," +
                    DatabaseHelper.COLUMN_LAST_NAME + " TEXT," +
                    DatabaseHelper.COLUMN_AGE + " INTEGER," +
                    DatabaseHelper.COLUMN_MOBILE_NUMBER + " TEXT," +
                    DatabaseHelper.COLUMN_LICENSE_IMAGE_PATH + " BLOB," + DatabaseHelper.COLUMN_VEHICLE_TYPE + " TEXT")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseHelper.TABLE_NAME;
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}


