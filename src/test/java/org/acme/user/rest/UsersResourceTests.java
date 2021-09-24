package org.acme.user.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.acme.car.rest.CarDto;
import org.acme.common.domain.Make;
import org.acme.user.service.UsersService;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
class UsersResourceTests {
	@InjectMock
	UsersService usersService;

	@Test
	public void getAllNoUsers() {
		when(this.usersService.getAllUsers())
			.thenReturn(List.of());

		given()
			.when().get("/users")
			.then()
				.statusCode(200)
				.body("$.size()", is(0));

		verify(this.usersService).getAllUsers();
		verifyNoMoreInteractions(this.usersService);
	}

	@Test
	public void getAllUsersExist() {
		when(this.usersService.getAllUsers())
			.thenReturn(List.of(
				new UserDto(1L, "Eric"),
				new UserDto(2L, "Jim", List.of(
					new CarDto(1L, Make.FORD, "F150"),
					new CarDto(2L, Make.HONDA, "CIVIC")
				))
			));

		given()
			.when().get("/users")
			.then()
				.statusCode(200)
				.body(
					"$.size()", is(2),
					"[0].id", is(1),
					"[0].name", is("Eric"),
					"[0].cars.size()", is(0),
					"[1].id", is(2),
					"[1].name", is("Jim"),
					"[1].cars.size()", is(2),
					"[1].cars[0].id", is(1),
					"[1].cars[0].make", is("FORD"),
					"[1].cars[0].model", is("F150"),
					"[1].cars[1].id", is(2),
					"[1].cars[1].make", is("HONDA"),
					"[1].cars[1].model", is("CIVIC")
				);

		verify(this.usersService).getAllUsers();
		verifyNoMoreInteractions(this.usersService);
	}

	@Test
	public void getUserDontExist() {
		when(this.usersService.findUser(eq(1L)))
			.thenReturn(Optional.empty());

		given()
			.when().get("/users/1")
			.then()
				.statusCode(404);

		verify(this.usersService).findUser(eq(1L));
		verifyNoMoreInteractions(this.usersService);
	}

	@Test
	public void getUserExists() {
		when(this.usersService.findUser(eq(1L)))
			.thenReturn(Optional.of(
				new UserDto(2L, "Jim", List.of(
					new CarDto(1L, Make.FORD, "F150"),
					new CarDto(2L, Make.HONDA, "CIVIC")
				))
			));

		given()
			.when().get("/users/1")
			.then()
				.statusCode(200)
				.body(
					"name", is("Jim"),
					"cars.size()", is(2),
					"cars[0].id", is(1),
					"cars[0].make", is("FORD"),
					"cars[0].model", is("F150"),
					"cars[1].id", is(2),
					"cars[1].make", is("HONDA"),
					"cars[1].model", is("CIVIC")
				);

		verify(this.usersService).findUser(eq(1L));
		verifyNoMoreInteractions(this.usersService);
	}
}
