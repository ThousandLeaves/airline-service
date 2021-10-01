package com.airline.service.api.controllers;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import com.airline.service.api.entities.Flight;
import com.airline.service.api.services.FlightService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlightControllerTest {
	@MockBean
	FlightService flightService;
	@Autowired
    private WebApplicationContext context;
	@Autowired
	private MockMvc mockMvc;
	
	 @Before
	    public void setup() {
	        mockMvc = MockMvcBuilders
	          .webAppContextSetup(context)
	          .apply(springSecurity())
	          .build();
	    }
	 @WithMockUser("spring")
	@Test
	void FlightSearching() throws Exception {
		 Time time=new Time(1500);
		 java.sql.Date date= new java.sql.Date(150000);
		Flight flight= new Flight("AA 716", 1, time,date, time,date, 376, 0, 29, 1, 4, "SFO", "IAH");
		
		when(flightService.flightToBookById("AA 716")).thenReturn(flight);
		mockMvc.perform(get("booking/findOne", 1L)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isCreated());
	}
	

}
