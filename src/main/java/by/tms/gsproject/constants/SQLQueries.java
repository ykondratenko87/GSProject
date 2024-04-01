package by.tms.gsproject.constants;

public class SQLQueries {
    public static final String ADD_USER = "insert into gsproject.users (id, \"name\",surname, login, password, role) " + "values (?,?,?,?,?,?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM gsproject.users WHERE id = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM gsproject.users";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM gsproject.users WHERE id = ?";
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM gsproject.users WHERE login = ?";
    public static final String SELECT_MAX_ID = "SELECT max(id) FROM gsproject.users";
    public static final String ADD_PRODUCT = "insert into gsproject.products (id, \"name\",type, price, quantity) " + "values (?,?,?,?,?)";
    public static final String MAX_ID = "select max(id) from gsproject.products";
    public static final String SELECT_PRODUCT_BY_NAME_AND_TYPE_AND_PRICE = "SELECT id, quantity FROM gsproject.products WHERE \"name\" = ? AND type = ? AND price = ?";
    public static final String UPDATE_QUANTITY = "UPDATE gsproject.products SET quantity = ? WHERE id = ?";
    public static final String DELETE_PRODUCT_BY_ID = "DELETE FROM gsproject.products WHERE id = ?";
    public static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM gsproject.products WHERE id = ?";
    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM gsproject.products ORDER BY id";
    public static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM gsproject.products WHERE name = ?";
    public static final String SELECT_PRODUCTS_BY_IDS = "SELECT * FROM gsproject.products WHERE id = ANY(?)";
    public static final String SELECT_PRODUCT_QUANTITY_BY_ID = "SELECT quantity FROM gsproject.products WHERE id = ?";
    public static final String SELECT_MAX_BASKET_ID = "select max(id) from gsproject.baskets";
    public static final String INSERT_INTO_BASKETS = "insert into gsproject.baskets(id, orderid ,productid, count)  " + "values (?,?,?,?)";
    public static final String SELECT_PRODUCT_QUANTITY = "SELECT quantity from gsproject.products WHERE id = ?";
    public static final String UPDATE_ORDER_STATUS = "UPDATE gsproject.orders SET status = ? WHERE userid = ?";
    public static final String SELECT_PRODUCTS_AND_QUANTITIES = "SELECT productid, count FROM gsproject.baskets WHERE orderid IN (SELECT id FROM gsproject.orders WHERE userid = ?)";
    public static final String UPDATE_PRODUCT_QUANTITIES = "UPDATE gsproject.products SET quantity = quantity - ? WHERE id = ?";
    public static final String SELECT_BASKETS_BY_ORDER_ID = "SELECT * from gsproject.baskets WHERE orderid = ?";
    public static final String SELECT_ORDER_STATUS = "select status from gsproject.orders WHERE id = ?";
    public static final String SELECT_PRODUCT_QUANTITIES = "SELECT quantity from gsproject.products WHERE id = ANY(?)";
    public static final String DELETE_FROM_BASKETS = "DELETE FROM gsproject.baskets where orderid = ?";
    public static final String DELETE_FROM_ORDERS = "DELETE FROM gsproject.orders WHERE id = ?";
    public static final String DELETE_ALL_FROM_BASKETS = "DELETE FROM gsproject.baskets";
    public static final String DELETE_ALL_FROM_ORDERS = "DELETE FROM gsproject.orders WHERE status = 'ORDERING'";
    public static final String SELECT_MAX_ID_QUERY = "SELECT max(id) FROM gsproject.orders";
    public static final String INSERT_ORDER_QUERY = "insert into gsproject.orders(id, userId, cost, status)  " + "values (?,?,?,?)";
    public static final String SELECT_ORDER_BY_USER_ID_QUERY = "SELECT * FROM gsproject.orders WHERE userid = ? AND status = 'ORDERING'";
    public static final String SELECT_COST_BY_ORDER_ID_QUERY = "SELECT cost FROM gsproject.orders WHERE id = ?";
    public static final String UPDATE_ORDER_COST_QUERY = "UPDATE gsproject.orders SET cost = ? WHERE id = ?";
}