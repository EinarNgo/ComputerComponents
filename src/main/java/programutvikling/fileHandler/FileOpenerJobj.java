package programutvikling.fileHandler;

import programutvikling.base.ComponentRegister;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOpenerJobj implements FileOpener {
    @Override
    public void open(ComponentRegister componentRegister, Path filePath) throws IOException {
        try (InputStream fin = Files.newInputStream(filePath);
             ObjectInputStream oin = new ObjectInputStream(fin))
        {
            ComponentRegister register = (ComponentRegister) oin.readObject();
            componentRegister.removeAll();
            register.getRegister().forEach(componentRegister::addComponent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // debug information here
            throw new IOException("Something is wrong with the implementation. See debug information");
        }
    }
}
