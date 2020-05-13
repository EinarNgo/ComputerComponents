package programutvikling.fileHandler;

import programutvikling.base.ComponentRegister;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSaverJobj implements FileSaver {
    @Override
    public void save(ComponentRegister registry, Path filePath) throws IOException {
        Path path = Paths.get("kari.jobj");
        try (OutputStream os = Files.newOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(registry);
        }
    }
}
