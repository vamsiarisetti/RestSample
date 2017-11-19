package org.demo.myDemoUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.demo.mapping.DataConnection;
import org.demo.mapping.InputData;
import org.demo.mapping.InputFields;
import org.demo.mapping.JobStatus;
import org.demo.mapping.OutputData;
import org.demo.mapping.Patterns;
import org.demo.mapping.Person;
import org.demo.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;

public class App {

	public static void main1(String[] args) {
		try {
			doSelectUserInfo();
		} catch (

		JSONException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void doSelectUserInfo() throws JSONException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		String[] strCols = sessionFactory.getClassMetadata(Person.class).getPropertyNames();
		session.beginTransaction();

		Query qry = session.createQuery("FROM Person where lastname = :username");
		qry.setString("username", "vamsi");

		List lst = qry.list();
		Iterator itr = lst.iterator();
		List<String> lstObj = null;
		Map<Integer, Map<String, Object>> mapObj = new HashMap<Integer, Map<String, Object>>();
		int index = 0;
		while (itr.hasNext()) {
			Person prsn = (Person) itr.next();

			Map<String, Object> mapPrsn = new HashMap<String, Object>();
			Map<String, Object> mapwithCols = new HashMap<String, Object>();
			for (String column : strCols) {
				String columnNm = column.substring(0, 1).toUpperCase() + column.substring(1);

				Method method = prsn.getClass().getMethod("get" + columnNm, null);
				Object mtdRtn = method.invoke(prsn, null);
				mapwithCols.put(columnNm, method.invoke(prsn, null));
			}

			mapObj.put(index, mapwithCols);
			++index;
		}
		JSONObject json = new JSONObject();
		json.put("Details", mapObj);
		System.out.println("json map >>" + json);
	}

	public void doInsert() {
		SessionFactory sessionFactory = null;
        Session session = null;
        try {
        	sessionFactory = HibernateUtil.getSessionFactory();
        	session = sessionFactory.openSession();
        	session.beginTransaction();
        	
        	InputData iData = new InputData();
        	iData.setInputDataDisplayName("Displat Test Input");
        	iData.setInputDataSql("Select * from ..");

        	DataConnection dataConnection = new DataConnection("http://localhost:8080", "localhost", "postgres", 3);
        	iData.setDataConnection(dataConnection);
        	iData.setDataOutput(new OutputData("Test Display Name", "test Table", dataConnection));

        	long inputDataId = (long) session.save(iData);

        	JobStatus jSts = new JobStatus("TASK", "CMPL", 10, "17/11/17", "19/11/17", "This is a test Job Message", iData);
        	InputFields iFlds = new InputFields(iData, "Test Data Field", "String", "Test Category");
        	Patterns patterns = new Patterns(iData, "Test Pattern", "17/11/17", "17/11/17", "CMPL");

        	long StatusId = (long) session.save(jSts);
        	long iFldId = (long) session.save(iFlds);
        	long patternId = (long) session.save(patterns);

        	session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			//new App().doInsert();
			String joinQry = "from InputData iData "
					+ "INNER JOIN iData.dataConnection dConn "
					+ "JOIN iData.outputData oData "
					+ "WHERE iData.inputDataId=:inputDataId";

			String sqlQry = "select * from ed_input_data iData "
					+ "INNER JOIN ed_data_connection dConn on iData.dataconnId=dConn.dataconnId"
					+ " INNER JOIN ed_output_data oData on oData.dataconnId=iData.dataconnId"
					+ " WHERE iData.inputDataId=:inputDataId";

			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();

			Query qry = session.createQuery(joinQry);
			qry.setInteger("inputDataId", 2);

			/*SQLQuery sqlQuery = session.createSQLQuery(sqlQry);
			sqlQuery.setInteger("inputDataId", 2);
			List<Object[]> list = sqlQuery.list();*/

			List<Object[]> list = qry.list();
			InputData ipData = null;
			DataConnection dataConnection = null;
			OutputData outputData = null;

			for (Object[] object : list) {
				System.out.println("Object>>"+object);
				ipData = (InputData) object[0];
				dataConnection = (DataConnection) object[1];
				outputData = (OutputData) object[2];
			}
			System.out.println("INPUTDATA : "+ipData+"\n OUTPUTDATA : "+outputData+" \n DATACONNECTION : "+dataConnection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}