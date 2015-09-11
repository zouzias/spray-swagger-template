package org.zouzias.spray.actors

import akka.actor.ActorLogging
import spray.routing.HttpServiceActor


/**
 * Handle all REST queries
 */
class Mars extends HttpServiceActor with ActorLogging{

  def receive = runRoute {
    (get & path("hi")) { complete("Hi from Mars!") }
  }

}
