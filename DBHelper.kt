package com.example.basicbankapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(context: Context):SQLiteOpenHelper(context,DB_NAME,null,DB_VER) {

    companion object{
        private const val DB_NAME = "INFO_DB"
        private const val DB_VER = 1

        private const val TABLE_NAME1 = "CUSTOMERLIST"
        private const val ACC_NAME = "NAME"
        private const val ACC_NO = "ACCOUNT_NO"
        private const val ACC_BAL = "ACCOUNT_BAL"
        private const val ACC_PHONE = "PHONE"
        private const val ACC_EMAIL = "EMAIL"


        private const val TABLE_NAME2 = "TRANSACTION_LIST"
        private const val FROM_ACC = "FROM_ACCOUNT"
        private const val TO_ACC = "TO_ACCOUNT"
        private const val AMOUNT = "AMOUNT"
        private const val FLAG = "STATUS"
        private const val DATE = "DATETIME"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY:String = ("CREATE TABLE $TABLE_NAME1($ACC_NAME TEXT NOT NULL,$ACC_NO TEXT NOT NULL,$ACC_BAL DECIMAL,$ACC_PHONE INTEGER PRIMARY KEY,$ACC_EMAIL TEXT,CHECK ($ACC_BAL>0))")
        db!!.execSQL(CREATE_TABLE_QUERY)

        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('JEON JUNGKOOK','XXXXXXX1234',20000,123456789,'jungkook@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('KIM NAMJOON','XXXXXXX2345',50000,134567890,'namjoon@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('KIM TAEHYUNG','XXXXXXX3456',23000,145678902,'taehyung@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('KIM SEOK JIN','XXXXXXX4566',100000,156789023,'seokjin@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('JUNG HOSEOK','XXXXXXX5678',54000,167890123,'jhope@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('MIN YOONGI','XXXXXXX6789',450000,178901234,'minyoongi@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('PARK JIMIN','XXXXXXX7890',40000,189012345,'jimin@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('LEE JI EUN','XXXXXXX9078',350000,190123457,'iu@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('KIM JISOO','XXXXXXX5476',90000,180124386,'jisoo@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('LISA MANOBAN','XXXXXXX3212',45500,113324170,'lisa@gmail.com')")
        db.execSQL("INSERT INTO $TABLE_NAME1 VALUES('PARK ROSEANNE','XXXXXXX8890',34500,121095789,'rose@gmail.com')")

       val CREATING_TABLE_QUERY:String = ("CREATE TABLE $TABLE_NAME2($FROM_ACC TEXT,$TO_ACC TEXT,$AMOUNT TEXT,$FLAG TEXT,$DATE TEXT)")
      db.execSQL(CREATING_TABLE_QUERY)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME1")
       db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME2")
        onCreate(db)
    }

    val allperson:List<ExampleItem>
        @SuppressLint("Recycle")
        get(){
            val listPerson = ArrayList<ExampleItem>()
            val selectquery = "SELECT * FROM $TABLE_NAME1"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectquery,null)

            if(cursor.moveToFirst()){
                do {
                    val person = ExampleItem()

                    person.Name = cursor.getString(cursor.getColumnIndex(ACC_NAME))
                    person.Acc_No = cursor.getString(cursor.getColumnIndex(ACC_NO))
                    person.Acc_Bal = cursor.getInt(cursor.getColumnIndex(ACC_BAL))
                    person.Email = cursor.getString(cursor.getColumnIndex(ACC_EMAIL))
                    person.Phone = cursor.getInt(cursor.getColumnIndex(ACC_PHONE))


                    listPerson.add(person)

                }while (cursor.moveToNext())
            }
            db.close()
            return listPerson
        }




        fun getList(context:Context):ArrayList<SentAmount>{
            val listPerson = ArrayList<SentAmount>()
            val select_query = "SELECT * FROM $TABLE_NAME2"
            val db = this.writableDatabase
            val cursor = db.rawQuery(select_query,null)
            if(cursor.count==0){Toast.makeText(context,"No Records Found!",Toast.LENGTH_SHORT).show()}
                else {
                while (cursor.moveToNext()) {
                    val transfer = SentAmount()

                    transfer.from = cursor.getString(cursor.getColumnIndex(FROM_ACC))
                    transfer.to = cursor.getString(cursor.getColumnIndex(TO_ACC))
                    transfer.amt = cursor.getString(cursor.getColumnIndex(AMOUNT))
                    transfer.status = cursor.getString(cursor.getColumnIndex(FLAG))
                    transfer.date = cursor.getString(cursor.getColumnIndex(DATE))


                    listPerson.add(transfer)

                }
            }
            cursor.close()
            db.close()
            return listPerson
        }

    fun addperson(person:SentAmount){

        val values = ContentValues()
        values.put(FROM_ACC,person.from)
        values.put(TO_ACC,person.to)
        values.put(AMOUNT,person.amt)
        values.put(FLAG,person.status)
        values.put(DATE,person.date)

        val db = this.writableDatabase
        db.insert(TABLE_NAME2,null,values)
        db.close()
    }
}