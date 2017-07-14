package com.iaid.webservice.resources;

import com.iaid.webservice.dto.BloodDonation;
import com.iaid.webservice.services.BloodDonationService;
import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Crawlers on 8/30/2016.
 */
@Path("/firstAid/blood-donation")
@Produces(MediaType.APPLICATION_JSON)
public class BloodDonationResource extends BaseResource<BloodDonation>{
  final static Logger logger = Logger.getLogger(BloodDonationResource.class);
  private BloodDonationService bloodDonationService;

  public BloodDonationResource(BloodDonationService bloodDonationService) {
    this.bloodDonationService = bloodDonationService;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response add(BloodDonation bloodDonation) {
    Long id = bloodDonationService.insert(bloodDonation);
    bloodDonation.setId(id);
    return response(HttpStatus.CREATED_201, bloodDonation, "Blood Donation Request Send");
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("id") Long id, BloodDonation bloodDonation) {
    bloodDonationService.update(bloodDonation, id, false);
    return response(HttpStatus.OK_200, bloodDonation, "bloodDonation Updated");
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    bloodDonationService.delete(id, BloodDonation.class);
    return response(HttpStatus.OK_200, null, "Blood donation Deleted");
  }

  @GET
  @Path("/{id}")
  public Response get(@PathParam("id") Long id) {
    BloodDonation bloodDonation = bloodDonationService.getbyid(id, BloodDonation.class);
    if (bloodDonation == null){
      return response(HttpStatus.NO_CONTENT_204, bloodDonation, "No Bood Requirement with given id found");
    }
    return response(HttpStatus.OK_200, bloodDonation, "Blood Donation Fetched");
  }

  @GET
  public Response getAll(@QueryParam("search") String searchText, @QueryParam("offset") String offset, @QueryParam("limit") String limit) {
    List<BloodDonation> bloodDonations = bloodDonationService.getAll(searchText,null, offset, limit, BloodDonation.class);
    return responseList(HttpStatus.OK_200, bloodDonations, "Blood Donations Fetched");
  }
}
