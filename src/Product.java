package src;
public class Product 
{
    private String name;
    private Category category;
 
    public Product(String name, Category category)
    {
        this.name = name;
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public Category getCategory()
    {
        return category;
    }
}
