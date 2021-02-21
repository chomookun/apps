package net.chomookun.apps.sdk.core;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static ThreadLocal<String> currentKey = new ThreadLocal<String>();

    /**
     * sets current key
     * 
     * @param key
     */
    public static void setCurrentKey(String key) {
        currentKey.set(key);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return currentKey.get();
    }

    /**
     * closes element dataSource
     */
    public void close() {
        log.info("RoutingDataSource.close()");
        for (DataSource dataSource : getResolvedDataSources().values()) {
            try {
                log.info("RoutingDataSource.dataSource[{}] close.", dataSource);
                ((HikariDataSource) dataSource).close();
            } catch (Exception ignore) {
                log.warn(ignore.getMessage());
            }
        }
    }

}
