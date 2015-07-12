package io.mstream.pinger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


@Component
public class PingService {

	private static final Logger LOGGER =
			Logger.getLogger( PingService.class.getName( ) );

	private final EndpointsSupplier endpointsSupplier;
	private final RestTemplate restTemplate;

	@Autowired
	public PingService( EndpointsSupplier endpointsSupplier,
			RestTemplate restTemplate ) {
		this.endpointsSupplier = endpointsSupplier;
		this.restTemplate = restTemplate;
	}

	public void sendPings( ) {
		for ( String endpoint : endpointsSupplier.get( ) ) {
			ResponseEntity resp = restTemplate.getForEntity(
					endpoint,
					String.class );
			LOGGER.info( String.format(
					"Ping sent: %s %s",
					endpoint,
					resp.getStatusCode( )
			) );
		}
	}
}
