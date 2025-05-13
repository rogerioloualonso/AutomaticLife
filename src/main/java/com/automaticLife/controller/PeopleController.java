package com.automaticLife.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.automaticLife.dto.PeopleDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "People", description = "People operations")
public interface PeopleController {

	@ApiOperation(value = "Create new people")
	@RequestBody(description = "People data to edit", 
	 required = true, 
	 content = @Content(schema = 
	 	@Schema(implementation = PeopleDTO.class), 
	 	examples = @ExampleObject(name = "Example People", 
	 	value = """
				{
				  "name": "Maria Silva",
				  "phoneNumer": "21987458789",
				  "birthday": "2022-09-20 21:02:00"
				}
		""")))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "People created!"),
			@ApiResponse(responseCode = "500", description = "Sorry, bad execution...") })
	@RequestMapping(value = "/people", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(PeopleDTO people);

	@ApiOperation(value = "Edit people")
	@Parameter(description = "ID of people", example = "1", required = true)
	@RequestBody(description = "People data to edit", 
				 required = true, 
				 content = @Content(schema = 
				 	@Schema(implementation = PeopleDTO.class), 
				 	examples = @ExampleObject(name = "Example People", 
				 	value = """
							{
							  "name": "Maria Silva",
							  "phoneNumer": "21987458789",
							  "birthday": "2022-09-20 21:02:00"
							}
					""")))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Congratulations send!"),
			@ApiResponse(responseCode = "404", description = "People not found."),
			@ApiResponse(responseCode = "500", description = "Sorry, bad execution...") })
	@RequestMapping(value = "/people", method = RequestMethod.PUT)
	public ResponseEntity<Void> edit(@Parameter(description = "ID of people", example = "1", required = true) int id, PeopleDTO people);

	@ApiOperation(value = "Delete people")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Congratulations send!"),
			@ApiResponse(responseCode = "404", description = "People not found."),
			@ApiResponse(responseCode = "500", description = "Sorry, bad execution...") })
	@RequestMapping(value = "/people", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@Parameter(description = "ID of people", example = "1", required = true) int id);
}
