package programutvikling.fileHandler;

import programutvikling.base.ComponentRegister;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {

    void save(ComponentRegister registry, Path filePath) throws IOException;

}
