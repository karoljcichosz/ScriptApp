/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;


import org.glassfish.jersey.media.multipart.MultiPartFeature;



import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import projekt.SBean;

//Defines the base URI for all resource URIs.
@ApplicationPath("app")
//The java class declares root resource and provider classes
public class MyApp extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet hs = new HashSet<Class<?>>();
        hs.add(MultiPartFeature.class);
        hs.add(dbS.class);
        hs.add(deleteS.class);
        hs.add(downloadS.class);
        hs.add(uploadS.class);
        




        return hs;
    }
}