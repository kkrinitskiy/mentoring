package data_bases.hw2.task_02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO {
    private final SessionFactory sessionFactory = UtilSessionFactory.getSessionFactory();

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product", Product.class).list();
        }
    }

    public boolean addProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addProductList(List<Product> products) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(multiInsertSQL(products), Product.class).executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private String multiInsertSQL(List<Product> products) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into products(name, price) values");
        String value = "('%s', %s),";
        for (Product product : products) {
            stringBuilder.append(String.format(value, product.getName(), product.getPrice().toString().replace(',', '.')));
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(";");
        return stringBuilder.toString();
    }


}
