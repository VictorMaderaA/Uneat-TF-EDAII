package vicand.finaleda.models;

import java.io.File;

public class FileSimple {

    String name;
    String path;
    String extension;

    public FileSimple(File file) {
        name = file.getName();
        path = file.getAbsolutePath();
        extension = getFileExtension(file);
    }

    private static String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }

    @Override
    public String toString() {
        return "File[" + name + ";" + path + ";" + extension + "]";
    }
}
