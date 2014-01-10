package controllers.admin

import play.api._
import play.api.mvc._
import play.api.Play.current

object Auth extends Controller {
  def login = Action { request =>
    Ok(views.html.admin.login())
  }

  def logout = Action {
    Redirect(controllers.routes.Application.index).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
/*
  def authenticate = Action {

  }
*/
}
