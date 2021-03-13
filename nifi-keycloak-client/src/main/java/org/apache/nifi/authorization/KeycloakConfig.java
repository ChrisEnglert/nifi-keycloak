package org.apache.nifi.authorization;

import org.apache.nifi.authorization.exceptions.PropertyNotSetException;
import org.apache.nifi.components.PropertyValue;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakConfig {

    public static final String PROP_SERVER_URL = "ServerUrl";
    public static final String PROP_REALM = "Realm";
    public static final String PROP_USERNAME = "Username";
    public static final String PROP_PASSWORD = "Password";
    public static final String PROP_CLIENT_ID = "ClientID";
    public static final String PROP_CLIENT_SECRET = "ClientSecret";

    private final String server;
    private final String realm;
    private final String clientId;
    private final String clientSecret;
    private final String username;
    private final String password;

    private KeycloakConfig(String server,
                           String realm,
                           String username,
                           String password,
                           String clientId,
                           String clientSecret) {

        this.server = server;
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.username = username;
        this.password = password;
    }

    public static KeycloakConfig from(AuthorizerConfigurationContext context) {
        return new KeycloakConfig(
            getProperty(context, PROP_SERVER_URL),
            getProperty(context, PROP_REALM),
            getProperty(context, PROP_USERNAME),
            getProperty(context, PROP_PASSWORD),
            getProperty(context, PROP_CLIENT_ID),
            getOptionalProperty(context, PROP_CLIENT_SECRET)

        );
    }

    private static String getProperty(AuthorizerConfigurationContext context, String property) {
        PropertyValue propertyValue = context.getProperty(property);
        if (propertyValue == null || !propertyValue.isSet()) {
            throw new PropertyNotSetException(property);
        }

        return propertyValue.getValue();
    }

    private static String getOptionalProperty(AuthorizerConfigurationContext context, String property) {
        PropertyValue propertyValue = context.getProperty(property);
        if (propertyValue == null || !propertyValue.isSet()) {
            return null;
        }

        return propertyValue.getValue();
    }

    public final String getRealm() {
        return realm;
    }

    public KeycloakBuilder keycloakBuilder() {
        KeycloakBuilder builder = KeycloakBuilder.builder();
        apply(builder);
        return builder;
    }

    public KeycloakBuilder apply(KeycloakBuilder builder) {
        return builder.serverUrl(server)
            .realm(realm)
            .username(username)
            .password(password)
            .clientId(clientId)
            .clientSecret(clientSecret);
    }
}
