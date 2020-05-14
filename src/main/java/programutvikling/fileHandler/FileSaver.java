package programutvikling.fileHandler;

import programutvikling.base.ComponentRegister;
import programutvikling.base.DataRegister;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {

    void saveComponent(ComponentRegister registry, Path filePath) throws IOException;
    void saveData(DataRegister registry, Path filePath) throws IOException;

}
