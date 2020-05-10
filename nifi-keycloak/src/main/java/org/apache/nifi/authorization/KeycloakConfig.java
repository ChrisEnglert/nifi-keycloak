package org.apache.nifi.authorization;

import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakConfig {

    private final String SERVER_URL = "http://localhost:4000/auth";
    private final String REALM = "master";
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";
    private final String CLIENT_ID = "admin-cli";


    public static KeycloakConfig from(AuthorizerConfigurationContext configurationContext) {
        return new KeycloakConfig();
    }


    public final String getRealm() {
        return REALM;
    }

    public KeycloakBuilder builder() {
        var builder = KeycloakBuilder.builder();
        apply(builder);
        return builder;
    }

    public KeycloakBuilder apply(KeycloakBuilder builder) {

        builder.serverUrl(SERVER_URL)
                .realm(REALM)
                .username(USERNAME)
                .password(PASSWORD)
                .clientId(CLIENT_ID);
        return builder;
    }

}
