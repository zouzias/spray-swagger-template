package org.zouzias.spray

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import org.zouzias.spray.actors.swagger.SwaggerActor
import org.zouzias.spray.actors.{UserActor, PetActor, JupiterActor, MarsACtor}
import spray.routing.SimpleRoutingApp

import scala.concurrent.duration._

object Boot extends App with SimpleRoutingApp{

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("swagger-service")

  implicit val timeout = Timeout(5.seconds)

  // create and start our service actor
  val mars = system.actorOf(Props[MarsACtor], "mars-actor")
  val jupiter = system.actorOf(Props[JupiterActor], "jupiter-actor")

  val pet = system.actorOf(Props[PetActor], "pet-actor")
  val user = system.actorOf(Props[UserActor], "user-actor")

  // Actor for handling Swagger
  val swagger = system.actorOf(Props[SwaggerActor], "swagger-actor")

  startServer(interface = "localhost", port = 8080) {
    pathPrefix("jupiter") { ctx => jupiter ! ctx } ~
      pathPrefix("mars") { ctx => mars ! ctx } ~
      pathPrefix("pet") {ctx => pet ! ctx} ~
      pathPrefix("user") {ctx => user ! ctx} ~
      { ctx => swagger ! ctx } /* Last is the swagger API actor*/
  }

}
