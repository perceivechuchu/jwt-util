package io.github.perceivechuchu.jwt.signing.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The exception that is thrown when the JWT signing fails
 *
 * @author Perceive Chuchu
 * @since 1.0.0
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class JWTSignerException extends Exception {

    /**
     * The message that is returned when the exception is thrown
     *
     * @since 1.0.0
     */
    private final String message;

    /**
     * The constructor that instantiates an instance of JWTSignerException
     *
     * @param message the message that is returned when the exception is thrown
     * @since 1.0.0
     */
    public JWTSignerException(String message) {
        super(message);
        this.message = message;
    }

}
