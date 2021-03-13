package org.apache.nifi.authorization.exceptions;

public class PropertyNotSetException extends RuntimeException {

    public PropertyNotSetException(String property) {
        super("KeycloakUserGroupProvider config property '" + property + "' is not set");
    }
}
