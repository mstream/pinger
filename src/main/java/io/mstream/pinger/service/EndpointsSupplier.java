package io.mstream.pinger.service;

import com.google.appengine.repackaged.com.google.common.base.Supplier;

import java.util.List;


public interface EndpointsSupplier extends Supplier<List<String>> {
}
