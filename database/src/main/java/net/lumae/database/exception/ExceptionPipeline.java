package net.lumae.database.exception;

public class ExceptionPipeline {
    public void submitException(AbstractDatabaseException exception) {
        try {
            throw exception;
        } catch(AbstractDatabaseException e) {

        }
    }
}
