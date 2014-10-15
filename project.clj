(defproject viggo "0.1.0-SNAPSHOT"
  :description "A web application for assembling communication books"
  :url "https://github.com/rmoehn/viggo"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.3.0"]
                 [ring/ring-jetty-adapter "1.3.0"]
                 [compojure "1.1.8"]
                 [enlive "1.1.5"]]
  :profiles {:dev {:dependencies [[midje "1.6.3"]
                                  [com.google.guava/guava "13.0.1"]]}})
