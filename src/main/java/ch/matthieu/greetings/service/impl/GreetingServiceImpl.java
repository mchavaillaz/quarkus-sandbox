package ch.matthieu.greetings.service.impl;

import ch.matthieu.greetings.domain.dto.CreateGreeting;
import ch.matthieu.greetings.domain.dto.ReadGreeting;
import ch.matthieu.greetings.domain.entity.Greeting;
import ch.matthieu.greetings.repository.GreetingRepository;
import ch.matthieu.greetings.service.GreetingService;
import io.reactivex.functions.Consumer;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GreetingServiceImpl implements GreetingService {

	private final GreetingRepository greetingRepository;

	@Inject
	public GreetingServiceImpl(GreetingRepository greetingRepository) {
		this.greetingRepository = greetingRepository;
	}

	@Override
	public ReadGreeting createGreeting(CreateGreeting createGreeting) {
		return convertToDto(greetingRepository.saveAndFlush(convertToEntity(createGreeting)));
	}

	@Override
	public List<ReadGreeting> getGreetings(Integer limit) {
		if (limit == null) {
			return convertToDtoList(greetingRepository.findAll());
		}

		final Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "id"));
		final Page<Greeting> greetingPage = greetingRepository.findAll(pageable);
		return greetingPage
				.get()
				.map(this::convertToDto)
				.collect(Collectors.toList());

	}

	private List<ReadGreeting> convertToDtoList(List<Greeting> sourceGreetingList) {
		if (sourceGreetingList.isEmpty()) {
			return Collections.emptyList();
		}

		return sourceGreetingList
				.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	private ReadGreeting convertToDto(Greeting sourceGreeting) {
		final ReadGreeting destinationReadGreeting = new ReadGreeting();
		destinationReadGreeting.setId(sourceGreeting.getId());
		destinationReadGreeting.setName(sourceGreeting.getName());
		destinationReadGreeting.setCreatedMoment(sourceGreeting.getCreatedMoment());
		return destinationReadGreeting;
	}

	private Greeting convertToEntity(CreateGreeting sourceCreateGreeting) {
		final Greeting destinationGreeting = new Greeting();
		destinationGreeting.setName(sourceCreateGreeting.getName());
		destinationGreeting.setCreatedMoment(Instant.now());
		return destinationGreeting;
	}
}
