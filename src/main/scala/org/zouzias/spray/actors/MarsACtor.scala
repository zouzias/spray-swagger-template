package org.zouzias.spray.actors

import akka.actor.{ActorRefFactory, ActorLogging}
import org.zouzias.spray.httpservices.MarsHttpService
import spray.routing.HttpServiceActor


class MarsACtor extends HttpServiceActor with ActorLogging{

  lazy val service = new MarsHttpService{
    override implicit def actorRefFactory: ActorRefFactory = context
  }

  def receive = runRoute(service.routes)
}
