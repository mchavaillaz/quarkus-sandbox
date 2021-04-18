package ch.matthieu.greetings.resource;

import ch.matthieu.greetings.domain.dto.CreateGreeting;
import ch.matthieu.greetings.domain.dto.ReadGreeting;
import ch.matthieu.greetings.service.GreetingService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/greetings")
public class GreetingResource {

	private GreetingService greetingService;

	@Inject
	public GreetingResource(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GET
	public Response getGreetings(@QueryParam("query") Integer limit) {
		return Response.ok(
				greetingService.getGreetings(limit),
				MediaType.APPLICATION_JSON_TYPE)
				.build();
	}

	@POST
	public Response createGreeting(CreateGreeting createGreeting) {
		final ReadGreeting readGreetingCreated = greetingService.createGreeting(createGreeting);

		if (readGreetingCreated == null) {
			return Response
					.serverError()
					.build();
		}

		return Response
				.ok()
				.status(Response.Status.CREATED)
				.entity(readGreetingCreated)
				.build();
	}
}
