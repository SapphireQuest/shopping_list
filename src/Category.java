package src;
import java.util.ArrayList;
import java.util.List;

public class Category 
{
    private String name;
    private List<Product> products;

    public Category(String name)
    {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public List<Product> getProducts()
    {
        return List.copyOf(products);
    }

    public void addProduct(Product product)
    {
        this.products.add(product);
    }
}
