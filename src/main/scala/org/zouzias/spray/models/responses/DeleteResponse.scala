package org.zouzias.spray.models.responses

import spray.json.DefaultJsonProtocol

/**
 * Created by zouzias on 26/09/15.
 */
case class DeleteResponse(id : Int, message : String)


object DeleteResponseProtocol extends DefaultJsonProtocol {
  implicit val deleteResponseFormat = jsonFormat2(DeleteResponse)
}