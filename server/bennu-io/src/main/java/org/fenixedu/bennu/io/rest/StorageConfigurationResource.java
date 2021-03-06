package org.fenixedu.bennu.io.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fenixedu.bennu.core.rest.BennuRestResource;
import org.fenixedu.bennu.io.domain.FileStorageConfiguration;
import org.fenixedu.bennu.io.domain.FileSupport;

import pt.ist.fenixframework.Atomic;

@Path("/bennu-io/storage/config")
public class StorageConfigurationResource extends BennuRestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String all() {
        accessControl("#managers");
        createMissingConfigurations();
        return view(FileSupport.getInstance().getConfigurationSet(), "storageConfigurations");
    }

    @Atomic
    private void createMissingConfigurations() {
        FileStorageConfiguration.createMissingStorageConfigurations();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(String json) {
        accessControl("#managers");
        create(json, FileStorageConfiguration.class);
        return all();
    }

}
