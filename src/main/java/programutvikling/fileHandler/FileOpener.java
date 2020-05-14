package programutvikling.fileHandler;

import programutvikling.base.ComponentRegister;
import programutvikling.base.DataRegister;

import java.io.IOException;
import java.nio.file.Path;

public interface FileOpener {

    void openComponent(ComponentRegister componentRegister, Path filePath) throws IOException;
    void openData(DataRegister dataRegister, Path filePath) throws IOException;
}
