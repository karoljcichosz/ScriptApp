/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;


import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author Kahol
 */

@Stateful

public class SBean implements SBeanRemote {
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String JDBC_URL = "jdbc:derby://localhost:1527/db";
    private java.sql.Connection conn;
    LinkedList<script> records=new LinkedList<>();
    script current;
    
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

    
    
    

    
    
    @Override
    public LinkedList<script> getList()
    {
        
        try {
        if (conn == null) {
        Connect();
        }
        records = getListL();
        }catch (SQLException ex){
        return null;
        }
        return records;
    }

    @Override
    public boolean delete(String name) 
    {      
        /*
        String output;
        try {
        if (conn == null) {
        Connect();
        }
        deleteL(name);
        }catch (SQLException ex){
        return false;
        }

        return true;*/return false;
    }
    
    
    @Override
    public boolean upload(InputStream uploadedInputStream,
            FormDataContentDisposition fileDetail,
            String description) 
    {/*
        try {
            if (conn == null) {
                Connect();
            }
            uploadL(description, fileDetail.getFileName(), uploadedInputStream);
            
        }catch (SQLException ex){
            return false;
        }
        return true;*/return false;

    }
    
    @Override
    public Response download(String name) 
    {
        /*
        byte[] file;
        String output;
        try {
            if (conn == null) {
                Connect();
            }
            file = downloadL(name);
        }catch (SQLException | IOException ex){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            output = sw.toString();
            return Response.status(404).entity(output).build();
        }


        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition",
                "attachment; filename=" + name);
        return response.build();*/ return null;

    }
    
    
    @Override
    public void getScript(String name) 
    {
        for (script s : records)
        {
            if(s.getname().equals(name))
                current=s;
        }
    }
    
      @Override
    public String getSName(String name) {
        return current.getname();
    }

    @Override
    public String getSDesc(String name) {
        return current.getdesc();
    }

    @Override
    public String getSDate(String name) {
        return current.getdate();
    }
    
    
    
    
    
    
    
    
    
        
    
    
    
    
    
    
    
    
    
    public LinkedList<script> getListL() throws SQLException 
    {
        LinkedList<script> ls=new LinkedList<>();
        try
        {
            String query="select name, date, descr from scripts";
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
                script a=new script(rs.getString("name"),rs.getString("date"),rs.getString("descr"));
                String b=rs.getString("descr");
                a.setdesc(b);
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
    
    /*
    public void deleteL(String name) throws SQLException
    {
        try
        {
            PreparedStatement st = conn.prepareStatement("DELETE FROM scripts WHERE name = '" + name + "'");
            st.executeUpdate(); 
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }


    public void uploadL(String description, String name, InputStream file) throws SQLException
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        
        PreparedStatement ps =conn.prepareStatement("INSERT INTO APP.string (name,date,data,descr) VALUES (?,?,?,?)");
        ps.setString(1, name);
        ps.setString(2, dateFormat.format(date));
        ps.setBinaryStream(3, file);
        ps.setString(4, description);
        ps.executeUpdate();
    }

    public byte[] downloadL(String name) throws SQLException, IOException
    {
        PreparedStatement ps;
        ByteArrayOutputStream out = null;
        ps = conn.prepareStatement(
                "SELECT name, data FROM APP.scripts WHERE name = '?'");
        ps.setString(1, name);
        ResultSet result = ps.executeQuery();
        if (result.next()) {
            // get filename for injecting into response header
            name = result.getString("name");

            final InputStream in = result.getBinaryStream("data");

            out = new ByteArrayOutputStream();
            int data = in.read();
            while (data >= 0) {
                out.write((char) data);
                data = in.read();
            }
            out.flush();

        }
        return out.toByteArray();
    }
*/
  

    

}
