package com.example.demo.testing;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.model.ScoutInfo;
import com.example.demo.repo.Repo;
import com.example.demo.service.ScoutService;



@SpringBootTest
public class ScoutServiceTest {

	
	
	@MockBean Repo repo;
	
	
	@Autowired
	private ScoutService service;
	
	
	ScoutInfo obj = new ScoutInfo("Kante", 25, "Paris-France", "CDM", "Holding player", 83000000, false, "Player is the best in his position for country and club");
	ScoutInfo obj2 = new ScoutInfo("Koke", 27, "Madrid-Spain", "CM", "Space maker", 43000000, true, "Player is not happpy at current club");
	
	
	ScoutInfo objID = new ScoutInfo(1l, "Kante", 25, "Paris-France", "CDM", "Holding player", 83000000, false, "Player is the best in his position for country and club");
	ScoutInfo objID2 = new ScoutInfo(2l,"Koke", 27, "Madrid-Spain", "CM", "Space maker", 43000000, true, "Player is not happpy at current club");
	
	
	@Test 
	public void createReport() {
		
		Mockito.when(repo.save(obj)).thenReturn(objID);
		
		ScoutInfo result = service.createReport(obj);
		System.out.println(result);
		System.out.println(obj);
		
		
		Assertions.assertEquals(objID, result);
		
		Mockito.verify(repo, Mockito.times(1)).save(obj);
	
	}
	
	@Test
	public void getById() {
		
		
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(objID));
		
		ScoutInfo result = service.getById(1l);
		
		Assertions.assertEquals(objID, result);
		
		Mockito.verify(repo, Mockito.times(1)).findById(1l);
	}
	@Test
	public void getByregion() {
		
		String ss = "Madrid-Spain";
		List<ScoutInfo> reportSS = new ArrayList<>();
		reportSS.add(obj);
		
		Mockito.when(this.repo.findByRegion(ss)).thenReturn(reportSS);
		
		assertThat(this.service.getByRegion(ss)).isEqualTo(reportSS);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByRegion(ss);
		
	}
	@Test 
	public void getByPlaystyle() {
		
		String PS = "Space maker";
		List<ScoutInfo> reportPS = new ArrayList<>();
		reportPS.add(obj2);
		
		Mockito.when(this.repo.findByPlaystyle(PS)).thenReturn(reportPS);
		
		assertThat(this.service.getByPlaystyle(PS)).isEqualTo(reportPS);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByPlaystyle(PS);
	
	
	
	}
	@Test 
	public void getByPosition() {
		
		String POS = "CM";
		List<ScoutInfo> reportPS = new ArrayList<>();
		reportPS.add(obj2);
		
		Mockito.when(this.repo.findByPosition(POS)).thenReturn(reportPS);
		
		assertThat(this.service.getByPosition(POS)).isEqualTo(reportPS);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByPosition(POS);
	
	
	
	}
	@Test
	public void getReports() {
		
		List<ScoutInfo> readList = new ArrayList<>();
		readList.add(obj2);
		
		Mockito.when(this.repo.findAll()).thenReturn(readList);
		
		assertThat(this.service.getReports()).isEqualTo(readList);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		
	}
	@Test 
	public void getByIsInjured() {
		
		Boolean INJ = true;
		List<ScoutInfo> reportPS = new ArrayList<>();
		reportPS.add(obj2);
		
		Mockito.when(this.repo.findByInjured(INJ)).thenReturn(reportPS);
		
		assertThat(this.service.getByInjured(INJ)).isEqualTo(reportPS);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByInjured(true);
	
	
	
	}
	
	
}
