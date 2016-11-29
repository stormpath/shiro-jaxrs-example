package com.stormpath.shiro.samples.jaxrs.resources;

import com.stormpath.shiro.samples.jaxrs.dao.StormtrooperDao;
import com.stormpath.shiro.samples.jaxrs.model.Stormtrooper;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;

@Path("/troopers")
@Produces("application/json")
public class StormtroooperResource {

    private final StormtrooperDao trooperDao;

    public StormtroooperResource(StormtrooperDao trooperDao) {
        this.trooperDao = trooperDao;
    }

    @GET
    @RequiresPermissions("troopers:read")
    public Collection<Stormtrooper> listTroopers() {
        return trooperDao.listStormtroopers();
    }

    @Path("/{id}")
    @GET
    @RequiresPermissions("troopers:read")
    public Stormtrooper getTrooper(@PathParam("id") String id) throws NotFoundException {

        Stormtrooper stormtrooper = trooperDao.getStormtrooper(id);
        if (stormtrooper == null) {
            throw new NotFoundException();
        }
        return stormtrooper;
    }

    @POST
    @RequiresPermissions("troopers:create")
    public Stormtrooper createTrooper(Stormtrooper trooper) {

        return trooperDao.addStormtrooper(trooper);
    }

    @Path("/{id}")
    @POST
    @RequiresPermissions("troopers:update")
    public Stormtrooper updateTrooper(@PathParam("id") String id, Stormtrooper updatedTrooper) throws NotFoundException {

        return trooperDao.updateStormtrooper(id, updatedTrooper);
    }

    @Path("/{id}")
    @DELETE
    @RequiresPermissions("troopers:delete")
    public void deleteTrooper(@PathParam("id") String id) {
        trooperDao.deleteStormtrooper(id);
    }
}
