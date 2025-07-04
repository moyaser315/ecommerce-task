import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        Product [] productsTest = testProducts(args);

        for (Product product : productsTest) {
            if (product != null) {
                System.out.println(product);
            }
        }

    }
    public static Product [] testProducts(String[] args) throws Exception  {
        Product [] productsTest = new Product [8];
        productsTest[0] = ProductFactory.createProduct("cheese", 280 ,5 ,1000.0, LocalDate.now().plusYears(2));
        productsTest[1] = ProductFactory.createProduct("TV", 280 ,5 ,1000.0, null);
        productsTest[2] = ProductFactory.createProduct("Mobile", 280 ,5 ,1000.0, null);
        productsTest[3] = ProductFactory.createProduct("Scratch card", 280 ,5 ,null, LocalDate.now().plusYears(2));
        // productsTest[4] = ProductFactory.createProduct("", 280 ,5 ,null, LocalDate.now().plusYears(2));
        // productsTest[5] = ProductFactory.createProduct("Scratch card", 0 ,5 ,null, LocalDate.now().plusYears(2));
        // productsTest[6] = ProductFactory.createProduct("Scratch card", 280 ,-1 ,null, LocalDate.now().plusYears(2));
        productsTest[7] = ProductFactory.createProduct("Scratch card", 280 ,100 ,null, null);

        productsTest[1].setShippingDate(LocalDate.now().plusDays(5));
        return productsTest;
    }
}
