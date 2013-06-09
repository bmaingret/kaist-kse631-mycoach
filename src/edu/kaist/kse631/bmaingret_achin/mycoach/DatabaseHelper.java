package edu.kaist.kse631.bmaingret_achin.mycoach;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{


	//The Android's default system path of your application database.
	private static String DB_PATH = "";
	private static String DB_NAME = "db.sqlite";
	private final Context myContext;
	public static final int DATABASE_VERSION = 1;
	private static final String TAG = "DatabaseHelper";
	private static final String CREATE_FILE = "create.sql";
	private static final String DELETE_FILE = "delete.sql";

	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		this.myContext = context;
		DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();
		
	}	

	public void onCreate (SQLiteDatabase db){
		try {
			executeFile(db, CREATE_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
		try {
			executeFile(db, DELETE_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			executeFile(db, CREATE_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void executeFile(SQLiteDatabase db, String assetFile) throws IOException {
		// Open the resource
		InputStream insertsStream = myContext.getAssets().open(assetFile);
		BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

		// Iterate through lines (assuming each insert has its own line and theres no other stuff)
		while (insertReader.ready()) {
			String insertStmt = insertReader.readLine();
			db.execSQL(insertStmt);
		}
		insertReader.close();
	}
}