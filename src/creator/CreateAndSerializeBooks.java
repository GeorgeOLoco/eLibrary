package creator;

import java.util.List;

import Models.Book;
import Models.Category;

public class CreateAndSerializeBooks {

    public static void main(String[] args) {
        // Assuming categories have already been created and serialized
        Category.deserialize(); // Load categories from serialized data if needed

        String[] authors = { "Author1", "Author2", "Author3", "Author4", "Author5" };
        String[] publishers = { "Publisher1", "Publisher2", "Publisher3", "Publisher4", "Publisher5" };
        int[] publishYears = { 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022 };
        int[] numOfCopies = { 10, 15, 20, 25, 30 };

        List<Category> categories = Category.getAllCategories();
        int categoryIndex = 0;

        for (int i = 1; i <= 60; i++) {
            String isbn = "ISBN" + i;
            String title = "Book Title " + i;
            String author = authors[i % authors.length];
            String publisher = publishers[i % publishers.length];
            int publishYear = publishYears[i % publishYears.length];
            String category = categories.get(categoryIndex).getCategoryTitle();
            int copies = numOfCopies[i % numOfCopies.length];

            Book newBook = new Book(isbn, title, author, publisher, publishYear, category, copies);
            Book.addToList(newBook);

            categoryIndex = (categoryIndex + 1) % categories.size(); // Cycle through categories
        }

        // Serialize the books
        Book.serialize();

        System.out.println("60 books have been created and serialized successfully.");
    }
}
