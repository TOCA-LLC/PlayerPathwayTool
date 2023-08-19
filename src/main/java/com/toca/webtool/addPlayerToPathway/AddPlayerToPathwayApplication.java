package com.toca.webtool.addPlayerToPathway;

import com.toca.webtool.addPlayerToPathway.model.Pathway;
import com.toca.webtool.addPlayerToPathway.utility.AddPlayerToPathway;
import com.toca.webtool.addPlayerToPathway.utility.ExcelParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AddPlayerToPathwayApplication {

	public static void main(String[] args) throws IOException {
//		Pathway pathway = new Pathway(100000102, "Beginner Intensive", -2147482215, 65);
//		AddPlayerToPathway.addPlayer(1, pathway);
		List<Pathway> pathways = ExcelParser.parseExcel();

		pathways.remove(0);
		System.out.println("Number of Records: " + pathways.size());

		pathways.get(1140).getData();

	}

}
