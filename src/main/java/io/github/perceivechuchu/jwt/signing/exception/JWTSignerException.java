package io.github.perceivechuchu.jwt.signing.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * JWTSignerException is thrown JWT signing fails
 *
 * @since 1.0.0
 *
 * @author Perceive Chuchu
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = true)
public class JWTSignerException extends Exception {

    /**
     * the message that is returned when the exception is thrown
     *
     * @since 1.0.0
     */
    private final String message;

    /**
     * the constructor that instantiates an instance of JWTSignerException
     *
     * @param message the message that is returned when the exception is thrown
     * @since 1.0.0
     */
    public JWTSignerException(String message) {
        super(message);
        this.message = message;
    }

}
