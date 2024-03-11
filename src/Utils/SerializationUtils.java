package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializationUtils {

    /**
     * Serializes a list of objects to a file in the 'medialab' directory, specific
     * to the class type of the objects.
     * The method checks for or creates the necessary directory structure before
     * serializing the objects.
     * 
     * @param <T>     The type of the objects in the list, which extends
     *                Serializable.
     * @param objects The list of objects to be serialized.
     * @param clazz   The Class object of the objects in the list, used to define
     *                the directory and file names.
     * 
     * @throws IOException if an I/O error occurs during serialization.
     */
    public static <T extends Serializable> void serializeAll(List<T> objects, Class<T> clazz) {

        String basePath = System.getProperty("user.dir"); // Get the current working directory
        File directory = new File(basePath + File.separator + "medialab" + File.separator + clazz.getSimpleName());

        // Check if the directory exists, if not, create it
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully.");
            } else {
                System.out.println("Failed to create directory.");
                return; // Stop the method if the directory cannot be created
            }
        }

        try {
            String filePath = directory.getAbsolutePath() + File.separator + clazz.getSimpleName() + ".ser";
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(objects);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filePath);
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    /**
     * Deserializes and returns a list of objects from a file in the 'medialab'
     * directory, specific to the class type.
     * The method checks for the existence of the necessary directory and file
     * before deserializing.
     * 
     * @param <T>   The type of the objects to be deserialized.
     * @param clazz The Class object of the type T, used to define the directory and
     *              file names.
     * @return A List of deserialized objects of type T. Returns an empty list if
     *         the file does not exist, is not a directory, or an error occurs
     *         during deserialization.
     * 
     * @throws FileNotFoundException  if the file with serialized objects does not
     *                                exist.
     * @throws IOException            if an I/O error occurs during deserialization.
     * @throws ClassNotFoundException if the class of a serialized object cannot be
     *                                found.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> deserializeAll(Class<T> clazz) {
        List<T> objects = new ArrayList<>();
        String basePath = System.getProperty("user.dir"); // Get the current working directory
        File directory = new File(basePath + File.separator + "medialab" + File.separator + clazz.getSimpleName());

        // Ensure the directory exists
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory.");
            return objects; // Return an empty list instead of null to avoid NullPointerException
        }

        File file = new File(directory, clazz.getSimpleName() + ".ser");

        if (file.exists() && file.isFile()) {
            try (FileInputStream fileIn = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fileIn)) {
                // This unchecked cast is necessary due to type erasure with generics
                objects = (List<T>) in.readObject();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Serialized file does not exist.");
        }

        return objects;
    }

}