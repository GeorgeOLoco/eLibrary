package creator;

import Models.Category;

public class CreateAndSerializeCategories {

    public static void main(String[] args) {
        // Define an array of category titles
        String[] categoryTitles = {
                "Fiction", "Non-Fiction", "Science Fiction", "Fantasy",
                "Mystery", "Thriller", "Romance", "Historical",
                "Biography", "Self-Help", "Health", "Travel",
                "Cooking", "Art", "Science"
        };

        // Create and add categories to the list
        for (String title : categoryTitles) {
            Category newCategory = new Category(title);
            Category.addToList(newCategory);
        }

        // Serialize the categories
        Category.serialize();

        System.out.println("15 book categories have been created and serialized successfully.");
    }
}
