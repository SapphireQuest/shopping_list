import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Catalog 
{
    private List<Category> categories;

    public Catalog()
    {
        this.categories = new ArrayList<>();
    }

    public List<Category> getCategories()
    {
        return categories;
    }

    public void loadFromCSV(String fileName)
    {
        Path path = Paths.get(fileName);

        try 
        {
            List<String> lines = Files.readAllLines(path);

            if (lines.isEmpty())
            {
                System.out.println("Error: The file is empty!");
                return;
            }

            processAllLines(lines);
            System.out.println("Successfully loaded file content!\n");
        } 
        catch (IOException e) 
        {
            System.out.println("Error: Program couldn't read file " + fileName);
            System.out.println();
            System.out.println(e.getMessage());
        }
    }

    private void processAllLines(List<String> lines)
    {
        for (String line : lines)
        {
            if (line.trim().isEmpty())
            {
                continue;
            }

            processSingleLine(line);
        }
    }

    private void processSingleLine(String line)
    {
        String[] columns = line.split(";");
        if (columns.length == 2)
        {
            String categoryName = columns[0].trim();
            String productName = columns[1].trim();
            Category category = findOrCreateCategory(categoryName);
            Product product = new Product(productName, category);
            category.addProduct(product);
        }
        else
        {
            System.out.println("Line with error exist in the file!");
        }
    }

    private Category findOrCreateCategory(String categoryName)
    {
        for (Category category : categories)
        {
            if (category.getName().equals(categoryName))
            {
                return category;
            }
        }

        Category newCategory = new Category(categoryName);
        categories.add(newCategory);
        return newCategory;

    }
}
