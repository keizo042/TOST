package controllers.admin

import play.api._
import play.api.mvc._
import play.api.Play.current

object Application extends Controller with Secured {
  def index = IsAuthenticated { name => _ =>
    Ok(views.html.admin.index())
  }
}
