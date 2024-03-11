import java.io.IOException;

import Models.Book;
import Models.Category;
import Models.CommentAndRating;
import Models.Rent;
import Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Views/StartScene.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("icons/ScreenIcons/user.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("eLibrary");
            primaryStage.setScene(scene);
            primaryStage.show();

            root.requestFocus();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {

        // Deserialize data
        User.deserialize();
        Category.deserialize();
        Book.deserialize();
        Rent.deserialize();
        CommentAndRating.deserialize();

        // Launch the program
        launch(args);

        // Serialize data
        User.serialize();
        Category.serialize();
        Book.serialize();
        Rent.serialize();
        CommentAndRating.serialize();

        System.out.println("Users\t\t" + User.getAllUsers().size());
        System.out.println("Categories\t" + Category.getAllCategories().size());
        System.out.println("Books\t\t" + Book.getAllBooks().size());
        System.out.println("Rents\t\t" + Rent.getAllRents().size());
        System.out.println("Comments\t" +
                CommentAndRating.getAllCommentAndRating().size());

    }
}