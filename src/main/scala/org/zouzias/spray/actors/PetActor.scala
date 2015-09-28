package org.zouzias.spray.actors

import akka.actor.{ActorRefFactory, ActorLogging}
import org.zouzias.spray.httpservices.PetHttpService
import spray.routing.HttpServiceActor

class PetActor extends HttpServiceActor with ActorLogging{

  lazy val service = new PetHttpService {
    override implicit def actorRefFactory: ActorRefFactory = context
  }

  def receive = runRoute(service.routes)
}
