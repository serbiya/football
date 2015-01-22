package util;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("file:src/main/resources/Property.properties")
public interface Property extends Config {

    String driverName();

    String pathToDB();

    String login();

    String passDB();

    int max_waiting_time();

    int pool_size();

}




