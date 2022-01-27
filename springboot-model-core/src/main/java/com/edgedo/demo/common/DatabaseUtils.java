package com.edgedo.demo.common;

import com.edgedo.demo.exception.DatabaseException;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author:Qiutianzhu
 * @Description: 数据库连接工具
 * @Date: 2022/1/27 17:16
 **/
public class DatabaseUtils {

    public static String connectUrl(String type, String host) {
        return connectUrl(type, host, 0, null, null, null, null);
    }

    public static String connectUrl(String type, String host, String port) {
        try {
            Integer intPort = Integer.parseInt(port);
            return connectUrl(type, host, intPort, null, null, null, null);
        } catch (Exception e) {
            return connectUrl(type, host, 0, null, null, null, null);
        }
    }

    public static String connectUrl(String type, String host, String port, String service, String database, String username, String password) {
        try {
            Integer intPort = Integer.parseInt(port);
            return connectUrl(type, host, intPort, service, database, username, password);
        } catch (Exception e) {
            return connectUrl(type, host, 0, service, database, username, password);
        }
    }

    /**
     * @param type     类型
     * @param host     host
     * @param port     port
     * @param service  Oracle的服务名称 || Mongodb的认证库AuthDatabase;
     * @param database 库名称
     * @param username username
     * @param password password
     * @return 连接URL
     */
    public static String connectUrl(String type, String host, Integer port, String service, String database, String username, String password) {
        String result;
        switch (type) {
            case "Neo4j":
                result = connectToNeo4j(host, port);
                break;
            case "MongoDB":
                result = connectToMongodb(host, port, database, username, password, service);
                break;
            case "MySQL":
                result = connectToMySql(host, port, database, username, password);
                break;
            case "Oracle":
                result = connectToOracle(host, port, service, username, password);
                break;
            case "MSSQL":
                result = connectToMicrosoftSqlServer(host, port, database, username, password);
                break;
            case "MariaDB":
                result = connectToMariaDb(host, port, database, username, password);
                break;
            default:
                return null;
        }
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        return result;
    }

    public static String jdbcClass(String type) {
        String classString;
        switch (type) {
            case "MySQL":
                classString = "org.mariadb.jdbc.Driver";
                break;
            case "Oracle":
                classString = "oracle.jdbc.driver.OracleDriver";
                break;
            case "MSSQL":
                classString = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                break;
            case "MariaDB":
                classString = "org.mariadb.jdbc.Driver";
                break;
            default:
                throw new DatabaseException("数据源类型未指定");
        }
        return classString;
    }

    public static String connectToMongodb(String host, Integer port, String database, String username, String password, String authDatabase) {
        String connectFormat = "mongodb://%s:%s/%s";
        String connectFormatWithAuth = "mongodb://%s:%s@%s:%s/%s?authsource=%s";
        host = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
        port = port == 0 ? 27017 : port;
        database = StringUtils.isEmpty(database) ? "test" : database;
        authDatabase = StringUtils.isEmpty(authDatabase) ? "admin" : authDatabase;
        if (StringUtils.isEmpty(password)) {
            return String.format(connectFormat, host, port, database);
        } else {
            return String.format(connectFormatWithAuth, username, password, host, port, database, authDatabase);
        }
    }

    public static String connectToReplicaMongodb(List<String> hostList, String database, String username, String password, String authDatabase) {
        String connectFormat = "mongodb://%s:%s/%s";
        String connectFormatWithAuth = "mongodb://%s:%s@%s/%s?authsource=%s";
        String host = hostList.isEmpty() ? "127.0.0.1:0" : String.join(",", hostList);
        database = StringUtils.isEmpty(database) ? "test" : database;
        authDatabase = StringUtils.isEmpty(authDatabase) ? "admin" : authDatabase;
        if (StringUtils.isEmpty(password)) {
            return String.format(connectFormat, host, database);
        } else {
            return String.format(connectFormatWithAuth, username, password, host, database, authDatabase);
        }
    }

    private static String connectToNeo4j(String host, Integer port) {
        String connectFormat = "bolt://%s:%s";
        host = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
        port = port == 0 ? 7687 : port;
        return String.format(connectFormat, host, port);
    }

    private static String connectToOracle(String host, Integer port, String serviceName, String username, String password) {
        String connectFormat = "jdbc:oracle:thin:@%s:%s/%s";
        String connectFormatWithAuth = "jdbc:oracle:thin:%s/%s@%s:%s/%s";
        host = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
        port = port == 0 ? 1521 : port;
        serviceName = StringUtils.isEmpty(serviceName) ? "orcl" : serviceName;
        if (StringUtils.isEmpty(password)) {
            return String.format(connectFormat, host, port, serviceName);
        } else {
            return String.format(connectFormatWithAuth, username, password, host, port, serviceName);
        }
    }

    private static String connectToMicrosoftSqlServer(String host, Integer port, String database, String username, String password) {
        String connectFormat = "jdbc:microsoft:sqlserver://%s:%s;DatabaseName=%s";
        String connectFormatWithAuth = "jdbc:microsoft:sqlserver://%s:%s;DatabaseName=%s;user=%s;password=%s";
        host = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
        port = port == 0 ? 1433 : port;
        database = StringUtils.isEmpty(database) ? "master" : database;
        if (StringUtils.isEmpty(password)) {
            return String.format(connectFormat, host, port, database);
        } else {
            return String.format(connectFormatWithAuth, host, port, database, username, password);
        }
    }

    private static String connectToMariaDb(String host, Integer port, String database, String username, String password) {
        String connectFormat = "jdbc:mariadb://%s:%s/%s";
        String connectFormatWithAuth = "jdbc:mariadb://%s:%s/%s?user=%s&password=%s";
        host = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
        port = port == 0 ? 3306 : port;
        database = StringUtils.isEmpty(database) ? "test" : database;
        if (StringUtils.isEmpty(password)) {
            return String.format(connectFormat, host, port, database);
        } else {
            return String.format(connectFormatWithAuth, host, port, database, username, password);
        }
    }

    private static String connectToMySql(String host, Integer port, String database, String username, String password) {
        String connectFormat = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";
        String connectFormatWithAuth = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";
        host = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
        port = port == 0 ? 3306 : port;
        database = StringUtils.isEmpty(database) ? "test" : database;
        if (StringUtils.isEmpty(password)) {
            return String.format(connectFormat, host, port, database);
        } else {
            return String.format(connectFormatWithAuth, host, port, database, username, password);
        }
    }

    private static String connectToOracleMySql(String host, Integer port, String database, String username, String password) {
        String connectFormat = "jdbc:mysql://%s:%s/%s";
        String connectFormatWithAuth = "jdbc:mysql://%s:@s@%s:%s/%s";
        host = StringUtils.isEmpty(host) ? "127.0.0.1" : host;
        port = port == 0 ? 3306 : port;
        database = StringUtils.isEmpty(database) ? "test" : database;

        if (StringUtils.isEmpty(password)) {
            return String.format(connectFormat, host, port, database);
        } else {
            return String.format(connectFormatWithAuth, username, password, host, port, database);
        }
    }
}
