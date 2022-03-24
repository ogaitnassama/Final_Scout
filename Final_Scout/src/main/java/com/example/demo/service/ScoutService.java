package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ScoutInfo;
import com.example.demo.repo.Repo;


@Service
public class ScoutService {
	private Repo repo;

	public ScoutService(Repo repo) {
		super();
		this.repo = repo;
	}
	
	public ScoutInfo createReport(ScoutInfo report) {
		ScoutInfo savedReport = repo.save(report);
//		repo.save(report); // this takes an entity and puts it in the DB
		return savedReport;
	}
	
	public ScoutInfo getById( long id) {
		return repo.findById(id).get();
	}
	
	public List<ScoutInfo> getReports(){
		
		return repo.findAll();
	}
	
	public boolean update(long id, ScoutInfo report) {
		
		ScoutInfo oldReport = getById(id);
		
		oldReport.setPlayer(report.getPlayer());
		oldReport.setAge(report.getAge());
		oldReport.setRegion(report.getRegion());
		oldReport.setPosition(report.getPosition());
		oldReport.setPlaystyle(report.getPlaystyle());
		oldReport.setPrice(report.getPrice());
		oldReport.setInjured(report.isInjured());
		oldReport.setDetails(report.getDetails());
		
		ScoutInfo updatedReport = oldReport;
		
		repo.save(updatedReport);
		
		return true;
	}
	public boolean deleteAll() {
		repo.deleteAll();
		return true;
	}
	public boolean remove(long Id) {
		repo.deleteById(Id);
		
		return true;
	}
	public List<ScoutInfo> getByRegion(String region){
		return repo.findByRegion(region);
	}
	public List<ScoutInfo> getByInjured(boolean injured){
		return repo.findByInjured(injured);
	}
	public List<ScoutInfo> getByPosition(String position){
		return repo.findByPosition(position);
	}
	public List<ScoutInfo> getByPlaystyle (String playstyle){
		return repo.findByPlaystyle(playstyle);
	}
	
	
	
}

