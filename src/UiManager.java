package src;
import java.util.Scanner;
import java.util.List;

public class UiManager
{
    private Scanner scanner;
    private Catalog catalog;
    private ShoppingList shoppingList;
    private FileManager fileManager;


    public UiManager(Catalog catalog, ShoppingList shoppingList, FileManager fileManager) 
    {
        this.scanner = new Scanner(System.in);
        this.catalog = catalog;
        this.shoppingList = shoppingList;
        this.fileManager = fileManager;
    }

    public void start() 
    {
        boolean running = true;

        while (running) 
        {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add product to shopping list");
            System.out.println("2. Remove product from shopping list");
            System.out.println("3. Display shopping list");
            System.out.println("4. Save shopping list to file");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) 
            {
                case "1":
                    handleAddProduct();
                    break;
                case "2":
                    handleRemoveProduct();
                    break;
                case "3":
                    shoppingList.display();
                    break;
                case "4":
                    System.out.print("Enter file name to save: ");
                    String saveFileName = scanner.nextLine();
                    fileManager.saveShoppingList(shoppingList, saveFileName);
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }


    private void handleRemoveProduct() 
    {
        if (shoppingList.getItems().isEmpty()) 
        {
            System.out.println("Your shopping list is empty. Nothing to remove.");
            return;
        }

        System.out.println("\nSelect a product to remove:");
        List<Product> currentItems = shoppingList.getItems();
        
        for (int i = 0; i < currentItems.size(); i++) 
        {
            Product product = currentItems.get(i);
            System.out.println((i + 1) + ". [" + product.getCategory().getName() + "] " + product.getName());
        }
        System.out.println("0. Cancel");
        System.out.print("Your choice: ");

        int index = readIntFromUser();
        
        if (index > 0 && index <= currentItems.size()) 
        {
            Product productToRemove = currentItems.get(index - 1);
            shoppingList.removeProduct(productToRemove);
        } 
        else if (index != 0) 
        {
            System.out.println("Invalid choice.");
        }
    }
    

    private void handleAddProduct()
    {
        List<Category> categories = catalog.getCategories();
        if (categories.isEmpty()) 
        {
            System.out.println("Catalog is empty!");
            return;
        }

        Category selectedCategory = selectCategory(categories);

        if (selectedCategory == null)
        {
            return;
        }

        List<Product> products = selectedCategory.getProducts();
        Product selectedProduct = selectProduct(products);

        if (selectedProduct == null)
        {
            return;
        }

        shoppingList.addProduct(selectedProduct);
    }

    
    private Category selectCategory(List<Category> categories) 
    {
        System.out.println("\n--- Select Category ---");
        for (int i = 0; i < categories.size(); i++) 
        {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.println("0. Cancel");
        System.out.print("Your choice: ");

        int index = readIntFromUser();
        
        if (index > 0 && index <= categories.size()) 
        {
            return categories.get(index - 1);
        }
        return null;
    }

    private Product selectProduct(List<Product> products) 
    {
        if (products.isEmpty()) 
        {
            System.out.println("No products in this category.");
            return null;
        }

        System.out.println("\n--- Select Product ---");
        for (int i = 0; i < products.size(); i++) 
        {
            System.out.println((i + 1) + ". " + products.get(i).getName());
        }
        System.out.println("0. Cancel");
        System.out.print("Your choice: ");

        int index = readIntFromUser();

        if (index > 0 && index <= products.size()) 
        {
            return products.get(index - 1);
        }
        return null;
    }

    private int readIntFromUser() 
    {
        try 
        {
            return Integer.parseInt(scanner.nextLine());
        } 
        catch (NumberFormatException e) 
        {
            return -1;
        }
    }



}
