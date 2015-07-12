package io.mstream.pinger.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Configuration
public class PingConfiguration {

	@Bean
	public RestTemplate restTemplate( ) {
		return new RestTemplate( );
	}

	@Bean( name = "endpoints" )
	public List<String> endpoints( ) {
		return Arrays.asList(
				"https://crossword-conf-svc.herokuapp.com/health",
				"https://crossword-dictionary.herokuapp.com/health",
				"https://crossword-svcs-reg.herokuapp.com/health",
				"https://crossword-web-api-gw.herokuapp.com/health",
				"https://crossword-web-ui.herokuapp.com"
		);
	}
}
