package org.acme.user.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acme.user.service.UsersService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {
	private final UsersService usersService;

	public UsersResource(UsersService usersService) {
		this.usersService = usersService;
	}

	@GET
	public List<UserDto> getAllUsers() {
		return this.usersService.getAllUsers();
	}

	@GET
	@Path("/{userId}")
	public Response getUser(@PathParam("userId") Long userId) {
		return this.usersService.findUser(userId)
			.map(user -> Response.ok(user).build())
			.orElseGet(() -> Response.status(Status.NOT_FOUND).build());
	}
}
