package com.team11;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.team11.dao.LevelRepository;
import com.team11.dao.SemesterRepository;
import com.team11.entity.Level;
import com.team11.entity.Semester;

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
		
	}
	
}
