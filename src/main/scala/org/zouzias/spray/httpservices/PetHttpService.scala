package org.zouzias.spray.httpservices

import javax.ws.rs.Path

import com.wordnik.swagger.annotations._
import org.zouzias.spray.models.responses.{DeleteResponse, DeleteResponseProtocol, UpdateResponse}
import org.zouzias.spray.models.Pet
import spray.routing.HttpService

import org.zouzias.spray.models.PetProtocol._
import DeleteResponseProtocol._
import org.zouzias.spray.models.responses.UpdateResponseProtocol._
import spray.httpx.SprayJsonSupport._


// These two are required for marshalling and unmarshalling case classes
import spray.httpx.unmarshalling._
import spray.httpx.marshalling._



@Api(value = "/pet", description = "Operations about pets.", position = 0)
trait PetHttpService extends HttpService {


  val routes = readRoute ~ updateRoute ~ deleteRoute ~ addRoute ~ searchRoute ~ findByTags ~ readRouteForNestedResource


  // Path is required for swagger to read the correct route
  @Path("/id/{petId}")
  @ApiOperation(value = "Find a pet by ID", notes = "Returns a pet based on ID", httpMethod = "GET", response = classOf[Pet])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "petId", value = "ID of pet that needs to be fetched", required = true, dataType = "integer", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Pet not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied")
  ))
  def readRoute = get {
    path("id" / IntNumber) { id =>
      complete(Pet(id, "Tassos"))
    }
  }

  @Path("/update/{petId}")
  @ApiOperation(value = "Updates a pet in the store with form data.", notes = "", nickname = "updatePetWithForm", httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "petId", value = "ID of pet that needs to be updated", required = true, dataType = "string", paramType = "path"),
    new ApiImplicitParam(name = "name", value = "Updated name of the pet.", required = false, dataType = "string", paramType = "form"),
    new ApiImplicitParam(name = "status", value = "Updated status of the pet.", required = false, dataType = "string", paramType = "form")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Dictionary does not exist."),
    new ApiResponse(code = 200, message = "Added new pet.", response = classOf[String])
  ))
  def updateRoute = post {
    path("update" / Segment) { id => {
      formFields('name, 'status) { (name, status) =>
        complete(UpdateResponse(name, status, "All good"))
      }
    }
    }
  }

  @Path("/delete/{petId}")
  @ApiOperation(value = "Deletes a pet", nickname = "deletePet", httpMethod = "DELETE", produces="application/json")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "petId", value = "Pet id to delete", required = true, dataType = "integer", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid pet value")
  ))
  def deleteRoute = delete {
      path("delete" / IntNumber) {
        id => { ctx => ctx.complete(DeleteResponse(id, "Successfully deleted..."))
        }
      }
  }

  @Path("/new")
  @ApiOperation(value = "Add a new pet to the store", nickname = "addPet", httpMethod = "POST", consumes="application/json", produces="application/json")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "Pet object that needs to be added to the store", dataType = "application/json", required = true, paramType = "body")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 405, message = "Invalid input"),
    new ApiResponse(code = 200, message = "All good")
  ))
  def addRoute = post {
    path("new") {
      entity(as[Pet]) { pet =>
        complete(pet)
      }
    }
  }

  @Path("/search")
  @ApiOperation(value = "Searches for a pet", nickname = "searchPet", httpMethod = "GET", produces = "application/json, application/xml")
  def searchRoute = get {
    path("search") {
      complete(Pet(1, "Costas"))
    }
  }

  @Path("/findByTags")
  @ApiOperation(value = "Find Pets by Tags", httpMethod = "GET", nickname = "findPetsByTags")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "petId", value = "Tags to filter by", required = true, dataType = "string", paramType = "query", allowMultiple = true)
  ))
  def findByTags = get {
    path("findByTags") { parameters("petId")
      { petId =>
        complete(Pet(petId.toInt, "First of DB"))
      }
    }
  }

  @Path("/friends/{petId}/{friendId}")
  @ApiOperation(value = "Find Pet's friend by friendId", httpMethod = "GET", nickname = "findPetsFriendById", response = classOf[Pet])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "petId", value = "Pet id of the pet whose friend needs to be fetched", required = true, dataType = "string", paramType = "path"),
    new ApiImplicitParam(name = "friendId", value = "Id of the friend that needs to be fetched", required = true, dataType = "string", paramType = "path")
  ))
  def readRouteForNestedResource = get {
    path("friends" / Segment / Segment) {
      (petId, friendId) => {
        complete(Pet(2, "Soulis (Costas's friend)"))
      }
    }
  }
}