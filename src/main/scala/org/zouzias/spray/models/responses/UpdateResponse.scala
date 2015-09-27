package org.zouzias.spray.models.responses

import spray.json.DefaultJsonProtocol

/**
 * Created by zouzias on 26/09/15.
 */
case class UpdateResponse(name : String, status : String, message: String)


object UpdateResponseProtocol extends DefaultJsonProtocol {
  implicit val updateFormat = jsonFormat3(UpdateResponse)
}