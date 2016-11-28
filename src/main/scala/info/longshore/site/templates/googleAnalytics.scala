package info.longshore.site.templates

import org.apache.commons.lang3.StringEscapeUtils
import scalatags.Text.all._

object googleAnalytics {
  def apply(id: String) = script(`type` := "text/javascript",
    s"""(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');ga('create','${StringEscapeUtils.escapeEcmaScript(id)}','auto');ga('send','pageview');"""
  )
}
