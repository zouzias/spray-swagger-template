package org.zouzias.spray.httpservices

import javax.ws.rs.Path

import com.wordnik.swagger.annotations._
import spray.routing.HttpService

import org.zouzias.spray.models.User
import org.zouzias.spray.models.UserProtocol._

import org.zouzias.spray.models.responses.UserPutResponse
import org.zouzias.spray.models.responses.UserPutResponseProtocol._
import spray.httpx.SprayJsonSupport._



@Api(value = "/user", description = "Operations about users.", position=3)
trait UserHttpService extends HttpService {

  val routes = readRoute ~ getUser

  @Path("/{username}")
  @ApiOperation(value = "Updated user",
    notes = "This can only be done by the logged in user.",
    nickname = "updateUser",
    httpMethod = "PUT")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "username", value = "ID of pet that needs to be updated", required = true, dataType = "string", paramType = "path"),
    new ApiImplicitParam(name = "body", value = "Updated user object.", required = false, dataType = "string", paramType = "form")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "User not found"),
    new ApiResponse(code = 400, message = "Invalid username supplied")
  ))
  def readRoute = put { path(IntNumber) { id =>
    complete(UserPutResponse(id, "OK"))
  }}

  @Path("/{petId}")
  @ApiOperation(value = "Get user by name",
    notes = "",
    response=classOf[User],
    nickname = "getUserByName",
    httpMethod = "GET")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "petId", value = "ID of pet that needs to be updated", required = true, dataType = "string", paramType = "path"),
    new ApiImplicitParam(name = "name", value = "Updated name of the pet.", required = false, dataType = "string", paramType = "form"),
    new ApiImplicitParam(name = "status", value = "Updated status of the pet.", required = false, dataType = "string", paramType = "form")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "User does not exist.")
  ))
  def getUser = post { path(Segment) { id => { formFields('name, 'status) { (name, status) =>
    complete(s"Posted $name, $status")
  }}}}

}
