package com.example.demo.testing;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.model.ScoutInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ActiveProfiles('')
@Sql(scripts = {"classpath:report-testing.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ControllerTest{

	@Autowired
	private  MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	ScoutInfo test = new ScoutInfo("Pedri", 19, "Barcelona-Spain", "CM", "Tiki Taka", 90000000, false, "Club is not willing to sell, Must activate release clause");
	ScoutInfo testID =  new ScoutInfo(1l, "Pedri", 19, "Barcelona-Spain","CM", "Tiki Taka",90000000, false, "Club is not willing to sell, Must activate release clause");
	ScoutInfo testID2 =  new ScoutInfo(2l, "Tiago", 19, "London-England", "CDM", "Box to Box",5000000, false, "Not in a club but agent is demanding a signing fee of 15%");
	
	@BeforeAll
	public void setUp() throws Exception {
		String resultJSON = mapper.writeValueAsString(test);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(resultJSON);
		mvc.perform(req);
	}
	
	
	@Test 
	public void testCreate() throws Exception{
		
		String reportJSON = mapper.writeValueAsString(test);
		
		RequestBuilder req = post("/createReport").contentType(MediaType.APPLICATION_JSON).content(reportJSON);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().string("Report added with ID : 3");
		
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test 
	public void testGetID() throws JsonProcessingException{
		
		ScoutInfo testID = test;
		testID.setId(3l);
		
		String testResultJson = mapper.writeValueAsString(testID);
		
		RequestBuilder req = get("/getId/3");
		
		ResultMatcher checkStatus = status().isAccepted();
		
		ResultMatcher checkBody = content().json(testResultJson);
			
	}
	
	@Test 
	public void testGetAll() throws Exception{
	
		List<ScoutInfo> allReports = List.of(testID, testID2);
		
		
		String allReportsJson = mapper.writeValueAsString(allReports);
		
		RequestBuilder req = get("/getReports");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(allReportsJson);
		
		mvc.perform(req).andExpect(checkStatus); 
		
		
	}
	@Test
	public void testGetRegion() throws Exception{
		
		List<ScoutInfo> allReports = List.of(testID);
		
		String allReportsRegion = mapper.writeValueAsString(allReports);
		
		RequestBuilder req = get("/getRegion/Barcelona-Spain");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(allReportsRegion);
		
		mvc.perform(req).andExpect(checkBody).andExpect(checkStatus);
		
		
		
	}
	
	
	@Test
	public void testDeleteId() throws Exception {
	
		RequestBuilder req = delete("/delete/1");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().string("Report of ID : 1 deleted");
		
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test 
	public void deleteAll() throws Exception{
		
		RequestBuilder req = delete("/deleteAll");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().string("All Reports have been deleted");
		
		mvc.perform(req).andExpect(checkBody).andExpect(checkStatus);
		
	}
	
	
	
	@Test 
	public void testUpdate() throws Exception{
		
		ScoutInfo updatedReport = new ScoutInfo("Kaka", 45, "SaoPaolo-Brazil","CAM", "Playmaker", 15000000, true, "Retired but willing to come back for 200k weekly salary");
		
		String updatedReportJson = mapper.writeValueAsString(updatedReport);
		
		RequestBuilder req  = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(updatedReportJson);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("Updating report of ID : 1");
	
	
	
	}
	
	
	
	
}

