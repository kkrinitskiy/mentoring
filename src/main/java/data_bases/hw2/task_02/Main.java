package data_bases.hw2.task_02;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Product> products = getProducts();

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.addProductList(products);
        HistoryDAO historyDAO = new HistoryDAO();
        GroceryStore groceryStore = new GroceryStore(0, historyDAO, productDAO);

        groceryStore.sell(products.get(0), 10);

        groceryStore.sell(new Product(1L, "Кошачий корм", 50.0), 1);


    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("Молоко").price(80.00).build());
        products.add(Product.builder().name("Хлеб").price(40.00).build());
        products.add(Product.builder().name("Сыр").price(300.00).build());
        products.add(Product.builder().name("Масло сливочное").price(150.00).build());
        products.add(Product.builder().name("Яйца").price(90.00).build());
        products.add(Product.builder().name("Яблоки").price(120.00).build());
        products.add(Product.builder().name("Апельсины").price(130.00).build());
        products.add(Product.builder().name("Бананы").price(85.00).build());
        products.add(Product.builder().name("Помидоры").price(150.00).build());
        products.add(Product.builder().name("Картофель").price(50.00).build());
        products.add(Product.builder().name("Лук").price(40.00).build());
        products.add(Product.builder().name("Морковь").price(45.00).build());
        products.add(Product.builder().name("Курица").price(250.00).build());
        products.add(Product.builder().name("Говядина").price(600.00).build());
        products.add(Product.builder().name("Макароны").price(70.00).build());
        products.add(Product.builder().name("Рис").price(100.00).build());
        products.add(Product.builder().name("Йогурт").price(60.00).build());
        products.add(Product.builder().name("Сок").price(90.00).build());
        products.add(Product.builder().name("Кофе").price(300.00).build());
        products.add(Product.builder().name("Чай").price(150.00).build());
        return products;
    }
}
