package org.zouzias.spray.actors

import com.wordnik.swagger.annotations._
import spray.routing.HttpService



@Api(value="/mars/hello", description = "Operations about Mars.", produces="text/plain", position = 1)
trait MarsHttpService extends HttpService{

  val routes = hi ~ hello

  @ApiOperation(value = "Return hello someone from Mars", notes = "", response=classOf[String], produces="text/plain; charset=UTF-8", nickname = "hiJupiter", httpMethod = "GET")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "name", value = "Name of astronaut", required = true, dataType = "string", paramType = "path")
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

  @ApiOperation(value = "Return hello from Mars again", notes = "", response=classOf[String], produces="text/plain; charset=UTF-8", nickname = "helloJupiter", httpMethod = "GET")
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

