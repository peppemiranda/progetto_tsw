package model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConPool {
	
    private static DataSource datasource;

    public static Connection getConnection() throws SQLException {
        if (datasource == null) {
            try {
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                
                datasource = (DataSource) envCtx.lookup("jdbc/PianetaCalcioDB"); 
                
            } catch (NamingException e) {
                throw new SQLException("Errore fatale: impossibile caricare il Connection Pool dal context.xml", e);
            }
        }
        return datasource.getConnection();	
    }
}
