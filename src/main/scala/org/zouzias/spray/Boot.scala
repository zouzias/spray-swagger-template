package org.zouzias.spray

import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.util.Timeout
import org.zouzias.spray.actors.{Jupiter, Mars}
import spray.routing.SimpleRoutingApp

import scala.concurrent.duration._

object Boot extends App with SimpleRoutingApp{

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("jupiter-mars-actors")

  implicit val timeout = Timeout(5.seconds)

  // create and start our service actor
  val mars = system.actorOf(Props[Mars], "mars-actor")
  val jupiter = system.actorOf(Props[Jupiter], "jupiter-actor")

  startServer(interface = "localhost", port = 8080, serviceActorName = "jupiter-mars-actors") {
    pathPrefix("jupiter") { ctx => jupiter ! ctx } ~
      pathPrefix("mars") { ctx => mars ! ctx }
  }

}
