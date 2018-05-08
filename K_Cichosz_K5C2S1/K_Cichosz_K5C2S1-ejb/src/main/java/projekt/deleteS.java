/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
//"jdbc:derby:C:\\Users\\Kahol\\Documents\\NetBeansProjects\\K_Cichosz_K5C2S1\\db"
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.ws.WebServiceClient;

@Path("/file")
@WebServiceClient
public class deleteS {

    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String JDBC_URL = "jdbc:derby://localhost:1527/db";
    private java.sql.Connection conn;
    @POST
    @Path("/delete/{name}")
    public Response deleteFile(@PathParam("name")  String name) throws SQLException{

        String output;
        try {
            if (conn == null) {
                Connect();
            }
            delete(name);
        }catch (SQLException ex){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            output = sw.toString();
            return Response.status(200).entity(output).build();
        }
        output = "<head><meta charset=\"UTF-8\"></head> " +
                "Plik usuniety\n" +
                "<meta http-equiv=\"refresh\" content=\"2;url=/K_Cichosz_K5C2S1-web/\" />\n";
        return Response.status(200).entity(output).build();

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

    public void delete(String name) throws SQLException
    {
        try
        {
            PreparedStatement st = conn.prepareStatement("DELETE FROM APP.scripts WHERE name = ?");
            st.setString(1, name);
            st.executeUpdate(); 
            st.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}