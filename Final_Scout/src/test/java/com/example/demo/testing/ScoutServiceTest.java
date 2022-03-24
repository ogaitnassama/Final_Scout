package com.example.demo.testing;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
	
//	@Test
	public void getById() {
		
		
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(objID));
		
		ScoutInfo result = service.getById(1l);
		
		Assertions.assertEquals(objID, result);
		
		Mockito.verify(repo, Mockito.never()).findById(1l);
	}
	
	
	
	@Test
	public void getReports() {
		
		List<ScoutInfo> readList = new ArrayList<>();
		readList.add(obj);
		
		Mockito.when(this.repo.findAll()).thenReturn(readList);
		
		assertThat(this.service.getReports()).isEqualTo(readList);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		
	}
	@Test 
	public void delete() {
		
		
	}
	
}
