package za.co.hbrown.config;

import za.co.hbrown.boundary.PersonResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA by henryb.
 *
 * @author <a href="mailto:henry.g.brown@gmail.com">henryb</a>
 * @version 0.1201502201030
 * @since 0.1201502201030
 */
public class RestApplication extends Application {
    private Set<Class<?>> services;

    public RestApplication() {
        services = new HashSet<Class<?>>();
        services.add(PersonResource.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }

}
