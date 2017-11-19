package org.demo.control;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.demo.mapping.Person;
import org.demo.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@EnableWebMvc
@RestController
public class RestServcController {

	@RequestMapping(value="/getUser/{username}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON)
	public @ResponseBody Map<Integer, Map<String, Object>> getUserNames(@PathVariable("username") String username, HttpServletResponse response) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException {
		SessionFactory sessionFactory = null;
		Session session = null;
		JSONObject json = new JSONObject();
		response.setHeader("Content-Disposition", "inline; filename=templates.json");
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();

			System.out.println("session>>"+session);
			String[] strCols = sessionFactory.getClassMetadata(Person.class).getPropertyNames();
			session.beginTransaction();

			Query qry = session.createQuery("FROM Person where lastname = :username");
			qry.setString("username", "vamsi");

			List<Person> lst = qry.list();
			Iterator<Person> itr = lst.iterator();
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
			json.put("Details", mapObj);
			System.out.println("json map >>" + json);
			return mapObj;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return null;
	}

	@RequestMapping(value="/sayHello/{username}", method=RequestMethod.GET, produces=MediaType.TEXT_PLAIN)
	public String sayHello(@PathVariable("username") String username) {
		return "Hello, "+username+" Welcome to RESTFul services";
	}
}