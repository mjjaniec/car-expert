package pl.edu.agh.iiet.se.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApp extends ResourceConfig {
    public JerseyApp() {
        register(JacksonFeature.class);
    }
}
