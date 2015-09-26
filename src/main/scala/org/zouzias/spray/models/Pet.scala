package org.zouzias.spray.models

import spray.json.DefaultJsonProtocol

case class Pet(id: Int, name: String)


object PetProtocol extends DefaultJsonProtocol {
  implicit val petFormat = jsonFormat2(Pet)
}