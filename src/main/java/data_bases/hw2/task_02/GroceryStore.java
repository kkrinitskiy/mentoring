package data_bases.hw2.task_02;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GroceryStore {
    private double money;
    private HistoryDAO historyDAO;
    private ProductDAO productDAO;

    public GroceryStore(double money, HistoryDAO historyDAO, ProductDAO productDAO) {
        this.money = money;
        this.historyDAO = historyDAO;
        this.productDAO = productDAO;
    }

    public void sell(Product product, int quantity) {
        try {
            if (productDAO.getAllProducts().contains(product)) {
                money = money + product.getPrice() * quantity;
                System.out.println("history has been saved: " +
                historyDAO.save(History.builder()
                        .count(quantity)
                        .name(product.getName())
                        .price(product.getPrice())
                        .totalPrice(product.getPrice() * quantity)
                        .build())
                );
            }else {
                throw new NoSuchElementException("Нет такого продукта");
            }
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
