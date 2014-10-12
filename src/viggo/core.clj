(ns viggo.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [response]]
            [ring.middleware.file :refer [wrap-file]]
            [compojure.core :refer :all]
            [net.cgrand.enlive-html :as html]))

(def picture-resource (io/resource "pictures"))

(def image-files
  (filter #(.isFile %)
          (file-seq
            (io/file
              (io/resource "pictures")))))

(html/deftemplate image-page (io/resource "templates/pic_list.html") [])

(defroutes image-handler
  (ANY "/" [] (image-page)))
;; route/resources

(def app (wrap-file image-handler (.getPath picture-resource)))

(defonce server (run-jetty #'app {:port 10000 :join? false}))
