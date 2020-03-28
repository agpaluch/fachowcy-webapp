package images;

import javax.enterprise.context.SessionScoped;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

@SessionScoped
public class FileUploadProcessor implements FileUploadProcessorIF, Serializable {

    private static final String SETTINGS_FILE = "settings.properties";

    public Optional<File> processImage(Part filePart) {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (fileName == null || fileName.isEmpty()) {
            return Optional.empty();
        }
        File file = new File(getUploadImageFilesPath() + fileName);
        try (InputStream is = filePart.getInputStream();
             OutputStream os = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return Optional.of(file);
    }

    public String getUploadImageFilesPath() {
        Properties settings = new Properties();
        try {
            settings.load(Thread.currentThread()
                    .getContextClassLoader().getResource(SETTINGS_FILE).openStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        final String property = settings.getProperty("images.path");
        return property.replaceAll("\\$USER", System.getenv("USER"));
    }
}
