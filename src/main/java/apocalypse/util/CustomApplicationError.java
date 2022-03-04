package apocalypse.util;
/**
 * @author robertnjoroge
 */


public class CustomApplicationError extends Exception { 
    public CustomApplicationError(String errorMessage) {
        super(errorMessage);
    }
}