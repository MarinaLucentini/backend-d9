package marinalucentini;

import com.github.javafaker.Faker;
import marinalucentini.orders.Cathegory;
import marinalucentini.orders.Customer;
import marinalucentini.orders.Order;
import marinalucentini.orders.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        List<Product> productList =
                generateProduct();
        System.out.println(productList);
        List<Customer> customerList = generateCostumer();
        System.out.println(customerList);

        List<Order> orderList = generateOrders(productList, customerList);
        System.out.println(orderList);
        Map<Customer, List<Order>> ordersByCustomers = orderList.stream().collect(Collectors.groupingBy(customer -> customer.getCustomer()));
        System.out.println(ordersByCustomers);
        
    }

    public static Cathegory randomcathegory() {
        Random rdm = new Random();
        int num = rdm.nextInt(1, 4);
        Cathegory defaultCathegory = Cathegory.BABY;
        switch (num) {
            case 1: {
                defaultCathegory = Cathegory.BABY;
                break;
            }
            case 2: {
                defaultCathegory = Cathegory.BOOKS;
                break;
            }
            case 3: {
                defaultCathegory = Cathegory.BOYS;
                break;
            }

        }
        return defaultCathegory;
    }

    public static List<Product> generateProduct() {
        List<Product> prodotti = new ArrayList<Product>();
        Faker faker = new Faker(Locale.ITALY);

        Random rdmn = new Random();
        Supplier<Long> randomId = () -> rdmn.nextLong(10000, 60000);
        Supplier<Double> randomprice = () -> rdmn.nextDouble(0, 200);

        Supplier<Product> randomProduct = () -> new Product(faker.commerce().productName(), randomcathegory().name(), randomprice.get(), randomId.get());
        for (int i = 0; i < 200; i++) {
            Product generateProduct = randomProduct.get();
            prodotti.add(generateProduct);

        }
        return prodotti;
    }

    public static List<Customer> generateCostumer() {
        List<Customer> customers = new ArrayList<>();
        Random rdmn = new Random();
        Faker faker = new Faker(Locale.ITALY);

        Supplier<Long> randomId = () -> rdmn.nextLong(10000, 60000);
        Supplier<Integer> randomtier = () -> rdmn.nextInt(1, 50);
        Supplier<Customer> randomCustomers = () -> new Customer(randomId.get(), faker.dragonBall().character(), randomtier.get());
        for (int i = 0; i < 50; i++) {
            Customer generateCustomer = randomCustomers.get();
            customers.add(generateCustomer);

        }
        return customers;
    }

    public static List<Order> generateOrders(List<Product> products, List<Customer> customers) {
        List<Order> orders = new ArrayList<>();
        Random rdmn = new Random();
        Supplier<Long> randomId = () -> rdmn.nextLong(10000, 60000);
        Supplier<String> randomStatus = () -> {
            String[] statuses = {"PENDING", "SHIPPED", "DELIVERED", "CANCELLED"};
            return statuses[rdmn.nextInt(statuses.length)];
        };
        Supplier<LocalDate> randomOrderDate = () -> LocalDate.now().minusDays(rdmn.nextInt(1, 30));
        Supplier<LocalDate> randomDeliveryDate = () -> randomOrderDate.get().plusDays(7);
        Supplier<Order> randomOrder = () -> {

            Customer customer = customers.get(rdmn.nextInt(customers.size()));

            int numberOfProducts = rdmn.nextInt(1, 10);
            List<Product> orderProducts = new ArrayList<>();
            for (int i = 0; i < numberOfProducts; i++) {
                orderProducts.add(products.get(rdmn.nextInt(products.size())));
            }


            return new Order(
                    randomId.get(),
                    randomStatus.get(),
                    randomOrderDate.get(),
                    randomDeliveryDate.get(),
                    orderProducts,
                    customer
            );
        };

        for (int i = 0; i < 100; i++) {
            Order order = randomOrder.get();
            orders.add(order);
        }

        return orders;
    }
}
