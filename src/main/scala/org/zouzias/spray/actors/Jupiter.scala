package org.zouzias.spray.actors

import akka.actor.ActorLogging
import spray.routing.HttpServiceActor


class Jupiter extends HttpServiceActor with ActorLogging{

  def receive = runRoute {
    (get & path("hi")) { complete("Hi from Jupiter!") }
  }

}