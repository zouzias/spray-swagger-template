package org.zouzias.spray.models.responses

import spray.json.DefaultJsonProtocol

/**
 * Delete Response model
 */
case class DeleteResponse(id : Int, message : String)


object DeleteResponseProtocol extends DefaultJsonProtocol {
  implicit val deleteResponseFormat = jsonFormat2(DeleteResponse)
}