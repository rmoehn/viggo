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

(def image-data
  (map (fn [img-file]
         (let [basename (.getName img-file)]
           {:src basename, :descr basename}))
       image-files))

(def ^:dynamic *pic-sel* [[:p (html/nth-of-type 1)]])

(def pic-list-template (io/resource "templates/pic_list.html"))

(html/defsnippet image-item pic-list-template *pic-sel*
  [{:keys [src descr]}]
  [:img] (html/do->
           (html/set-attr :src src)
           (html/set-attr :alt descr)))

(html/deftemplate image-page pic-list-template [images]
  [:body] (html/content (map image-item images)))

(defroutes image-handler
  (ANY "/" [] (image-page image-data)))
;; route/resources

(def app (wrap-file image-handler (.getPath picture-resource)))

(defonce server (run-jetty #'app {:port 10000 :join? false}))
