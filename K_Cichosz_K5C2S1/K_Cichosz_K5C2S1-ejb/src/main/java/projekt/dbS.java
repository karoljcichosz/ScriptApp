/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import javax.xml.ws.WebServiceClient;
import projekt.script;
import projekt.script;
import projekt.script;
import projekt.script;
import projekt.script;


@Path("/db")
@WebServiceClient
public class dbS {
    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String JDBC_URL = "jdbc:derby://localhost:1527/db";
    private java.sql.Connection conn;
    private LinkedList<script> records;
    @GET
    @Produces("application/json")
    public String getData() {
        try {
            if (conn == null) {
                Connect();
            }
            records = getList();
        }catch (SQLException ex){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String str = sw.toString();
            return str;
        }
        Gson gson = new Gson();
        return gson.toJson(records);
    }

    public boolean Connect() throws SQLException {
        conn = DriverManager.getConnection(JDBC_URL);
        return conn != null;
    }
    public boolean Disconnect() throws SQLException {
        if (conn == null) {
            return false;
        } else {
            conn.close();
            return true;
        }
    }

    public LinkedList<script> getList() throws SQLException 
    {
        LinkedList<script> ls=new LinkedList();
        try
        {
            String query="select * from scripts";
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
                script a=new script(rs.getString("name"),rs.getString("date"),rs.getString("descr"));
                ls.add(a);



            }


            rs.close();
            stmt.close();
            return ls;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            return null;
    
    }

}