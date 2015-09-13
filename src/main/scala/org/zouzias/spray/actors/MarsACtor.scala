package org.zouzias.spray.actors

import akka.actor.{ActorRefFactory, ActorLogging}
import spray.routing.HttpServiceActor


/**
 * Handle all REST queries
 */
class MarsACtor extends HttpServiceActor with ActorLogging{

  lazy val service = new MarsHttpService{
    override implicit def actorRefFactory: ActorRefFactory = context
  }

  def receive = runRoute(service.routes)

}
