package programutvikling.fileHandler;

import programutvikling.base.ComponentRegister;
import programutvikling.base.Data;
import programutvikling.base.DataRegister;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSaverTxt implements FileSaver {
    @Override
    public void saveComponent(ComponentRegister registry, Path filePath) throws IOException {
        Files.write(filePath, registry.toString().getBytes());
    }
    @Override
    public void saveData(DataRegister registry, Path filePath) throws IOException {
        Files.write(filePath, registry.toString().getBytes());
    }

}
