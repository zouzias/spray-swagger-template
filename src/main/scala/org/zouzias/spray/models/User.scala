package org.zouzias.spray.models

import spray.json.DefaultJsonProtocol


case class User(username: String, status: String)

object UserProtocol extends DefaultJsonProtocol {
  implicit val userFormat = jsonFormat2(User)
}
