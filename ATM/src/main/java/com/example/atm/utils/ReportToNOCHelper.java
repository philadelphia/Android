package com.example.atm.utils;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.atm.entities.TroubleTicketEdit;


@SuppressWarnings("unused")
public class ReportToNOCHelper extends SQLiteOpenHelper {

	private Context context;

	public final static int DATABASE_VERSION = 2;
	public final static String DATABASE_NAME = "REPORT_TO_NOC.db";
	public final static String TABLE_NAME = "report_to_noc";

	public final static String SITEID = "siteID";
	public final static String IMAGEPATH = "ImagePath";
	public final static String IMAGENAME = "ImageName";
	public final static String LOGINID = "loginID";
	public final static String TROBULETICKETID = "trobuleTicketID";

	private Cursor cursor;
	private ArrayList<TroubleTicketEdit> items;

	public ReportToNOCHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME
				+ " ( _id integer primary key autoincrement," + SITEID
				+ " TEXT," + IMAGEPATH + " TEXT," + IMAGENAME + " TEXT,"
				+ LOGINID + " TEXT," + TROBULETICKETID + " TEXT);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	public long insert(String siteID, String imagepath, String imagename,
			String loginid, String trobuleTicketID) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(SITEID, siteID);
		cv.put(IMAGEPATH, imagepath);
		cv.put(IMAGENAME, imagename);
		cv.put(LOGINID, loginid);
		cv.put(TROBULETICKETID, trobuleTicketID);
		long row = db.insert(TABLE_NAME, null, cv);
		return row;
	}

	public void delete(String siteID, String loginID, String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "delete  from " + TABLE_NAME + " where " + SITEID + " = "
				+ "\"" + siteID + "\"" + " and " + LOGINID + " = " + "\""
				+ loginID + "\"" + " and " + IMAGENAME + " = " + "\"" + name
				+ "\"";
		System.out.println(sql);
		db.execSQL(sql);
	}

	public int cancel(String siteID, String loginID, String name,
			String trobuleTicketID) {
		SQLiteDatabase db = this.getWritableDatabase();
		int num = db
				.delete(TABLE_NAME,
						"siteID = ? and loginID = ? and ImageName = ? and trobuleTicketID = ?",
						new String[] { siteID, loginID, name, trobuleTicketID });
		return num;
	}

	public int cancelByPath(String siteID, String loginID,
			String trobuleTicketID, String path) {
		SQLiteDatabase db = this.getWritableDatabase();
		int num = db
				.delete(TABLE_NAME,
						"siteID = ? and loginID = ? and trobuleTicketID = ? and ImagePath = ? ",
						new String[] { siteID, loginID, trobuleTicketID, path });
		return num;
	}

	public TroubleTicketEdit getTrobuleTicketByID(String siteID,
												  String loginID, String name, String trobuleTicketID) {

		SQLiteDatabase db = this.getWritableDatabase();
		TroubleTicketEdit troubleTicketEdit = null;

		cursor = db
				.rawQuery(
						"select * from report_to_noc where siteID = ? and loginID = ? and ImagePath = ? and trobuleTicketID = ? ",
						new String[] { siteID, loginID, name, trobuleTicketID });
		if (cursor != null) {
			while (cursor.moveToNext()) {
				troubleTicketEdit = new TroubleTicketEdit(cursor.getString(1),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4), cursor.getString(5));
			}
		}
		cursor.close();
		db.close();
		return troubleTicketEdit;
	}

	public ArrayList<TroubleTicketEdit> getTrobuleTickets(String siteID,
			String loginID, String trobuleTicketID) {
		SQLiteDatabase db = this.getWritableDatabase();
		TroubleTicketEdit troubleTicketEdit = null;
		items = new ArrayList<TroubleTicketEdit>();
		cursor = db
				.rawQuery(
						"select * from report_to_noc where siteID = ? and loginID = ? and trobuleTicketID = ? ",
						new String[] { siteID, loginID, trobuleTicketID });
		if (cursor != null) {
			while (cursor.moveToNext()) {
				troubleTicketEdit = new TroubleTicketEdit(cursor.getString(1),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4), cursor.getString(5));
				items.add(troubleTicketEdit);
			}
		}
		cursor.close();
		db.close();

		return items;
	}
}
