package mubbi.saveme.configuration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pdidone on 29/06/2015.
 */
public class AdvicesSqLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE advices (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT," +
            " delay INTEGER, contacts TEXT)";

    public AdvicesSqLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS advices");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}
