package io.mstream.pinger;

import io.mstream.pinger.service.EndpointsSupplier;
import io.mstream.pinger.service.PingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;


public class PingServiceTest {

	private PingService instance;

	@Mock
	private EndpointsSupplier endpointsSupplier;

	@Mock
	private RestTemplate restTemplate;

	@Before
	public void setUp( ) {
		MockitoAnnotations.initMocks( this );
		this.instance = new PingService(
				endpointsSupplier,
				restTemplate
		);
	}

	@Test
	public void shouldMakeHttpCallsForSpecifiedEndpoints( ) {
		doReturn( Arrays.asList(
						"endpoint1",
						"endpoint2" )
		).when( endpointsSupplier ).get( );
		doReturn( ResponseEntity.ok( ).build( ) )
				.when( restTemplate )
				.getForEntity(
						any( String.class ),
						any( Class.class ) );
		//
		instance.sendPings( );
		//
		ArgumentCaptor<String> uriCaptor =
				ArgumentCaptor.forClass( String.class );
		verify( restTemplate, times( 2 ) ).getForEntity(
				uriCaptor.capture( ),
				any( Class.class ) );
		List<String> capturedUris = uriCaptor.getAllValues( );
		assertFalse(
				"should make http calls for endpoints",
				capturedUris.isEmpty( ) );
		assertEquals(
				"should make http calls for all endpoints",
				2,
				capturedUris.size( )
		);
		assertEquals(
				"should make http calls for proper endpoints",
				"endpoint1",
				capturedUris.get( 0 ) );
		assertEquals(
				"should make http calls for proper endpoints",
				"endpoint2",
				capturedUris.get( 1 ) );
	}

}