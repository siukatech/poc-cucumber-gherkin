package com.siukatech.poc.cucumber.gherkin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
//@SpringBootTest(properties = {
//		"oauth2.client.keycloak=xxx"
//		, "client-realm=xxx"
//})
@TestPropertySource(
//		properties = {
//		"client-realm=react-backend-realm"
//		, "oauth2.client.keycloak=http://localhost:38180"
//		}
//		,
		locations = {"classpath:oauth2-tests-local.properties"}
)
class AppTests {

//	@MockitoBean
//	private OAuth2ClientProperties oAuth2ClientProperties;
//	@MockitoBean
//	private InMemoryClientRegistrationRepository clientRegistrationRepository;
//	@MockitoBean
//	private OAuth2ResourceServerProperties oAuth2ResourceServerProperties;
//	@MockitoBean
//	private JwtDecoder jwtDecoder;

	@Test
	void contextLoads() {
	}

}
