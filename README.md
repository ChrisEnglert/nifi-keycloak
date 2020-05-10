# nifi-keycloak
Keycloak NiFi AuthProvider



## NiFi properties

# OpenId Connect SSO Properties #
nifi.security.user.oidc.discovery.url=http://<keycloak-host>:<keycloak-port>/auth/realms/{REALM}/.well-known/openid-configuration
nifi.security.user.oidc.connect.timeout=5 secs
nifi.security.user.oidc.read.timeout=5 secs
nifi.security.user.oidc.client.id={CLIENTID}
nifi.security.user.oidc.client.secret={CLIENTSECRET}
nifi.security.user.oidc.preferred.jwsalgorithm=RS256
nifi.security.user.oidc.additional.scopes=
nifi.security.user.oidc.claim.identifying.user=email
