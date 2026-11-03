import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager 
{
    public Catalog loadFromCSV(String fileName)
    {
        Path path = Paths.get(fileName);
        Catalog catalog = new Catalog();

        try 
        {
            List<String> lines = Files.readAllLines(path);

            if (lines.isEmpty())
            {
                System.out.println("Error: The file is empty!");
                return catalog;
            }

            processAllLines(lines, catalog);
            System.out.println("Successfully loaded file content!\n");
        } 
        catch (IOException e) 
        {
            System.out.println("Error: Program couldn't read file " + fileName);
            System.out.println();
            System.out.println(e.getMessage());
        }

        return catalog;
    }

    private void processAllLines(List<String> lines, Catalog catalog)
    {
        for (String line : lines)
        {
            if (line.trim().isEmpty())
            {
                continue;
            }

            processSingleLine(line, catalog);
        }
    }

    private void processSingleLine(String line, Catalog catalog)
    {
        String[] columns = line.split(";");
        if (columns.length == 2)
        {
            String categoryName = columns[0].trim();
            String productName = columns[1].trim();
            Category category = catalog.findOrCreateCategory(categoryName);
            Product product = new Product(productName, category);
            category.addProduct(product);
        }
        else
        {
            System.out.println("Line with error exist in the file!");
        }
    }


    public void saveShoppingList(ShoppingList shoppingList, String fileName)
    {
        List<String> linesToSave = new ArrayList<>();

        for(Product product : shoppingList.getItems())
        {
            String categoryName = product.getCategory().getName();
            String productName = product.getName();
            linesToSave.add(categoryName + ";" + productName);
        }

        Path path = Paths.get(fileName);

        try
        {
            Files.write(path, linesToSave);
            System.out.println("Succesfully saved shopping list to: " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("Error: Counldn't save the file!");
            System.out.println(e.getMessage());
        }
    }
}
