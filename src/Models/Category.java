package Models;

import java.util.ArrayList;
import java.util.List;

import Controllers.PopUpScreens.AreYouSureController;
import Utils.SerializationUtils;
import Utils.Utils;

public class Category implements java.io.Serializable {

    private String categoryTitle;
    private static List<Category> allCategories = new ArrayList<>();

    private static final long serialVersionUID = 5643928179785397945L;

    // Constructor
    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle.substring(0, 1).toUpperCase() + categoryTitle.substring(1).toLowerCase();
    }

    // update the category
    public void updateCategory(String newCategoryTitle, String oldCategoryTitle) {
        if (newCategoryTitle != null && !newCategoryTitle.trim().isEmpty()) {
            this.categoryTitle = newCategoryTitle.substring(0, 1).toUpperCase()
                    + newCategoryTitle.substring(1).toLowerCase();

            List<Book> catBooks = Book.getAllBooks();
            for (Book book : catBooks) {
                if (book.getCategory().equals(oldCategoryTitle))
                    book.editBook(book.getISBN(), book.getTitle(), book.getAuthor(), book.getPublisher(),
                            book.getPublishYear(), this.categoryTitle, book.getNumOfCopies(),
                            book.getNumOfAvailableCopies());
            }
        }
    }

    // Delete category
    public static boolean deleteCategory(Category b) {
        boolean result = false;
        if (b != null) {
            Utils.areYouSurePopUp();
            if (AreYouSureController.getAreYouSure()) {
                // ena for loop na diagrafei ta vivlia tis katigorias
                String categoryTitle = b.getCategoryTitle();
                List<Book> catBooks = Book.getAllBooksByCategory(categoryTitle);
                for (Book book : catBooks) {
                    // delete Comments and ratings for every book in that category
                    CommentAndRating.deleteCommentAndRatingOfABook(book.getISBN());
                    Rent.deleteRentsOfABook(book.getISBN());
                    Book.removeFromList(book);
                }

                removeFromList(b);
                result = AreYouSureController.getAreYouSure();
                AreYouSureController.setAreYouSure();
            }
        } else {
            Utils.showErrorPopUp("Select a category.");
            result = false;
        }
        return result;
    }

    // setter
    public static boolean setCategory(String categoryTitle) {

        // check if null
        if (categoryTitle == null || categoryTitle.trim().isEmpty()) {
            Utils.showErrorPopUp("Category title cannot be null or empty");
            return false;
        }

        // check if exists
        else if (findCategory(categoryTitle.trim())) {
            Utils.showErrorPopUp("Category title already exists");
            return false;
        }

        else {
            return true;
        }
    }

    // get a category by category title
    public static Category getCategoryByTitle(String categoryTitle) {
        if (categoryTitle == null) {
            return null;
        }
        for (Category cat : allCategories) {
            if (categoryTitle.equals(cat.categoryTitle)) {
                return cat;
            }
        }
        return null;
    }

    // finder
    public static boolean findCategory(String categoryTitle) {
        // convert to lowercase
        String categoryTitleLC = categoryTitle.toLowerCase();
        for (Category s : allCategories) {
            if (categoryTitleLC.equals(s.categoryTitle.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // add to the list
    public static void addToList(Category obj) {
        Category.allCategories.add(obj);
    }

    // remove from list
    public static void removeFromList(Category cat) {
        Category.allCategories.remove(cat);
    }

    // get all Categories
    public static List<Category> getAllCategories() {
        return Category.allCategories;
    }

    // get all Categories String
    public static List<String> getAllCategoriesString() {
        List<String> list = new ArrayList<>();
        for (Category c : allCategories) {
            list.add(c.categoryTitle);
        }
        return list;
    }

    // get category title
    public String getCategoryTitle() {
        return this.categoryTitle;
    }

    // Serialize
    public static void serialize() {
        SerializationUtils.serializeAll(Category.allCategories, Category.class);
    }

    // Deserialize
    public static void deserialize() {
        Category.allCategories = SerializationUtils.deserializeAll(Category.class);
    }

}