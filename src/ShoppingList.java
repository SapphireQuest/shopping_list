package src;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList 
{
    private List<Product> items;
    
    public ShoppingList()
    {
        this.items = new ArrayList<>();
    }

    public List<Product> getItems()
    {
        return List.copyOf(items);
    }

    public void addProduct(Product product)
    {
        this.items.add(product);
        System.out.println("Added "+ product.getName() +" to shopping list.");
    }

    public void removeProduct(Product product)
    {
        if(this.items.remove(product))
        {
            System.out.println("Removed "+ product.getName() +" from shopping list.");
        }
        else
        {
            System.out.println("There is no such product in your shopping list.");
        }
    }

    public void display()
    {
        if(this.items.isEmpty())
        {
            System.out.println("Your shopping list is empty!");
            return;
        }

        System.out.println("\n===SHOPPING LIST===");

        for(Product product : items)
        {
            String categoryName = product.getCategory().getName();
            String productName = product.getName();

            System.out.println("[" + categoryName + "] " + productName);
        }
        System.out.println("===================\n");
    }   
}
