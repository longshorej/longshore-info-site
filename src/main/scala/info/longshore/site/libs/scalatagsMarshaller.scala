package info.longshore.site.libs

import akka.http.scaladsl.marshalling.PredefinedToEntityMarshallers
import akka.http.scaladsl.model.MediaTypes
import scalatags.Text.all._

object scalatagsMarshaller {
  def apply() =
    PredefinedToEntityMarshallers
      .stringMarshaller(MediaTypes.`text/html`)
      .wrap(MediaTypes.`text/html`)((f: Frag) => "<!doctype html>" + f.render)
}
