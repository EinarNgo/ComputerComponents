package programutvikling.fileHandler;

import programutvikling.base.ComponentRegister;
import programutvikling.base.DataRegister;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOpenerJobj implements FileOpener {
    @Override
    public void openComponent(ComponentRegister componentRegister, Path filePath) throws IOException {
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

    @Override
    public void openData(DataRegister dataRegister, Path filePath) throws IOException {
        try (InputStream fin = Files.newInputStream(filePath);
             ObjectInputStream oin = new ObjectInputStream(fin))
        {
            DataRegister register = (DataRegister) oin.readObject();
            dataRegister.removeAll();
            register.getRegister().forEach(dataRegister::addData);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // debug information here
            throw new IOException("Something is wrong with the implementation. See debug information");
        }
    }
}
