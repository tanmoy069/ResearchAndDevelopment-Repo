package com.tanmoy.JerseyWebApiRnd.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.tanmoy.JerseyWebApiRnd.controller.JerseyWebApiController;

/**
 * Jersey web controller register configuration class.
 * Configuration by {@link ResourceConfig}.
 * 
 * @author tanmoy.tushar
 * @since Feb 28, 2022
 *
 */
@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		register(JerseyWebApiController.class);
	}
}
