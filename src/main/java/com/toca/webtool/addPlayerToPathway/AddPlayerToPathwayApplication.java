package com.toca.webtool.addPlayerToPathway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toca.webtool.addPlayerToPathway.model.Pathway;
import com.toca.webtool.addPlayerToPathway.utility.AddPlayerToPathway;
import com.toca.webtool.addPlayerToPathway.utility.ExcelParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AddPlayerToPathwayApplication {

	public static void main(String[] args) throws IOException {
		List<Pathway> pathways = ExcelParser.parseExcel();
		pathways.remove(0);

		System.out.println("Number of Records: " + pathways.size());

		int count = 1;
		for(Pathway pathway : pathways){
			System.out.print("\n" + count + ": ");
			AddPlayerToPathway.addPlayer(count, pathway);
			count++;
		}

		saveFailures(AddPlayerToPathway.getFailures());
	}

	public static void saveFailures(List<Pathway> failures) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writeValueAsString(failures);

		FileWriter fileWriter = new FileWriter("C:\\Users\\Dr. Maxwell\\dev\\addPlayerToPathway\\files\\FailedPlayerPathways.json");
		fileWriter.write(jsonData);
		fileWriter.close();
	}

}
