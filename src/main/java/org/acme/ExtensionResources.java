package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/extension")
public class ExtensionResources {

  @GET
  public String demo() {

    return "ECHO";
  }
}
