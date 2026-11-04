package src;
public class App 
{
    public static void main(String[] args) 
    {
        FileManager fileManager = new FileManager();

        Catalog catalog = fileManager.loadFromCSV("catalog.csv");

        ShoppingList shoppingList = new ShoppingList();

        UiManager ui = new UiManager(catalog, shoppingList, fileManager);
        ui.start_app();
    }
    
}
