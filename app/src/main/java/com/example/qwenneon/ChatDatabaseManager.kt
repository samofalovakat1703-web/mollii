import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChatDatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_MESSAGE TEXT, $COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addMessage(message: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply { 
            put(COLUMN_MESSAGE, message)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllMessages(): List<String> {
        val messages = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                messages.add(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return messages
    }

    companion object {
        private const val DATABASE_NAME = "ChatDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "ChatMessages"
        private const val COLUMN_ID = "id"
        private const val COLUMN_MESSAGE = "message"
        private const val COLUMN_TIMESTAMP = "timestamp"
    }
}