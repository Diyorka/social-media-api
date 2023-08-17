package kg.diyor.socialmediaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoAccessException extends RuntimeException{
    public NoAccessException(String message){
        super(message);
    }
}
