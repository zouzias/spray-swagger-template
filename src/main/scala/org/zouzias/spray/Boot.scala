package org.zouzias.spray

import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.util.Timeout
import org.zouzias.spray.actors.swagger.SwaggerActor
import org.zouzias.spray.actors.{JupiterActor, MarsACtor}
import spray.routing.SimpleRoutingApp

import scala.concurrent.duration._

object Boot extends App with SimpleRoutingApp{

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("swagger-service")

  implicit val timeout = Timeout(5.seconds)

  // create and start our service actor
  val mars = system.actorOf(Props[MarsACtor], "mars-actor")
  val jupiter = system.actorOf(Props[JupiterActor], "jupiter-actor")
  val swagger = system.actorOf(Props[SwaggerActor], "swagger-actor")

  startServer(interface = "localhost", port = 8080) {
      pathPrefix("jupiter") { ctx => jupiter ! ctx } ~
      pathPrefix("mars") { ctx => mars ! ctx } ~
      { ctx => swagger ! ctx }
  }

}
