package programutvikling.fileHandler;

import java.io.IOException;

public class InvalidPersonFormatException extends IOException {
    InvalidPersonFormatException(String message) {
        super(message);
    }
}
