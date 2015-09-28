package org.zouzias.spray.actors.swagger

import com.gettyimages.spray.swagger.SwaggerHttpService
import com.wordnik.swagger.model.ApiInfo
import org.zouzias.spray.actors.JupiterActor
import org.zouzias.spray.httpservices.{UserHttpService, JupiterHttpService, MarsHttpService, PetHttpService}
import spray.routing.HttpServiceActor
import scala.reflect.runtime.universe.typeOf

/**
 * Swagger HTTP Service actor. It is responsible for handling all HTTP requests to Swagger ui.
 * It forwards all requests to swagger-ui.
 *
 */
class SwaggerActor extends HttpServiceActor {

  val swaggerService = new SwaggerHttpService {
    override def apiTypes = Seq(typeOf[UserHttpService], typeOf[PetHttpService], typeOf[JupiterHttpService], typeOf[MarsHttpService])
    override def apiVersion = "2.0"
    override def baseUrl = "/" // let swagger-ui determine the host and port
    override def docsPath = "api-docs"
    override def actorRefFactory = context
    override def apiInfo = Some(new ApiInfo("Spray-Swagger Sample", "A sample petstore service using spray and spray-swagger.", "TOC Url", "Anastasios Zouzias @zouzias", "Apache License", "http://www.apache.org/licenses/LICENSE-2.0"))

    //authorizations, not used
  }

  def receive = runRoute(swaggerService.routes ~
    get {
      pathPrefix("") {
        pathEndOrSingleSlash {
          getFromResource("swagger-ui/index.html")
        }
      } ~
        getFromResourceDirectory("swagger-ui")
    })
}
