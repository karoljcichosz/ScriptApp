/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceClient;
import org.apache.commons.io.FileUtils;

@Path("/file")
@WebServiceClient
public class downloadS {

    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String JDBC_URL = "jdbc:derby://localhost:1527/db";
    private java.sql.Connection conn;
    private String name;
    protected String filename;
                                                    //tutaj ogarnac jak to ma dzialac
    @GET
    @Path("/download/{name}/")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("name")  String name) throws IOException, SQLException{
        byte[] file;
        String output;
        try {
            if (conn == null) {
                Connect();
            }
            file = download(name);
        }
        //catch (SQLException | IOException ex)
        catch (Exception ex)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            output = sw.toString();
            return Response.status(404).entity(output).build();
        }
        return Response
            .ok(file, MediaType.APPLICATION_OCTET_STREAM)
            .header("content-disposition","attachment; filename = "+filename)
            .build();



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

    public byte[] download(String name) throws SQLException, IOException{
        PreparedStatement ps;
        ByteArrayOutputStream out = null;
        ps = conn.prepareStatement(
                "SELECT name, data FROM APP.scripts WHERE name = ?");
        ps.setString(1, name);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
            // get filename for injecting into response header
            filename = result.getString("name");
            System.out.println(filename);System.out.println(filename);System.out.println(filename);
            final InputStream in = result.getBlob("data").getBinaryStream();

            out = new ByteArrayOutputStream();
            int data = in.read();
            while (data >= 0) {
                out.write((char) data);
                data = in.read();
            }
            out.flush();

        }
        ps.close();
        result.close();
        return out.toByteArray();
    }

}