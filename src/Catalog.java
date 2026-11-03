package src;
import java.util.ArrayList;
import java.util.List;


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


    public Category findOrCreateCategory(String categoryName)
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
