package com.team11;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.team11.dao.LevelRepository;
import com.team11.dao.SemesterRepository;
import com.team11.dao.StatusRepository;
import com.team11.entity.Level;
import com.team11.entity.Semester;
import com.team11.entity.Status;

@SpringBootApplication
public class SpringmvcdemoApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(SpringmvcdemoApplication.class, args);
	}

//Insert sample data into database for testing purposes
@Autowired
private LevelRepository levelRepository;
@Autowired
private SemesterRepository semesterRepository;
@Autowired
private StatusRepository statusRepository;

	@Override
	public void run(String... args) throws Exception {
		//Sample level data
		ArrayList<Level> levels = new ArrayList<Level>();
		levels.add(new Level(1, 1));
		levels.add(new Level(2, 2));
		levels.add(new Level(3, 3));
		levels.add(new Level(4, 4));
		levelRepository.saveAll(levels);
		
		//Sample Semester data
		ArrayList<Semester> semesters = new ArrayList<Semester>();
		semesters.add(new Semester(1,"Semester One"));
		semesters.add(new Semester(2,"Semester Two"));
		semesters.add(new Semester(3,"Semester Three"));
		semesterRepository.saveAll(semesters);
		
		//Sample status data
		ArrayList<Status> statuses = new ArrayList<Status>();
		statuses.add(new Status(1, "Undergraduate"));
		statuses.add(new Status(2, "Applied for graduation"));
		statuses.add(new Status(3, "Graduated"));
		statuses.add(new Status(4, "Other"));
		statusRepository.saveAll(statuses);
	}
	
}
