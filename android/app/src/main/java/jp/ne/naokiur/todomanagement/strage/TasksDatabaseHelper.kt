package jp.ne.naokiur.todomanagement.strage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import jp.ne.naokiur.todomanagement.models.TaskModel

class TasksDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    @Throws(SQLiteException::class)
    fun insertTask(task: TaskModel): Boolean {
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.TaskEntry.TASK_NAME, task.name)
        values.put(DBContract.TaskEntry.STATUS, task.status)
        values.put(DBContract.TaskEntry.BEGIN_DATE, task.beginData)
        values.put(DBContract.TaskEntry.END_DATE, task.endDate)
        values.put(DBContract.TaskEntry.UPDATE_TIME, task.updateTime)

        val rowId = db.insert(DBContract.TaskEntry.TABLE_NAME, null, values)

        return true
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "TASKS.db"

        private const val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBContract.TaskEntry.TABLE_NAME + " (" +
                        DBContract.TaskEntry.TASK_NAME + " TEXT ," +
                        DBContract.TaskEntry.STATUS + " TEXT," +
                        DBContract.TaskEntry.BEGIN_DATE + " INTEGER," +
                        DBContract.TaskEntry.END_DATE + " INTEGER," +
                        DBContract.TaskEntry.UPDATE_TIME + " INTEGER)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.TaskEntry.TABLE_NAME
    }
}