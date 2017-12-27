package com.sdzx.government.governmentpersonnel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sdzx.government.governmentpersonnel.bean.Group_info;
import com.sdzx.government.governmentpersonnel.bean.RsdaInfo;
import com.sdzx.government.governmentpersonnel.bean.rsda_info;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

	private static final String TAG = "DBHelper";

	private Class mClazz;
	private static SQLiteDatabase db;

	private DBOpenHelper dbOpenHelper;

	public DBHelper(Context context, String DB_NAME, int db_version) {
		this.dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, db_version);
		establishDb();
	}

	public DBHelper() {
		// TODO Auto-generated constructor stub
	}


	private void establishDb() {
		if (this.db == null) {
			this.db = this.dbOpenHelper.getWritableDatabase();
		}
	}

	public void cleanup() {
		if (this.db != null) {
			this.db.close();
			this.db = null;
		}
	}

	/**
	 * 关闭数据库
	 */
	public void closeDataBase() {
		db.close();
		dbOpenHelper = null;
		db = null;
	}

	/**
	 * 删除数据库
	 */
	public boolean deleteDataBase(Context context, String db_name) {
		return context.deleteDatabase(db_name);
	}

	/**
	 * 根据制定类名创建表
	 */
	public void createTable(Class<?> clazz) {
		mClazz = clazz;
		deleteTable(mClazz);
		db.execSQL(getCreateTableSql(mClazz));
	}

	/**
	 * 删除数据库中指定的表 表结构
	 *
	 * @param clazz
	 */
	public void deleteTable(Class<?> clazz) {
		String tbname = DBUtils.getTableName(clazz);
		db.execSQL("DROP TABLE IF EXISTS " + tbname);
	}

	/**
	 * 删除数据库中指定的表 表数据
	 * @param clazz
	 */
	public void deleteTableBase(Class<?> clazz) {
		db.delete(DBUtils.getTableName(clazz), null, null);
	}

	/**
	 * 插入一条数据
	 *
	 * @param obj
	 * @return 返回-1代表插入数据库失败，否则成功
	 * @throws IllegalAccessException
	 */
	public long insert(Object obj) {
		Class<?> modeClass = obj.getClass();
		Field[] fields = modeClass.getDeclaredFields();
		ContentValues values = new ContentValues();

		for (Field fd : fields) {
			fd.setAccessible(true);
			String fieldName = fd.getName();
			// 剔除主键id值得保存，由于框架默认设置id为主键自动增长
			if (fieldName.equalsIgnoreCase("id")
					|| fieldName.equalsIgnoreCase("_id")) {
				continue;
			}
			putValues(values, fd, obj);
		}
		return db.insert(DBUtils.getTableName(modeClass), null, values);
	}


	/**
	 * 查询所有部门
	 * @param tableName 表名
	 * @return
	 */
    public List<Group_info> findAllCursor(String tableName) {
        Cursor cursor = db.query(tableName, null, null, null, null,
                null, null);
        List<Group_info> groupInfoList=new ArrayList<>();
        Group_info ginfo = new Group_info(0,0,0,"全部部门");
        groupInfoList.add(ginfo);
        while (cursor.moveToNext()) {
            Group_info info = new Group_info();
            info.setName(cursor.getString(cursor.getColumnIndex("name")));
            info.setId(cursor.getInt(cursor.getColumnIndex("id")));
            info.setPid(cursor.getInt(cursor.getColumnIndex("pid")));
            info.setType(cursor.getInt(cursor.getColumnIndex("type")));
            info.setSort(cursor.getInt(cursor.getColumnIndex("sort")));
            groupInfoList.add(info);
        }
        return groupInfoList;

    }

	/**
	 * 查找游标
	 * @param tableName 表名
	 * @param rsid id
	 * @return
	 */
	public Cursor findJtcyCursor(String tableName,int rsid) {

		Cursor cursor = db.query(tableName, null, "rs_id="+rsid, null, null,
				null, null);

		return cursor;

	}

	/**
	 * 通过id查找制定数据
	 */
	public String findById(String tName, int id) {
		Cursor cursor = db.query(tName, null, "id=" + id, null,
				null, null, null);
		cursor.moveToNext();
		String cName=cursor.getString(cursor.getColumnIndex("name"));
		return cName;
	}
	/**
	 * 通过state查找制定数据
	 */
	public List<RsdaInfo> findAll() {
		Cursor cursor = db.query("rsda_info", null,null, null,
				null, null,"seq ASC", null);
		List<RsdaInfo> rsdaInfoList=new ArrayList<RsdaInfo>();
		while (cursor.moveToNext()) {
			RsdaInfo info = new RsdaInfo();
			info.setId(cursor.getInt(cursor.getColumnIndex("id")));
			info.setName(cursor.getString(cursor.getColumnIndex("name")));
			info.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
			info.setOrigin(cursor.getString(cursor.getColumnIndex("origin")));
			info.setNation(GetCS.getMzCs(cursor.getInt(cursor.getColumnIndex("nation"))));
			info.setRdtime(cursor.getString(cursor.getColumnIndex("rdtime")));

			info.setWorktime(cursor.getString(cursor.getColumnIndex("worktime")));
			info.setHealthy(cursor.getString(cursor.getColumnIndex("healthy")));
			info.setZyjszw(cursor.getString(cursor.getColumnIndex("zyjszw")));
			info.setSxzyzc(cursor.getString(cursor.getColumnIndex("sxzyzc")));
			info.setBianzhi(cursor.getString(cursor.getColumnIndex("bianzhi")));
			info.setDrsj(cursor.getString(cursor.getColumnIndex("drsj")));
			info.setXrzsj(cursor.getString(cursor.getColumnIndex("xrzsj")));
			info.setXrzjsj(cursor.getString(cursor.getColumnIndex("xrzjsj")));

			info.setQr_xl(cursor.getString(cursor.getColumnIndex("qr_xl")));
			info.setQr_byyx(cursor.getString(cursor.getColumnIndex("qr_byyx")));
			info.setQr_xw(cursor.getString(cursor.getColumnIndex("qr_xw")));
			info.setQr_xjzy(cursor.getString(cursor.getColumnIndex("qr_xjzy")));
			info.setQr_bysj(cursor.getString(cursor.getColumnIndex("qr_bysj")));
			info.setQr_xlzp(cursor.getString(cursor.getColumnIndex("qr_xlzp")));
			info.setQr_xwzp(cursor.getString(cursor.getColumnIndex("qr_xwzp")));
			info.setGzdwzw(cursor.getString(cursor.getColumnIndex("gzdwzw")));
			info.setTxzp(cursor.getString(cursor.getColumnIndex("txzp")));

			info.setXzjb(GetCS.getXzjbCs(cursor.getInt(cursor.getColumnIndex("xzjb"))));

			info.setRmfj(cursor.getString(cursor.getColumnIndex("rmfj")));
			info.setSfzh(cursor.getString(cursor.getColumnIndex("sfzh")));
			info.setZjzp(cursor.getString(cursor.getColumnIndex("zjzp")));
			info.setJianli(cursor.getString(cursor.getColumnIndex("jianli")));
			info.setJlqk(cursor.getString(cursor.getColumnIndex("jlqk")));
			info.setNdkhjg(cursor.getString(cursor.getColumnIndex("ndkhjg")));
			info.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));

			info.setRetire_time(cursor.getString(cursor.getColumnIndex("retire_time")));
			info.setRetire_attach(cursor.getString(cursor.getColumnIndex("retire_attach")));
			info.setLeave_time(cursor.getString(cursor.getColumnIndex("leave_time")));
			info.setLeave_attach(cursor.getString(cursor.getColumnIndex("leave_attach")));
			info.setAddtime(cursor.getInt(cursor.getColumnIndex("addtime")));
			try{
				int ssbm=cursor.getInt(cursor.getColumnIndex("ssbm"));
				info.setSsbm(findById("kjj_group_info",ssbm));
			}catch (Exception e){

			}
			info.setSex(GetCS.getSexCs(cursor.getInt(cursor.getColumnIndex("sex"))));
			info.setZzmm(GetCS.getZzmmCs(cursor.getInt(cursor.getColumnIndex("zzmm"))));


			rsdaInfoList.add(info);
		}
		return rsdaInfoList;
	}
	/**
	 * 通过state查找制定数据
	 */
	public List<RsdaInfo> findByState(int state) {
		Cursor cursor = db.query("rsda_info", null, "state=" + state, null,
				null, null,"seq ASC", null);
		List<RsdaInfo> rsdaInfoList=new ArrayList<RsdaInfo>();
		while (cursor.moveToNext()) {
			RsdaInfo info = new RsdaInfo();
			info.setId(cursor.getInt(cursor.getColumnIndex("id")));
			info.setName(cursor.getString(cursor.getColumnIndex("name")));
			info.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
			info.setOrigin(cursor.getString(cursor.getColumnIndex("origin")));
			info.setNation(GetCS.getMzCs(cursor.getInt(cursor.getColumnIndex("nation"))));
			info.setRdtime(cursor.getString(cursor.getColumnIndex("rdtime")));

			info.setWorktime(cursor.getString(cursor.getColumnIndex("worktime")));
			info.setHealthy(cursor.getString(cursor.getColumnIndex("healthy")));
			info.setZyjszw(cursor.getString(cursor.getColumnIndex("zyjszw")));
			info.setSxzyzc(cursor.getString(cursor.getColumnIndex("sxzyzc")));
			info.setBianzhi(cursor.getString(cursor.getColumnIndex("bianzhi")));
			info.setDrsj(cursor.getString(cursor.getColumnIndex("drsj")));
			info.setXrzsj(cursor.getString(cursor.getColumnIndex("xrzsj")));
			info.setXrzjsj(cursor.getString(cursor.getColumnIndex("xrzjsj")));

			info.setQr_xl(cursor.getString(cursor.getColumnIndex("qr_xl")));
			info.setQr_byyx(cursor.getString(cursor.getColumnIndex("qr_byyx")));
			info.setQr_xw(cursor.getString(cursor.getColumnIndex("qr_xw")));
			info.setQr_xjzy(cursor.getString(cursor.getColumnIndex("qr_xjzy")));
			info.setQr_bysj(cursor.getString(cursor.getColumnIndex("qr_bysj")));
			info.setQr_xlzp(cursor.getString(cursor.getColumnIndex("qr_xlzp")));
			info.setQr_xwzp(cursor.getString(cursor.getColumnIndex("qr_xwzp")));
			info.setGzdwzw(cursor.getString(cursor.getColumnIndex("gzdwzw")));
			info.setTxzp(cursor.getString(cursor.getColumnIndex("txzp")));

			info.setXzjb(GetCS.getXzjbCs(cursor.getInt(cursor.getColumnIndex("xzjb"))));

			info.setRmfj(cursor.getString(cursor.getColumnIndex("rmfj")));
			info.setSfzh(cursor.getString(cursor.getColumnIndex("sfzh")));
			info.setZjzp(cursor.getString(cursor.getColumnIndex("zjzp")));
			info.setJianli(cursor.getString(cursor.getColumnIndex("jianli")));
			info.setJlqk(cursor.getString(cursor.getColumnIndex("jlqk")));
			info.setNdkhjg(cursor.getString(cursor.getColumnIndex("ndkhjg")));
			info.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));

			info.setRetire_time(cursor.getString(cursor.getColumnIndex("retire_time")));
			info.setRetire_attach(cursor.getString(cursor.getColumnIndex("retire_attach")));
			info.setLeave_time(cursor.getString(cursor.getColumnIndex("leave_time")));
			info.setLeave_attach(cursor.getString(cursor.getColumnIndex("leave_attach")));
			info.setAddtime(cursor.getInt(cursor.getColumnIndex("addtime")));
			try{
				int ssbm=cursor.getInt(cursor.getColumnIndex("ssbm"));
				info.setSsbm(findById("kjj_group_info",ssbm));
			}catch (Exception e){

			}
			info.setSex(GetCS.getSexCs(cursor.getInt(cursor.getColumnIndex("sex"))));
			info.setZzmm(GetCS.getZzmmCs(cursor.getInt(cursor.getColumnIndex("zzmm"))));


			rsdaInfoList.add(info);
		}
		return rsdaInfoList;
	}
    /**
     * 通过state查找制定数据
     */
    public List<RsdaInfo> findByStateAndBm(int state,int ssbm) {
		String[] aa=new String[]{state+"",ssbm+""};
        Cursor cursor = db.query("rsda_info", null, "state=? and ssbm=?", new String[]{state+"",ssbm+""},
                null, null,"seq ASC", null);
        List<RsdaInfo> rsdaInfoList=new ArrayList<RsdaInfo>();
        while (cursor.moveToNext()) {
            RsdaInfo info = new RsdaInfo();
			info.setId(cursor.getInt(cursor.getColumnIndex("id")));
            info.setName(cursor.getString(cursor.getColumnIndex("name")));
            info.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
            info.setNation(cursor.getString(cursor.getColumnIndex("nation")));
            info.setOrigin(cursor.getString(cursor.getColumnIndex("origin")));
            info.setRdtime(cursor.getString(cursor.getColumnIndex("rdtime")));

            info.setWorktime(cursor.getString(cursor.getColumnIndex("worktime")));
            info.setHealthy(cursor.getString(cursor.getColumnIndex("healthy")));
            info.setZyjszw(cursor.getString(cursor.getColumnIndex("zyjszw")));
            info.setSxzyzc(cursor.getString(cursor.getColumnIndex("sxzyzc")));
            info.setBianzhi(cursor.getString(cursor.getColumnIndex("bianzhi")));
            info.setDrsj(cursor.getString(cursor.getColumnIndex("drsj")));
            info.setXrzsj(cursor.getString(cursor.getColumnIndex("xrzsj")));
            info.setXrzjsj(cursor.getString(cursor.getColumnIndex("xrzjsj")));

            info.setQr_xl(cursor.getString(cursor.getColumnIndex("qr_xl")));
            info.setQr_byyx(cursor.getString(cursor.getColumnIndex("qr_byyx")));
            info.setQr_xw(cursor.getString(cursor.getColumnIndex("qr_xw")));
            info.setQr_xjzy(cursor.getString(cursor.getColumnIndex("qr_xjzy")));
            info.setQr_bysj(cursor.getString(cursor.getColumnIndex("qr_bysj")));
            info.setQr_xlzp(cursor.getString(cursor.getColumnIndex("qr_xlzp")));
            info.setQr_xwzp(cursor.getString(cursor.getColumnIndex("qr_xwzp")));
            info.setGzdwzw(cursor.getString(cursor.getColumnIndex("gzdwzw")));
            info.setTxzp(cursor.getString(cursor.getColumnIndex("txzp")));

            info.setXzjb(GetCS.getXzjbCs(cursor.getInt(cursor.getColumnIndex("xzjb"))));

            info.setRmfj(cursor.getString(cursor.getColumnIndex("rmfj")));
            info.setSfzh(cursor.getString(cursor.getColumnIndex("sfzh")));
            info.setZjzp(cursor.getString(cursor.getColumnIndex("zjzp")));
            info.setJianli(cursor.getString(cursor.getColumnIndex("jianli")));
            info.setJlqk(cursor.getString(cursor.getColumnIndex("jlqk")));
            info.setNdkhjg(cursor.getString(cursor.getColumnIndex("ndkhjg")));
            info.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));

            info.setRetire_time(cursor.getString(cursor.getColumnIndex("retire_time")));
            info.setRetire_attach(cursor.getString(cursor.getColumnIndex("retire_attach")));
            info.setLeave_time(cursor.getString(cursor.getColumnIndex("leave_time")));
            info.setLeave_attach(cursor.getString(cursor.getColumnIndex("leave_attach")));
            info.setAddtime(cursor.getInt(cursor.getColumnIndex("addtime")));
            try{
                int ssbmdh=cursor.getInt(cursor.getColumnIndex("ssbm"));
                info.setSsbm(findById("kjj_group_info",ssbmdh));
            }catch (Exception e){

            }
            info.setSex(GetCS.getSexCs(cursor.getInt(cursor.getColumnIndex("sex"))));
            info.setZzmm(GetCS.getZzmmCs(cursor.getInt(cursor.getColumnIndex("zzmm"))));


            rsdaInfoList.add(info);
        }
        return rsdaInfoList;
    }
	/**
	 * 通过state查找制定数据
	 */
	public List<RsdaInfo> findByCs(String ssbm,String jb,String zzmm,String xb,String xl,String bz) {
		List<String> li=new ArrayList<>();
		List<String> keyList=new ArrayList<>();
		if (!"0".equals(ssbm)){
			keyList.add("ssbm=? ");
			li.add(ssbm);
		}
		if (!"0".equals(jb)){
			li.add(jb);
			keyList.add("xzjb=? ");
		}
		if (!"0".equals(zzmm)){
			li.add(zzmm);
			keyList.add("zzmm=? ");
		}
		if (!"0".equals(xb)){
			keyList.add("sex=? ");
			li.add(xb);
		}
		if (!"0".equals(xl)){
			li.add(xl);
			keyList.add("qr_xl=? ");
		}
		if (!"".equals(bz)){
			li.add(bz);
			keyList.add("bianzhi=? ");
		}
		String[] arr = (String[])li.toArray(new String[li.size()]);
		String key="";
		for (int i = 0; i <keyList.size() ; i++) {
			key+=keyList.get(i);
			if (i<keyList.size()-1){
				key+=" and ";
			}
		}
		Log.v("key",key);
		Log.v("list",arr.toString());
		Cursor cursor = db.query("rsda_info", null, key, arr,
				null, null,"seq ASC", null);
		List<RsdaInfo> rsdaInfoList=new ArrayList<RsdaInfo>();
		while (cursor.moveToNext()) {
			RsdaInfo info = new RsdaInfo();
			info.setId(cursor.getInt(cursor.getColumnIndex("id")));
			info.setName(cursor.getString(cursor.getColumnIndex("name")));
			info.setBirth(cursor.getString(cursor.getColumnIndex("birth")));
			info.setNation(cursor.getString(cursor.getColumnIndex("nation")));
			info.setOrigin(cursor.getString(cursor.getColumnIndex("origin")));
			info.setRdtime(cursor.getString(cursor.getColumnIndex("rdtime")));

			info.setWorktime(cursor.getString(cursor.getColumnIndex("worktime")));
			info.setHealthy(cursor.getString(cursor.getColumnIndex("healthy")));
			info.setZyjszw(cursor.getString(cursor.getColumnIndex("zyjszw")));
			info.setSxzyzc(cursor.getString(cursor.getColumnIndex("sxzyzc")));
			info.setBianzhi(cursor.getString(cursor.getColumnIndex("bianzhi")));
			info.setDrsj(cursor.getString(cursor.getColumnIndex("drsj")));
			info.setXrzsj(cursor.getString(cursor.getColumnIndex("xrzsj")));
			info.setXrzjsj(cursor.getString(cursor.getColumnIndex("xrzjsj")));

			info.setQr_xl(cursor.getString(cursor.getColumnIndex("qr_xl")));
			info.setQr_byyx(cursor.getString(cursor.getColumnIndex("qr_byyx")));
			info.setQr_xw(cursor.getString(cursor.getColumnIndex("qr_xw")));
			info.setQr_xjzy(cursor.getString(cursor.getColumnIndex("qr_xjzy")));
			info.setQr_bysj(cursor.getString(cursor.getColumnIndex("qr_bysj")));
			info.setQr_xlzp(cursor.getString(cursor.getColumnIndex("qr_xlzp")));
			info.setQr_xwzp(cursor.getString(cursor.getColumnIndex("qr_xwzp")));
			info.setGzdwzw(cursor.getString(cursor.getColumnIndex("gzdwzw")));
			info.setTxzp(cursor.getString(cursor.getColumnIndex("txzp")));

			info.setXzjb(GetCS.getXzjbCs(cursor.getInt(cursor.getColumnIndex("xzjb"))));

			info.setRmfj(cursor.getString(cursor.getColumnIndex("rmfj")));
			info.setSfzh(cursor.getString(cursor.getColumnIndex("sfzh")));
			info.setZjzp(cursor.getString(cursor.getColumnIndex("zjzp")));
			info.setJianli(cursor.getString(cursor.getColumnIndex("jianli")));
			info.setJlqk(cursor.getString(cursor.getColumnIndex("jlqk")));
			info.setNdkhjg(cursor.getString(cursor.getColumnIndex("ndkhjg")));
			info.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));

			info.setRetire_time(cursor.getString(cursor.getColumnIndex("retire_time")));
			info.setRetire_attach(cursor.getString(cursor.getColumnIndex("retire_attach")));
			info.setLeave_time(cursor.getString(cursor.getColumnIndex("leave_time")));
			info.setLeave_attach(cursor.getString(cursor.getColumnIndex("leave_attach")));
			info.setAddtime(cursor.getInt(cursor.getColumnIndex("addtime")));
			try{
				int ssbmdh=cursor.getInt(cursor.getColumnIndex("ssbm"));
				info.setSsbm(findById("kjj_group_info",ssbmdh));
			}catch (Exception e){

			}
			info.setSex(GetCS.getSexCs(cursor.getInt(cursor.getColumnIndex("sex"))));
			info.setZzmm(GetCS.getZzmmCs(cursor.getInt(cursor.getColumnIndex("zzmm"))));


			rsdaInfoList.add(info);
		}
		return rsdaInfoList;
	}


	/**
	 * 从数据库得到实体类
	 *
	 * @param cursor
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	private <T> List<T> getEntity(Cursor cursor, Class<T> clazz) {
		List<T> list = new ArrayList<>();
		try {
			if (cursor != null && cursor.moveToFirst()) {
				do {
					Field[] fields = clazz.getDeclaredFields();
					T modeClass = clazz.newInstance();
					for (Field field : fields) {
						Class<?> cursorClass = cursor.getClass();
						String columnMethodName = getColumnMethodName(field
								.getType());
						Method cursorMethod = cursorClass.getMethod(
								columnMethodName, int.class);

						Object value = cursorMethod.invoke(cursor,
								cursor.getColumnIndex(field.getName()));

						if (field.getType() == boolean.class
								|| field.getType() == Boolean.class) {
							if ("0".equals(String.valueOf(value))) {
								value = false;
							} else if ("1".equals(String.valueOf(value))) {
								value = true;
							}
						} else if (field.getType() == char.class
								|| field.getType() == Character.class) {
							value = ((String) value).charAt(0);
						} else if (field.getType() == Date.class) {
							long date = (Long) value;
							if (date <= 0) {
								value = null;
							} else {
								value = new Date(date);
							}
						}
						String methodName = makeSetterMethodName(field);
						Method method = clazz.getDeclaredMethod(methodName,
								field.getType());
						method.invoke(modeClass, value);
					}
					list.add(modeClass);
				} while (cursor.moveToNext());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return list;
	}

	private String getColumnMethodName(Class<?> fieldType) {
		String typeName;
		if (fieldType.isPrimitive()) {
			typeName = DBUtils.capitalize(fieldType.getName());
		} else {
			typeName = fieldType.getSimpleName();
		}
		String methodName = "get" + typeName;
		if ("getBoolean".equals(methodName)) {
			methodName = "getInt";
		} else if ("getChar".equals(methodName)
				|| "getCharacter".equals(methodName)) {
			methodName = "getString";
		} else if ("getDate".equals(methodName)) {
			methodName = "getLong";
		} else if ("getInteger".equals(methodName)) {
			methodName = "getInt";
		}
		return methodName;
	}

	private String makeSetterMethodName(Field field) {
		String setterMethodName;
		String setterMethodPrefix = "set";
		if (isPrimitiveBooleanType(field)
				&& field.getName().matches("^is[A-Z]{1}.*$")) {
			setterMethodName = setterMethodPrefix
					+ field.getName().substring(2);
		} else if (field.getName().matches("^[a-z]{1}[A-Z]{1}.*")) {
			setterMethodName = setterMethodPrefix + field.getName();
		} else {
			setterMethodName = setterMethodPrefix
					+ DBUtils.capitalize(field.getName());
		}
		return setterMethodName;
	}

	private boolean isPrimitiveBooleanType(Field field) {
		Class<?> fieldType = field.getType();
		if ("boolean".equals(fieldType.getName())) {
			return true;
		}
		return false;
	}

	/**
	 * put value to ContentValues for Database
	 *
	 * @param values
	 *            ContentValues object
	 * @param fd
	 *            the Field
	 * @param obj
	 *            the value
	 */
	private void putValues(ContentValues values, Field fd, Object obj) {
		Class<?> clazz = values.getClass();
		try {
			Object[] parameters = new Object[] { fd.getName(), fd.get(obj) };
			Class<?>[] parameterTypes = getParameterTypes(fd, fd.get(obj),
					parameters);
			Method method = clazz.getDeclaredMethod("put", parameterTypes);
			method.setAccessible(true);
			method.invoke(values, parameters);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到反射方法中的参数类型
	 *
	 * @param field
	 * @param fieldValue
	 * @param parameters
	 * @return
	 */
	private Class<?>[] getParameterTypes(Field field, Object fieldValue,
										 Object[] parameters) {
		Class<?>[] parameterTypes;
		if (isCharType(field)) {
			parameters[1] = String.valueOf(fieldValue);
			parameterTypes = new Class[] { String.class, String.class };
		} else {
			if (field.getType().isPrimitive()) {
				parameterTypes = new Class[] { String.class,
						getObjectType(field.getType()) };
			} else if ("java.util.Date".equals(field.getType().getName())) {
				parameterTypes = new Class[] { String.class, Long.class };
			} else {
				parameterTypes = new Class[] { String.class, field.getType() };
			}
		}
		return parameterTypes;
	}

	/**
	 * 是否是字符类型
	 *
	 * @param field
	 * @return
	 */
	private boolean isCharType(Field field) {
		String type = field.getType().getName();
		return type.equals("char") || type.endsWith("Character");
	}

	/**
	 * 得到对象的类型
	 *
	 * @param primitiveType
	 * @return
	 */
	private Class<?> getObjectType(Class<?> primitiveType) {
		if (primitiveType != null) {
			if (primitiveType.isPrimitive()) {
				String basicTypeName = primitiveType.getName();
				if ("int".equals(basicTypeName)) {
					return Integer.class;
				} else if ("short".equals(basicTypeName)) {
					return Short.class;
				} else if ("long".equals(basicTypeName)) {
					return Long.class;
				} else if ("float".equals(basicTypeName)) {
					return Float.class;
				} else if ("double".equals(basicTypeName)) {
					return Double.class;
				} else if ("boolean".equals(basicTypeName)) {
					return Boolean.class;
				} else if ("char".equals(basicTypeName)) {
					return Character.class;
				}
			}
		}
		return null;
	}

	/**
	 * 得到建表语句
	 *
	 * @param clazz
	 *            指定类
	 * @return sql语句
	 */
	public String getCreateTableSql(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();
		String tabName = DBUtils.getTableName(clazz);
		sb.append("create table ").append(tabName)
				.append(" (id  INTEGER PRIMARY KEY AUTOINCREMENT, ");
		Field[] fields = clazz.getDeclaredFields();
		for (Field fd : fields) {
			String fieldName = fd.getName();
			String fieldType = fd.getType().getName();
			if (fieldName.equalsIgnoreCase("_id")
					|| fieldName.equalsIgnoreCase("id")) {
				continue;
			} else {
				sb.append(fieldName).append(DBUtils.getColumnType(fieldType))
						.append(", ");
			}
		}
		int len = sb.length();
		sb.replace(len - 2, len, ")");
		Log.d(TAG, "the result is " + sb.toString());
		return sb.toString();
	}
    public void changeToList(Cursor cursor, List<rsda_info> modules, Class<?> moduleClass)
	{
		// 取出所有的列名
		int count = cursor.getCount();
        rsda_info module;
		cursor.moveToFirst();
		synchronized (dbOpenHelper)
		{
			try
			{
				// 遍历游标
				for (int i = 0; i < count; i++)
				{
					// 转化为moduleClass类的一个实例
					module = changeToModule(cursor, moduleClass);
					modules.add(module);
					cursor.moveToNext();
				}
			}
			catch (SecurityException e)
			{
				// Log.e(TAG, e + FusionCode.EMPTY_STRING);
			}
			catch (IllegalArgumentException e)
			{
				// Log.e(TAG, e + FusionCode.EMPTY_STRING);
			}
			catch (IllegalAccessException e)
			{
				// Log.e(TAG, e + FusionCode.EMPTY_STRING);
			}
			catch (InstantiationException e)
			{
				// Log.e(TAG, e + FusionCode.EMPTY_STRING);
			}
			catch (NoSuchFieldException e)
			{
				System.out.println("");
			}
			finally
			{
				cursor.close();
			}
		}
	}

	private rsda_info changeToModule(Cursor cursor, Class<?> moduleClass) throws IllegalAccessException,
			InstantiationException, SecurityException, NoSuchFieldException
	{
		synchronized (dbOpenHelper)
		{
			// 取出所有的列名
			String[] columnNames = cursor.getColumnNames();
			String filedValue;
			int columncount = columnNames.length;
			Field field;
            rsda_info module = (rsda_info) moduleClass.newInstance();
			// 遍历有列
			for (int j = 0; j < columncount; j++)
			{
				// 根据列名找到相对应 的字段
				field = moduleClass.getField(columnNames[j]);
				filedValue = cursor.getString(j);
				if (filedValue != null)
				{
					field.set(module, filedValue.trim());
				}
			}
			return module;
		}
	}
}
