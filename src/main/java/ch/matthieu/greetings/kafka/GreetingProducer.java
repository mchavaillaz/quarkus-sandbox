package ch.matthieu.greetings.kafka;

import ch.matthieu.greetings.domain.dto.CreateGreeting;
import com.github.javafaker.Faker;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Locale;

@ApplicationScoped
public class GreetingProducer {
	private Faker faker;

	public GreetingProducer() {
		this.faker = new Faker(Locale.FRANCE);
	}

	@Outgoing("generated-greeting")
	public Multi<CreateGreeting> generate() {
		return Multi
				.createFrom()
				.ticks()
				.every(Duration.ofSeconds(10))
				.onOverflow()
				.drop()
				.map(tick -> new CreateGreeting(faker.name().firstName()));
	}
}
