package org.zouzias.spray.actors

import akka.actor.{ActorRefFactory, ActorLogging}
import org.zouzias.spray.httpservices.{UserHttpService}
import spray.routing.HttpServiceActor


class UserActor extends HttpServiceActor with ActorLogging{

  lazy val service = new UserHttpService {
    override implicit def actorRefFactory: ActorRefFactory = context
  }

  def receive = runRoute(service.routes)
}