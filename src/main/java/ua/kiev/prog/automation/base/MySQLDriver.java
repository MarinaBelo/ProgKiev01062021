package ua.kiev.prog.automation.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLDriver {
    //нужно найти коннектор в репозитории
    final private Connection _mysqlConn;

    public MySQLDriver (){
        String url = String.format("jdbc:mysql://%s:%s/%s", Config.MYSQL_HOST, Config.MYSQL_PORT, Config.MYSQL_DB);

        try {
            this._mysqlConn = DriverManager.getConnection(url, Config.MYSQL_USER.value, Config.MYSQL_PASSWD.value);
        }catch (SQLException e){
            throw  new RuntimeException("Could not connect to MySQL server " + url, e);
        }

    }

    final public boolean execute (String query){                    //
        try{
            return _mysqlConn.createStatement().execute(query);
        }catch (SQLException e){
            throw new RuntimeException("Could nit execute SQL query " + query, e);
        }
    }

    final public ResultSet executeQuery (String query) {
        try{
            return _mysqlConn.createStatement().executeQuery(query);
        }catch (SQLException e){
            throw new RuntimeException("Could nit execute SQL query " + query, e);
        }
    }

    final public void close  () {
        try{
            this. _mysqlConn.close();
        }catch (SQLException e){/*Ignore*/}
    }




}
