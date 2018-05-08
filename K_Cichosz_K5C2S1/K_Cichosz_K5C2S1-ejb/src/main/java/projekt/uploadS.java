package projekt;


import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.xml.ws.WebServiceClient;


@Path("/file")
@WebServiceClient
public class uploadS {

    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String JDBC_URL = "jdbc:derby://localhost:1527/db";
    private static java.sql.Connection conn;



    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("description") String description) throws SQLException, URISyntaxException{

        String output;
        try {
            if (conn == null) {
                Connect();
            }
            upload(description, fileDetail.getFileName(), uploadedInputStream);
            
        }catch (SQLException ex){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String output2 = sw.toString();
            output = "<head><meta charset=\"UTF-8\"></head> " +
                "SQLException:\n" + output2 +
                "<meta http-equiv=\"refresh\" content=\"40;url=/K_Cichosz_K5C2S1-web/\" />\n";
            return Response.status(200).entity(output).build();
        }
        output = "<head><meta charset=\"UTF-8\"></head> " +
                "Plik dodany\n" +
                "<meta http-equiv=\"refresh\" content=\"2;url=/K_Cichosz_K5C2S1-web/\" />\n";
        return Response.status(200).entity(output).build();

    }


    public static boolean Connect() throws SQLException {
        conn = DriverManager.getConnection(JDBC_URL);
        return conn != null;
    }
    public static boolean Disconnect() throws SQLException {
        if (conn == null) {
            return false;
        } else {
            conn.close();
            return true;
        }
    }

    public static void upload(String description, String name, InputStream file) throws SQLException
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        PreparedStatement ps =conn.prepareStatement("INSERT INTO APP.scripts (name,date,data,descr) VALUES (?,?,?,?)");
        ps.setString(1, name);
        ps.setString(2, dateFormat.format(date));
        ps.setBlob(3, file);
        ps.setString(4, description);   
        ps.executeUpdate();
        ps.close();
    }

}