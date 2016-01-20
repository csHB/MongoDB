package MongoUtil;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoUtil {

	// 查找
	public static void search(DBCollection collection) {

		DBCursor cursor = collection.find();

		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

	}

	// 根据条件查找， 可删除上面的search方法
	public static void searchByObject(DBCollection collection, BasicDBObject basicDBObject) {

		DBCursor cursor = collection.find(basicDBObject);

		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

	}

	// 插入
	public static void insert(DBCollection collection, BasicDBObject basicDBObject) {

		collection.insert(basicDBObject);

	}

	// 插入一条链表， 链表中都为DBObject对象
	public static void insertList(DBCollection collection, List<DBObject> list) {

		collection.insert(list);

	}

	// 更新：originObject：要更新的对象； updateObject：更新内容
	public static void update(DBCollection collection, BasicDBObject originObject, BasicDBObject updateObject) {

		/*
		 * collection.update(new BasicDBObject("name", "wode"), new
		 * BasicDBObject("$set", new BasicDBObject("age", 33).append("school",
		 * "aaa")), false, true);
		 */

		collection.update(originObject, new BasicDBObject("$set", updateObject), false, true);

	}

	// 删除
	public static void delete(DBCollection collection, BasicDBObject basicDBObject) {

		collection.remove(basicDBObject);

	}

	public static void main(String args[]) {

		char[] password = new char[10];

		try {

			Mongo mon = new Mongo("localhost", 27017);

			DB db = mon.getDB("db");

			System.out.println("Connect to database successfully");

			boolean auth = db.authenticate("", password);

			System.out.println("Authenticate:" + auth);

			DBCollection collection = db.getCollection("test");

			BasicDBObject basicDBObject1 = new BasicDBObject("name", "wode");

			BasicDBObject basicDBObject2 = new BasicDBObject("age", 13).append("school", "bbb");

			BasicDBObject update = new BasicDBObject("$set", basicDBObject2);

			BasicDBObject basicDBObject3 = new BasicDBObject("name", "b3").append("age", 15).append("school", "ssss");

			BasicDBObject basicDBObject4 = new BasicDBObject("name", "b4").append("age", 23).append("school", "444");

			BasicDBObject basicDBObject5 = new BasicDBObject("name", "b5").append("age", 26).append("school", "555");

			List<DBObject> list = new ArrayList<DBObject>();

			list.add(basicDBObject3);

			list.add(basicDBObject4);

			list.add(basicDBObject5);

			// insertList(collection, list);

			// search
			// search(collection);

			BasicDBObject basicDBObject6 = new BasicDBObject("age", 23);

			System.out.println("-------------------");

			// searchByObject(collection, basicDBObject6);

			System.out.println("-------------------");
			// insert
			// insert(collection);

			// update
			// update(collection, basicDBObject1, basicDBObject2);

			// delete
			// delete(collection, basicDBObject6);

			search(collection);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
