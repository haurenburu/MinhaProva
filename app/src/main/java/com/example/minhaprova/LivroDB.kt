package com.example.minhaprova

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class LivroDB(context: Context) : SQLiteOpenHelper(context, LivroContrato.DATABASE_NAME, null, LivroContrato.DATA_BASE_VERSION) {
    val TAG = "sql"
    val SQL_CREATE_TABLE =
        "CREATE TABLE ${LivroContrato.LivroEntry.TABLE_NAME}" +
                "(${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${LivroContrato.LivroEntry.NOME} TEXT," +
                "${LivroContrato.LivroEntry.AUTOR} TEXT," +
                "${LivroContrato.LivroEntry.NOTA} INTEGER," +
                "${LivroContrato.LivroEntry.ANO} INTEGER)"
    val SQL_DROP_TABLE =
        "DROP TABLE ${LivroContrato.LivroEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(SQL_DROP_TABLE)
            db?.execSQL(SQL_CREATE_TABLE)
        }
    }

    fun insert(l: Livro) {
        var db:SQLiteDatabase = writableDatabase

        try {
            var values = ContentValues()
            values.put(LivroContrato.LivroEntry.NOME, l.nome)
            values.put(LivroContrato.LivroEntry.AUTOR, l.autor)
            values.put(LivroContrato.LivroEntry.NOTA, l.nota)
            values.put(LivroContrato.LivroEntry.ANO, l.ano)

            db.insert(LivroContrato.LivroEntry.TABLE_NAME, null, values)
        } finally {
            db.close()
        }
    }

    fun findById(id: Int): Livro {
        var db: SQLiteDatabase = readableDatabase
        try {
            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor: Cursor = db.query(LivroContrato.LivroEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()

            var livro = Livro()

            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.nome = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.NOME))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroContrato.LivroEntry.AUTOR))
            livro.nota = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.NOTA))
            livro.ano = cursor.getInt(cursor.getColumnIndex(LivroContrato.LivroEntry.ANO))

            return livro

        } finally {
            db.close()
        }
    }
}