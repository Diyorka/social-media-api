package kg.diyor.socialmediaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author: Diyor Umurzakov
 * GitHub: Diyorka
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TokenNotValidException extends RuntimeException{
    public TokenNotValidException(String message){
        super(message);
    }
}
