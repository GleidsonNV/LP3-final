package clinica;

import java.util.*;
import java.lang.reflect.Method;

public class BancoDeDados {

    private Map<String, List> tables;
    
    private static BancoDeDados instancia = new BancoDeDados();
	
	
	public static BancoDeDados getInstance() {
        return instancia;
    }

    public BancoDeDados() {
        tables = new HashMap<>();
    }

    public void createTable(Class<?> clazz) {
        List list = new ArrayList();
        tables.put(clazz.getName(), list);
    }

    public void insert(Object obj) {
        List list = tables.get(obj.getClass().getName());
        list.add(obj);
    }
    
    public List all(Class<?> clazz) {
        List list = tables.get(clazz.getName());
        return list;
    }
    
    public Object selectIndex(Class<?> clazz, int id) {
        List list = tables.get(clazz.getName());
        return list.get(id);
    }
    
    public void remove(Class<?> clazz, int id) {
        List list = tables.get(clazz.getName());
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            try {
                Method method = obj.getClass().getMethod("getId");
                Object result = method.invoke(obj);
                if (result != null && result.equals(id)) {
                    iterator.remove();
                    tables.put(clazz.getName(), list);
                }
            } catch (Exception e) {
                // Handle any exceptions as needed.
            }
        }

    }

    public Object select(Class<?> clazz, int id) {
        List list = tables.get(clazz.getName());
        for (Object obj : list) {
            try {
                Method method = obj.getClass().getMethod("getId");
                Object result = method.invoke(obj);
                if (result != null && result.equals(id)) {
                    return obj;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public Object select(Class<?> clazz, String name) {
        List<?> list = tables.get(clazz.getName());
        for (Object obj : list) {
            try {
                Method method = obj.getClass().getMethod("getNome");
                Object result = method.invoke(obj);
                if (result != null && result.equals(name)) {
                    return obj;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
}
