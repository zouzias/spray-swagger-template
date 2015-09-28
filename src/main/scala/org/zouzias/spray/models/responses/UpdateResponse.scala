package org.zouzias.spray.models.responses

import spray.json.DefaultJsonProtocol

/**
 * Update Response model
 */
case class UpdateResponse(name : String, status : String, message: String)


object UpdateResponseProtocol extends DefaultJsonProtocol {
  implicit val updateFormat = jsonFormat3(UpdateResponse)
}