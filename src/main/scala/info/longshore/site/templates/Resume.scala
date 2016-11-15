package info.longshore.site.templates

import info.longshore.site.libs.compressCss
import scalatags.Text.all._
import scalatags.Text.tags2.{ title => titleTag, style => styleTag }

object Resume {
  def apply() = html(
    head(
      meta(charset := "utf-8"),
      meta(name := "viewport", content := "width=device-width, initial-scale=1.0"),
      meta(name := "author", content := "Jason Longshore"),
      meta(name := "description", content := "Jason Longshore is an experienced Scala developer"),
      meta(name := "keywords",
        content := 
          "scala,akka,spray,functional programming," +
          "event sourcing,java,typescript,sql,php,less," +
          "html,javascript,css,bash,pupet,linux,docker,sbt"
      ),
      titleTag("Jason Longshore"),
      styleTag(`type` := "text/css", raw(compressCss(cssText)))
    ),
    body(
      div(id := "resume",
        div(`class` := "row",
          div(`class` := "col", id := "personal",
            h1(id := "name", "Jason Longshore"),
            p(id := "job-title", "Software Developer")
          ),
          div(`class` := "col", id := "contact",
            div(id := "email", a(href:="mailto:longshorej@gmail.com", "longshorej@gmail.com")),
            div(id := "github", a(href:="https://github.com/longshorej", "longshorej @ GitHub")),
            //div(id := "phone", a(href :="tel:630-706-1287", "630-706-1287")),
            div(id := "location", "Chicago, IL, USA")
          )
        ),
        div(`class` := "row",
          p(id := "tag", `class` := "bordered",
            "I'm a software developer who is enthusiastic about " +
            "functional programming and immutable system design. " +
            "Software is changing the landscape of the world and yesterday's techniques " +
            "will not meet tomorrow's challenges. By embracing new methodologies we can " +
            "create truly robust technology that will scale and react to the demands of the future."
          )
        ),
        div(`class` := "row",
          div(`class` := "col",
            div(id := "work", `class` := "section",
              h2(`class` := "title", "Work Experience"),
  
              div(`class` := "entry", "HPN WorldWide"),
              div(`class` := "sub", "Sr. Software Developer"),
              div(`class` := "sub", "2008–present"),
              ul(
                li(span("Designed and implemented a health record management system using modern technologies–Akka HTTP, Event Sourcing, and React.JS.")),
                li(span("Overhauled legacy systems to interface with services using HTTP APIs.")),
                li(
                  span(
                    "Worked as lead developer of a small team to manage the development and maintenance of an " +
                    "enterprise system that powered hundreds of websites."
                  )
                ),
                li(span("Deployed and monitored a fleet of servers with tools such as Puppet and Docker.")),
                li(span("Worked to ensure compliance with HIPAA-related encryption regulations.")),
                li(span("Developed iOS and Java apps to collect on-site medical records during blood draws.")),
                li(span("Implemented code review and automated testing processes.")),
                li(span("Created tools to simplify internal development and deployment of applications."))
              )
            )
          ),
          div(`class` := "col",
            div(id := "education", `class` := "section",
              h2(`class` := "title", "Education"),
              div(`class` := "entry", "Elmhurst College"),
              div(`class` := "sub", "BS in Computer Science, magna cum laude"),
              div(`class` := "sub", "2005–2009")
            ),
  
            div(id := "keywords", `class` := "section",
              h2(`class` := "title", "Keywords"),
              div(`class` := "keywords-text",
                div("Scala, Akka, Spray, FP, SBT"),
                div("Java, TypeScript, SQL, PHP, LESS"),
                div("React, HTML, JavaScript, CSS"),
                div("Bash, Puppet, Linux, Docker")
              )
            ),

            div(id := "interests", `class` := "section",
              h2(`class` := "title", "Interests"),
              div(`class` := "keywords-text",
                div("Functional Programming, UNIX Philosophy"),
                div("PC Gaming, DIY, Family")
              )
            )
          )
        )
      )
    )
  )

  def cssText = /* language="CSS" */
    """
      body, html {
        margin: 0;
        padding: 0;
      }

      body {
        background-color: #FFF;
        color: #333;
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        font-size: 11pt;
        line-height: 1.5em;
      }

      a, a:visited {
        text-decoration: none;
        color: #1A0DAB;
      }

      a:hover {
        text-decoration: underline;
      }

      .col {
        width: 50%;
        float: left;
      }

      .row:after {
        content: "";
        display: table;
        clear: both;
      }

      #name {
        margin: 0;
      }

      #name, h2.title {
        font-family: Cambria, Garamond, Helvetica serif;
      }

      #contact {
        text-align: right;
      }

      #resume {
        width: 600px;
        margin: 20px auto;
      }

      #job-title {
        font-size: 1.1em;
        font-style: italic;
        padding-left: 50px;
        margin: 0 0 20px 0;
      }

      .bordered {
        border-left: 5px solid #0078E0;
        padding-left: 15px;
      }

      .section {
        padding: 20px;
      }

      .section .title {
        text-align: center;
        border-bottom: 2px solid #0078E0;
      }

      .section .entry {
        font-weight: bold;
      }

      .section .sub {
        font-size: 0.9em;
      }

      .section ul {
        padding-left: 15px;
        font-size: 0.9em;
      }

      .section ul li {
        margin-bottom: 5px;
        list-style-type: square;
        color: #0078E0;
      }

      .section ul li span {
        color: #333;
      }

      .keywords-text {
        text-align: center;
        font-size: 0.9em;
        line-height: 1.5em;
      }

      #tag {
        padding: 20px 10px;
        margin-top: 20px;
      }

      @media only screen and (max-width: 599px) {
        #resume {
          width: 96%;
          overflow-x: hidden;
        }

        .col {
          width: 100%;
          float: none;
        }

        #name, #job-title {
          text-align: center;
        }

        #contact {
          text-align: left;
          margin-left: 30%;
        }

        #job-title {
          padding: 0;
        }
      }

      @media print {
        body {
          margin: 0.75in 0.75in;
        }

        #resume {
          width: 100%;
        }

        #tag {
          margin: 100px 0;
        }
      }
    """
}