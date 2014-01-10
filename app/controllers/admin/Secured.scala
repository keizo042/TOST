package controllers.admin

import play.api._
import play.api.mvc._

trait Secured { 
  private def name(request: RequestHeader) = request.session.get("user")

  private def onUnauthorized(request: RequestHeader) = Results.Redirect(controllers.admin.routes.Auth.login)

  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(name,onUnauthorized){user => Action(request =>
      f(user)(request))
  }
}
