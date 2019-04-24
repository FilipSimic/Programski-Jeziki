package si.um.feri.database;

import Vaja1.Helper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBHelper {
    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource()
    {
        if (dataSource == null)
        {
            JsonObject info = new JsonParser().parse(Helper.read("info.json")).getAsJsonObject();
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(info.get("url").getAsString());
            ds.setUsername(info.get("username").getAsString());
            ds.setPassword(info.get("password").getAsString());

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

            dataSource = ds;
        }
        return dataSource;
    }
}
