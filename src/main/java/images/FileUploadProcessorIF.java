package images;

import javax.servlet.http.Part;
import java.io.File;
import java.util.Optional;

public interface FileUploadProcessorIF {

    Optional<File> processImage(Part filePart);
    String getUploadImageFilesPath();

}
