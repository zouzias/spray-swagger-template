package org.zouzias.spray.actors

import akka.actor.{ActorRefFactory, ActorLogging}
import com.wordnik.swagger.annotations._
import org.zouzias.spray.httpservices.JupiterHttpService
import spray.routing.{HttpService, Route, HttpServiceActor}

class JupiterActor extends HttpServiceActor with ActorLogging{

  lazy val service = new JupiterHttpService{
    override implicit def actorRefFactory: ActorRefFactory = context
  }

  def receive = runRoute(service.routes)
}