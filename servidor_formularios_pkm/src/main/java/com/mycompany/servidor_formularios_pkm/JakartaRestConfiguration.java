package com.mycompany.servidor_formularios_pkm;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("api/v1")
public class JakartaRestConfiguration extends ResourceConfig{

    public JakartaRestConfiguration() {
        packages("com.mycompany.servidor_formularios_pkm.controllers");
        packages("com.mycompany.servidor_formularios_pkm.excepciones");
    }
    
}
