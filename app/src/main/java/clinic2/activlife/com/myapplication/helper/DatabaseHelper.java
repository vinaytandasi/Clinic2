package clinic2.activlife.com.myapplication.helper;

import clinic2.activlife.com.myapplication.db.AppointmentDetailsTable;
import clinic2.activlife.com.myapplication.db.PatientTable;
import clinic2.activlife.com.myapplication.model.Tag;
import clinic2.activlife.com.myapplication.model.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private Context context;
	
	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	public static final String DATABASE_NAME = "PhysioClinicSoftware";

	// Table Names
	private static final String TABLE_TODO = "todos";
	private static final String TABLE_TAG = "tags";
	private static final String TABLE_TODO_TAG = "todo_tags";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";

	// NOTES Table - column names
	private static final String KEY_TODO = "todo";
	private static final String KEY_STATUS = "status";

	// TAGS Table - column names
	private static final String KEY_TAG_NAME = "tag_name";

	// NOTE_TAGS Table - column names
	private static final String KEY_TODO_ID = "todo_id";
	private static final String KEY_TAG_ID = "tag_id";

	public DatabaseHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		context = ctx;
		Log.i("DatabaseHelper", "Constructor called.");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.i("DatabaseHelper", "Calling on create.");
		PatientTable.createTable(context, db);
		AppointmentDetailsTable.createTable(context, db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		//Assert.fail("DB upgrade not supported");
		PatientTable.upgradeTable(context, db, oldVersion, newVersion);
		try {
			AppointmentDetailsTable.upgradeTable(context, db, oldVersion, newVersion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ------------------------ "todos" table methods ----------------//

	/*
	 * Creating a todo
	 */
	public long createToDo(Todo todo, long[] tag_ids) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TODO, todo.getNote());
		values.put(KEY_STATUS, todo.getStatus());
		values.put(KEY_CREATED_AT, getDateTime());

		// insert row
		long todo_id = db.insert(TABLE_TODO, null, values);

		// insert tag_ids
		for (long tag_id : tag_ids) {
			createTodoTag(todo_id, tag_id);
		}

		return todo_id;
	}

	/*
	 * get single todo
	 */
	public Todo getTodo(long todo_id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
				+ KEY_ID + " = " + todo_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Todo td = new Todo();
		td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
		td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

		return td;
	}

	/**
	 * getting all todos
	 * */
	public List<Todo> getAllToDos() {
		List<Todo> todos = new ArrayList<Todo>();
		String selectQuery = "SELECT  * FROM " + TABLE_TODO;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Todo td = new Todo();
				td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
				td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

				// adding to todo list
				todos.add(td);
			} while (c.moveToNext());
		}

		return todos;
	}

	/**
	 * getting all todos under single tag
	 * */
	public List<Todo> getAllToDosByTag(String tag_name) {
		List<Todo> todos = new ArrayList<Todo>();

		String selectQuery = "SELECT  * FROM " + TABLE_TODO + " td, "
				+ TABLE_TAG + " tg, " + TABLE_TODO_TAG + " tt WHERE tg."
				+ KEY_TAG_NAME + " = '" + tag_name + "'" + " AND tg." + KEY_ID
				+ " = " + "tt." + KEY_TAG_ID + " AND td." + KEY_ID + " = "
				+ "tt." + KEY_TODO_ID;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Todo td = new Todo();
				td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
				td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

				// adding to todo list
				todos.add(td);
			} while (c.moveToNext());
		}

		return todos;
	}

	/*
	 * getting todo count
	 */
	public int getToDoCount() {
		String countQuery = "SELECT  * FROM " + TABLE_TODO;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);

		int count = cursor.getCount();
		cursor.close();

		// return count
		return count;
	}

	/*
	 * Updating a todo
	 */
	public int updateToDo(Todo todo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TODO, todo.getNote());
		values.put(KEY_STATUS, todo.getStatus());

		// updating row
		return db.update(TABLE_TODO, values, KEY_ID + " = ?",
				new String[] { String.valueOf(todo.getId()) });
	}

	/*
	 * Deleting a todo
	 */
	public void deleteToDo(long tado_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TODO, KEY_ID + " = ?",
				new String[] { String.valueOf(tado_id) });
	}

	// ------------------------ "tags" table methods ----------------//

	/*
	 * Creating tag
	 */
	public long createTag(Tag tag) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TAG_NAME, tag.getTagName());
		values.put(KEY_CREATED_AT, getDateTime());

		// insert row
		long tag_id = db.insert(TABLE_TAG, null, values);

		return tag_id;
	}

	/**
	 * getting all tags
	 * */
	public List<Tag> getAllTags() {
		List<Tag> tags = new ArrayList<Tag>();
		String selectQuery = "SELECT  * FROM " + TABLE_TAG;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Tag t = new Tag();
				t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				t.setTagName(c.getString(c.getColumnIndex(KEY_TAG_NAME)));

				// adding to tags list
				tags.add(t);
			} while (c.moveToNext());
		}
		return tags;
	}

	/*
	 * Updating a tag
	 */
	public int updateTag(Tag tag) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TAG_NAME, tag.getTagName());

		// updating row
		return db.update(TABLE_TAG, values, KEY_ID + " = ?",
				new String[] { String.valueOf(tag.getId()) });
	}

	/*
	 * Deleting a tag
	 */
	public void deleteTag(Tag tag, boolean should_delete_all_tag_todos) {
		SQLiteDatabase db = this.getWritableDatabase();

		// before deleting tag
		// check if todos under this tag should also be deleted
		if (should_delete_all_tag_todos) {
			// get all todos under this tag
			List<Todo> allTagToDos = getAllToDosByTag(tag.getTagName());

			// delete all todos
			for (Todo todo : allTagToDos) {
				// delete todo
				deleteToDo(todo.getId());
			}
		}

		// now delete the tag
		db.delete(TABLE_TAG, KEY_ID + " = ?",
				new String[] { String.valueOf(tag.getId()) });
	}

	// ------------------------ "todo_tags" table methods ----------------//

	/*
	 * Creating todo_tag
	 */
	public long createTodoTag(long todo_id, long tag_id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TODO_ID, todo_id);
		values.put(KEY_TAG_ID, tag_id);
		values.put(KEY_CREATED_AT, getDateTime());

		long id = db.insert(TABLE_TODO_TAG, null, values);

		return id;
	}

	/*
	 * Updating a todo tag
	 */
	public int updateNoteTag(long id, long tag_id) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TAG_ID, tag_id);

		// updating row
		return db.update(TABLE_TODO, values, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	/*
	 * Deleting a todo tag
	 */
	public void deleteToDoTag(long id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TODO, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

	/**
	 * get datetime
	 * */
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

}
