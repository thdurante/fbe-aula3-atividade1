package br.ufg.inf.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    public Connection getConnection() {
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/funcionarios";
            String usr = "postgres";
            String pswrd = "postgres";
            return DriverManager.getConnection(url, usr, pswrd);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
