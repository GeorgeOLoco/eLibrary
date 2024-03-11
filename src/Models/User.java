package Models;

import java.util.List;

import Controllers.PopUpScreens.AreYouSureController;

import java.util.ArrayList;

import Utils.Utils;
import Utils.SerializationUtils;

public class User implements java.io.Serializable {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String uid;
    private String email;
    private String mobile;
    private Book[] rentedBooks;
    private static List<User> allUsers = new ArrayList<>();

    private static final long serialVersionUID = 2329090495112689009L;

    // Constructor
    public User(String username, String password, String name, String surname, String uid, String email,
            String mobile) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.uid = uid;
        this.email = email;
        this.mobile = mobile;
        this.rentedBooks = new Book[2];
    }

    // editUser returns false if passwords don't match
    public void editUser(String username, String name, String surname,
            String uid, String email,
            String mobile) {
        if (username != null && !username.trim().isEmpty())
            this.username = username;
        if (name != null && !name.trim().isEmpty())
            this.name = name;
        if (surname != null && !surname.trim().isEmpty())
            this.surname = surname;
        if (mobile != null && !mobile.trim().isEmpty())
            this.mobile = mobile;
        if (uid != null && !uid.trim().isEmpty())
            this.uid = uid;
        if (email != null && !email.trim().isEmpty())
            this.email = email;

        Utils.showSuccessfullPopUp();
    }

    // Delete user
    public static boolean deleteUser(User b) {
        boolean result = false;
        if (b != null) {
            Utils.areYouSurePopUp();
            if (AreYouSureController.getAreYouSure()) {
                // delete users rents
                if (b.rentedBooks[0] != null) {
                    Rent.deleteRent(Rent.getARentByISBN(b.rentedBooks[0].getISBN()));
                }
                if (b.rentedBooks[1] != null) {
                    Rent.deleteRent(Rent.getARentByISBN(b.rentedBooks[1].getISBN()));
                }
                removeFromList(b);
                result = AreYouSureController.getAreYouSure();
                AreYouSureController.setAreYouSure();
            }
        } else {
            Utils.showErrorPopUp("Select a user.");
            result = false;
        }
        return result;
    }

    // Check if user's credentials are gg
    public static boolean isUserGG(User currentUser, String username, String password, String confirmPassword,
            String name,
            String surname,
            String uid, String email,
            String mobile, char w) {

        for (User user : allUsers) {
            // For updates, skip the current user to avoid conflict with their own existing
            // data
            if (currentUser != null && user.equals(currentUser)) {
                continue; // Skip the current user to avoid self-conflict check
            }
            if (username.equals(user.getUsername())) {
                Utils.showErrorPopUp("Username already exists.");
                return false;
            }
            if (email.equals(user.getEmail())) {
                Utils.showErrorPopUp("e-mail already exists.");
                return false;
            }
            if (uid.equals(user.getID())) { // Assume there's a getUid method or use user.uid directly if accessible
                Utils.showErrorPopUp("ID already exists.");
                return false;
            }
        }
        if (w == 'n') {
            boolean checked = (username == null || username.trim().isEmpty() ||
                    password == null || password.trim().isEmpty() ||
                    name == null || name.trim().isEmpty() ||
                    surname == null || surname.trim().isEmpty() ||
                    uid == null || uid.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    mobile == null || mobile.trim().isEmpty());

            if (checked) {
                Utils.showErrorPopUp("No field can be empty.");
                return false;
            } else {
                // check if mobile and uid are numbers
                if (!(mobile != null && mobile.matches("\\d+"))) {
                    Utils.showErrorPopUp("Phone Number must include only numbers.");
                    return false;
                }

                if (!(uid != null && uid.matches("\\d+"))) {
                    Utils.showErrorPopUp("ID must include only numbers.");
                    return false;
                }
                if (!password.equals(confirmPassword)) {
                    Utils.showErrorPopUp("Passwords don't match.");
                    return false;
                }
                // Utils.showSuccessfullPopUp();
                return true;
            }
        } else if (w == 'u') {
            // Check if mobile and uid are numbers for 'u' case
            if (!(mobile.matches("\\d+"))) {
                Utils.showErrorPopUp("Phone Number must include only numbers.");
                return false;
            }

            if (!(uid.matches("\\d+"))) {
                Utils.showErrorPopUp("ID must include only numbers.");
                return false;
            }

            return true;
        }

        Utils.showErrorPopUp("Something went wrong.");
        return false;
    }

    // add to list
    public static void addToList(User user) {
        User.allUsers.add(user);
    }

    // remove from list
    public static void removeFromList(User user) {
        User.allUsers.remove(user);
    }

    // find user
    public static boolean findUser(User user) {
        for (User u : allUsers) {
            if (u.uid == user.uid)
                return true;
        }
        return false;
    }

    // get a verified User
    public static User getUser(String username, String password) {
        for (User u : allUsers) {
            if (u.username.equals(username) && u.password.equals(password)) {
                return u;
            }
        }
        return null;
    }

    // check if is admin
    public boolean isAdmin() {
        if (username.equals("medialab") && password.equals("medialab_2024")) {
            return true;
        }
        return false;
    }

    // return allUsers list
    public static List<User> getAllUsers() {
        return User.allUsers;
    }

    // Get username
    public String getUsername() {
        return username;
    }

    // Get name
    public String getName() {
        return name;
    }

    // Get surname
    public String getSurname() {
        return surname;
    }

    // Get mobile
    public String getMobile() {
        return mobile;
    }

    // Get id
    public String getID() {
        return uid;
    }

    // Get email
    public String getEmail() {
        return email;
    }

    // Get password
    public String getPassword() {
        return password;
    }

    // get a user by username
    public static User getUserByUsername(String username) {
        for (User u : allUsers) {
            if (u.username.equals(username)) {
                return u;
            }
        }
        return null;
    }

    // Get rented books
    public Book[] getRentedBooks() {
        return rentedBooks;
    }

    // set rented books
    public void addToRentedBooks(Book b) {
        if (this.rentedBooks[0] == null)
            this.rentedBooks[0] = b;
        else if (this.rentedBooks[1] == null)
            this.rentedBooks[1] = b;
        else
            System.out.println("you bicj");
    }

    // remove rented books
    public void removeFromRentedBooks(Book b) {
        if (b == null) {
            System.out.println("Book is null.");
            return;
        }
        if (this.rentedBooks[0] != null && this.rentedBooks[0].getISBN().equals(b.getISBN())) {
            this.rentedBooks[0] = null;
        } else if (this.rentedBooks[1] != null && this.rentedBooks[1].getISBN().equals(b.getISBN())) {
            this.rentedBooks[1] = null;
        }
    }

    // Serialize
    public static void serialize() {
        SerializationUtils.serializeAll(User.allUsers, User.class);
    }

    // Deserialize
    public static void deserialize() {
        User.allUsers = SerializationUtils.deserializeAll(User.class);
    }
}