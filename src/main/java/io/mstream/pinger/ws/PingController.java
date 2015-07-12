package io.mstream.pinger.ws;

import io.mstream.pinger.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@RequestMapping( "/ping" )
public class PingController {

	private static final Logger LOGGER =
			Logger.getLogger( PingController.class.getName( ) );

	private final PingService pingService;

	@Autowired
	public PingController( PingService pingService ) {
		this.pingService = pingService;
	}

	@RequestMapping( method = GET )
	public ResponseEntity ping( ) {
		try {
			pingService.sendPings( );
		} catch ( Exception e ) {
			LOGGER.log( SEVERE, "Ping failed.", e );
			return ResponseEntity
					.status( INTERNAL_SERVER_ERROR )
					.build( );
		}
		return ResponseEntity
				.ok( )
				.build( );
	}
}

