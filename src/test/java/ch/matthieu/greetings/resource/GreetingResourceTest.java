package ch.matthieu.greetings.resource;

import ch.matthieu.greetings.domain.dto.CreateGreeting;
import ch.matthieu.greetings.domain.dto.ReadGreeting;
import ch.matthieu.greetings.service.GreetingService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GreetingResourceTest {

	@InjectMock
	GreetingService greetingServiceMock;

	/**
	 * Test the method {@link GreetingService#createGreeting(ch.matthieu.greetings.domain.dto.CreateGreeting)}.
	 * Test when the greeting is successfully created and returned as response body.
	 */
	@Test
	void createGreetingTest1() {
		final Integer expectedId = 1;
		final String expectedName = "Matthieu";
		final Instant expectedCreatedMoment = Instant.now();

		final CreateGreeting sentCreatedGreeting = new CreateGreeting();
		sentCreatedGreeting.setName(expectedName);

		final ReadGreeting expectedReadGreeting = new ReadGreeting();
		expectedReadGreeting.setId(expectedId);
		expectedReadGreeting.setName(expectedName);
		expectedReadGreeting.setCreatedMoment(expectedCreatedMoment);

		Mockito.when(greetingServiceMock.createGreeting(sentCreatedGreeting))
				.thenReturn(expectedReadGreeting);

		final ReadGreeting returnedReadGreeting = given()
				.body(sentCreatedGreeting)
				.contentType(ContentType.JSON)
				.when()
				.post("/greetings")
				.then()
				.assertThat()
				.statusCode(Response.Status.CREATED.getStatusCode())
				.contentType(ContentType.JSON)
				.extract()
				.as(ReadGreeting.class);

		assertEquals(expectedReadGreeting, returnedReadGreeting);
	}

	/**
	 * Test the method {@link GreetingService#createGreeting(ch.matthieu.greetings.domain.dto.CreateGreeting)}.
	 * Test when the greeting cannot be persisted.
	 */
	@Test
	void createGreetingTest2() {
		final String name = "Matthieu";

		final CreateGreeting sentCreatedGreeting = new CreateGreeting();
		sentCreatedGreeting.setName(name);

		Mockito.when(greetingServiceMock.createGreeting(sentCreatedGreeting))
				.thenReturn(null);

		given()
				.body(sentCreatedGreeting)
				.contentType(ContentType.JSON)
				.when()
				.post("/greetings")
				.then()
				.assertThat()
				.statusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
	}
}
