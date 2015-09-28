package org.zouzias.spray.httpservices

import javax.ws.rs.Path

import com.wordnik.swagger.annotations._
import spray.routing.HttpService


/**
 * A simple REST API endpoint
 *
 */
@Api(value="/mars", description = "Operations about Mars.", position = 1)
trait MarsHttpService extends HttpService{

  val routes = hello ~ hi

  @Path("/hello/{name}") // Path is required for swagger scanning
  @ApiOperation(value = "Return hello someone from Mars",
                notes = "",
                response=classOf[String],
                produces="text/plain; charset=UTF-8",
                nickname = "mars",
                httpMethod = "GET")
  @ApiImplicitParams(Array(
    new ApiImplicitParam( name = "name",
                          value = "Name of astronaut",
                          required = true,
                          dataType = "string",
                          paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Mars cannot say hello.")
  ))
  def hi = {
    get {
      path("hello" / Segment) { name =>
        complete("Hi " + name + " from Mars!")
      }
    }
  }

  @Path("/hello")   // Path is required for swagger scanning
  @ApiOperation(value = "Return hello from Mars again",
                notes = "No notes here",
                response=classOf[String],
                produces="text/plain; charset=UTF-8",
                nickname = "mars",
                httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Mars cannot say hello.")
  ))
  def hello = {
    get{
      path("hello") {
        complete("Hello anonymous from Mars!")
      }
    }
  }
}

