package Database;

/**
 * Catches enum errors when parsing from database
 * @author Alex Dobson
 *
 */
public class EnumMappingException extends Exception {
	public EnumMappingException(String errorMessage) {  
	    super(errorMessage);  
    }  
}
