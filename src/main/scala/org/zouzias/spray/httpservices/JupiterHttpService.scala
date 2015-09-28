package org.zouzias.spray.httpservices

import javax.ws.rs.Path

import com.wordnik.swagger.annotations._
import spray.routing.HttpService


@Api(value="/jupiter", description = "Operations about jupiter.", produces="text/plain", position = 2)
trait JupiterHttpService extends HttpService{

  val routes = hi ~ hello

  @Path("/hi/{name}") // Path is required for swagger scanning
  @ApiOperation(value = "Return hello from Jupiter",
    notes = "",
    response=classOf[String],
    produces="text/plain; charset=UTF-8",
    nickname = "jupiter",
    httpMethod = "GET")
  @ApiImplicitParams(Array(
    new ApiImplicitParam( name = "name",
      value = "Name of astronaut",
      required = true,
      dataType = "string",
      paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Jupiter cannot say hello.")
  ))
  def hi = {
    get {
      path("hi" / Segment) { name =>
        complete("Hi " + name + " from Jupiter!")
      }
    }
  }

  @Path("/hello") // Path is required for swagger scanning
  @ApiOperation(value = "Return hello from Jupiter again",
    notes = "",
    response=classOf[String],
    produces="text/plain; charset=UTF-8",
    nickname = "jupiter",
    httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Jupiter cannot say hello.")
  ))
  def hello = {
    get{
      path("hello") {
        complete("Hello anonymous from Jupiter!")
      }
    }
  }
}
