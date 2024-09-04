package data_bases.hw2.task_02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO {
    private final SessionFactory sessionFactory = UtilSessionFactory.getSessionFactory();

    public List<Product> getAllProducts() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<Product> fromProduct = session.createQuery("from Product", Product.class).list();
            transaction.commit();
            return fromProduct;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public boolean addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
            return true;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean addProductList(List<Product> products) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(multiInsertSQL(products), Product.class).executeUpdate();
            transaction.commit();
            return true;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
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
