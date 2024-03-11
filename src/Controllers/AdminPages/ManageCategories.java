package Controllers.AdminPages;

import Models.Category;
import Utils.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ManageCategories {

    public void initialize() {
        CategoryList.setItems(FXCollections.observableArrayList(Category.getAllCategoriesString()));
    }

    private Category selectedCategory;

    @FXML
    private ListView<String> CategoryList;

    @FXML
    private TextField categoryText;

    @FXML
    private Label messageLabel;

    @FXML
    private AnchorPane manageCategories;

    @FXML
    private BorderPane manageCategoriesBorder;

    @FXML
    void btnAddCategoryClicked(ActionEvent event) {
        String categoryTitle = categoryText.getText();
        if (Category.setCategory(categoryTitle)) {
            Category cat = new Category(categoryTitle);
            Category.addToList(cat);
            messageLabel.setText("Category added!");
            initialize();
        } else {
            messageLabel.setText("");
        }
        categoryText.clear();
    }

    @FXML
    void btnDeleteCategoryClicked(ActionEvent event) {
        selectedCategory = Category.getCategoryByTitle(CategoryList.getSelectionModel().getSelectedItem());
        boolean check = Category.deleteCategory(selectedCategory);
        if (selectedCategory != null && CategoryList.getSelectionModel().getSelectedItem() != null) {
            initialize();
        }
        if (selectedCategory == null) {
            messageLabel.setText("");
            initialize();
        }
        if (check) {
            messageLabel.setText("Category deleted!");
        }
    }

    @FXML
    void btnEditCategoryClicked(ActionEvent event) {
        selectedCategory = Category.getCategoryByTitle(CategoryList.getSelectionModel().getSelectedItem());
        String newCategoryTitle = categoryText.getText();
        if (selectedCategory == null) {
            messageLabel.setText("");
            categoryText.clear();
            Utils.showErrorPopUp("Select a category.");
        } else {
            if (Category.setCategory(newCategoryTitle)) {
                selectedCategory.updateCategory(newCategoryTitle, selectedCategory.getCategoryTitle());
                initialize();
                categoryText.clear();
                messageLabel.setText("Category edited!");
            } else {
                categoryText.clear();
                messageLabel.setText("");
            }
        }
    }

    @FXML
    void CategorySelected(MouseEvent event) {
        selectedCategory = Category.getCategoryByTitle(CategoryList.getSelectionModel().getSelectedItem());
    }

}
