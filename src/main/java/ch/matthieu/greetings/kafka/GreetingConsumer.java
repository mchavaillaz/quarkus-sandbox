package ch.matthieu.greetings.kafka;

import ch.matthieu.greetings.domain.dto.CreateGreeting;
import ch.matthieu.greetings.service.GreetingService;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GreetingConsumer {

	private GreetingService greetingService;

	@Inject
	public GreetingConsumer(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@Incoming("greetings")
//	@Outgoing("internal-greeting-stream")
//	@Broadcast
	@Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
	public void process(CreateGreeting createGreeting) {
		System.out.println(createGreeting.getName());
//		return new ReadGreeting(1, "coucou", Instant.now());
//		return Uni
//				.createFrom()
//				.item(greetingService.createGreeting(createGreeting));
	}
}
