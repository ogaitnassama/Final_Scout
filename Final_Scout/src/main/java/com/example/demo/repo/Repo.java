package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ScoutInfo;


public interface Repo extends JpaRepository<ScoutInfo, Long>{

	public List<ScoutInfo> findByRegion(String region);
	
	public List<ScoutInfo> findByPosition(String position);
	
	public List<ScoutInfo> findByPlaystyle(String playstyle);
	
	public List<ScoutInfo> findByAge(int age);
	
	public List<ScoutInfo> findByInjured(boolean injured);
	
	public List<ScoutInfo> findByOrderByPriceAsc();
	
}
