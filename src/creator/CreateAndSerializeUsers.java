package creator;

import Models.User;

public class CreateAndSerializeUsers {

    public static void main(String[] args) {
        // Assuming necessary data like usernames, passwords, etc., are generated or
        // available
        for (int i = 1; i <= 25; i++) {
            String username = "user" + i;
            String password = "password";
            String name = "Name" + i;
            String surname = "Surname" + i;
            String uid = String.format("%08d", i); // Generate a UID with leading zeros
            String email = "user" + i + "@example.com";
            String mobile = "123456789" + i; // Example mobile number

            User newUser = new User(username, password, name, surname, uid, email, mobile);
            User.addToList(newUser);
        }
        User user = new User("medialab", "medialab_2024", "Giorgos", "Pittakis",
                "007", "gp@gmail.com", "6944801436");
        User.addToList(user);
        // Serialize the users
        User.serialize();

        System.out.println("25 users have been created and serialized successfully.");
    }
}
