package creator;

import Models.Book;
import Models.Rent;
import Models.User;

public class CreateAndSerializeRents {

    public static void main(String[] args) {
        // Assume Books and Users have been deserialized previously in the application
        // lifecycle
        Book.deserialize();
        User.deserialize();

        int numberOfRents = 15;
        for (int i = 1; i <= numberOfRents; i++) {
            // Cycle through the list of books and users to ensure variety
            Book book = Book.getAllBooks().get(i % Book.getAllBooks().size());
            User user = User.getAllUsers().get(i % User.getAllUsers().size());

            if (Rent.isRentGG(user, book)) {
                Rent rent = new Rent(book, user);
                Rent.addToList(rent);
                // Optionally, approve some rents directly for varied data
                if (i % 2 == 0) {
                    rent.approveRent();
                }
            }
        }

        // Serialize the rents after generation
        Rent.serialize();
        Book.serialize();
        User.serialize();
        System.out.println(numberOfRents + " rents have been created and serialized successfully.");
    }
}
