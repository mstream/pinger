package io.mstream.pinger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class EndpointsService implements EndpointsSupplier {

	private final List<String> endpoints;

	@Autowired
	public EndpointsService( @Value( "#{endpoints}" ) List<String> endpoints ) {
		this.endpoints = Collections.unmodifiableList( endpoints );
	}

	@Override public List<String> get( ) {
		return endpoints;
	}
}
