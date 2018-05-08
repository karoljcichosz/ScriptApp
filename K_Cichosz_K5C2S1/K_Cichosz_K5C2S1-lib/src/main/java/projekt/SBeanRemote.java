/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.io.InputStream;
import java.util.LinkedList;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author Kahol
 */
@Remote
public interface SBeanRemote {

    LinkedList<script> getList();

    boolean delete(String name);

    boolean upload(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, String descriptio);

    Response download(String name);

    void getScript(String name);

    String getSName(String name);

    String getSDesc(String name);

    String getSDate(String name);



    
}
