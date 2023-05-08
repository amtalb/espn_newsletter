(defproject espn-newsletter "0.1.0-SNAPSHOT"
  :description "ESPN discontinued its newsletter in 2021 so I am making a new one using its RSS feed."
  :url "https://github.com/amtalb/espn_newsletter"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [enlive "1.1.6"]
                 [environ "1.2.0"]
                 [org.apache.commons/commons-email "1.5"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot espn-newsletter.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
