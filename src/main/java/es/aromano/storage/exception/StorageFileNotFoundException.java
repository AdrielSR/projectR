package es.aromano.storage.exception;

import java.net.MalformedURLException;

public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String msg) {
        super(msg);
    }

    public StorageFileNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
