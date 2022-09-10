package products;

public class Product {

    private long id;
    private String nameProduct;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Product(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
