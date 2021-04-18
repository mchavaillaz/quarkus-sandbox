package ch.matthieu.greetings.kafka;

import ch.matthieu.greetings.domain.dto.CreateGreeting;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class CreateGreetingDeserializer extends ObjectMapperDeserializer<CreateGreeting> {
	public CreateGreetingDeserializer() {
		super(CreateGreeting.class);
	}
}
