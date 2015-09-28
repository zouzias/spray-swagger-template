package org.zouzias.spray.models.responses

import spray.json.DefaultJsonProtocol

case class UserPutResponse(id : Int, message: String)


object UserPutResponseProtocol extends DefaultJsonProtocol {
  implicit val userPutFormat = jsonFormat2(UserPutResponse)
}
