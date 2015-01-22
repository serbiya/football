package util;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/test/resources/Property.properties"})
public abstract interface Property extends Config {

    String driverName();

    String pathToDB();

    String login();

    String passDB();

    Integer max_waiting_time();

    Integer pool_size();

}




