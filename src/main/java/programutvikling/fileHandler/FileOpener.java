package programutvikling.fileHandler;

import programutvikling.base.ComponentRegister;

import java.io.IOException;
import java.nio.file.Path;

public interface FileOpener {

    void open(ComponentRegister componentRegister, Path filePath) throws IOException;

}
