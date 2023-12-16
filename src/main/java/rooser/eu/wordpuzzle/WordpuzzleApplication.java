package rooser.eu.wordpuzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class WordpuzzleApplication {

	public static void main(String[] args) {

		SpringApplication.run(WordpuzzleApplication.class, args);
		Logger.getLogger(WordpuzzleApplication.class.getName()).info("Application started");
	}

}
