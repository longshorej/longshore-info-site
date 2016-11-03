package info.longshore.site.libs

import com.yahoo.platform.yui.compressor.CssCompressor
import java.io._

object compressCss {
  def apply(in: String): String = {
    val out = new StringWriter()
    val css = new CssCompressor(new StringReader(in))

    css.compress(out, -1)

    out.toString
  }
}
