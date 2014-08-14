(ns viggo.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [response]]
            [ring.middleware.file :refer [wrap-file]]
            [compojure.core :refer :all]))

(def picture-resource (io/resource "pictures"))

(def image-files
  (filter #(.isFile %)
          (file-seq
            (io/file
              (io/resource "pictures")))))

(def add-newline
  #(str % "\n"))

(defn make-lines
  "Return a multi-line string made from the string arguments."
  [& strs]
  (string/join \newline strs))

(defn gen-paragraph
  "Returns arbitrary html wrapped in paragraph tags."
  [html-str]
  (str "<p>" html-str "</p>\n\n"))

(def html-doc-start
  (make-lines
     "<!DOCTYPE html>"
     "<html lang=\"en\">"
     "<head>"
     "  <meta charset=\"utf-8\"/>"
     "</head>"
     "<body>"))

(def html-doc-end
  (make-lines
     "</body>"
     "</html>"))

(defn gen-img-directive
  "Takes an image file and returns a HTML directive for including that image
  in a web page."
  [file]
  (let [basename (.getName file)]
    (str "<img src=\"" basename "\">" basename "</img>")))

(defn gen-image-list
  [image-files]
  (map #(gen-paragraph
          (gen-img-directive %))
       image-files))

(def image-page
  (make-lines
    html-doc-start
    (string/join (gen-image-list image-files))
    html-doc-end))

(defroutes image-handler
  (ANY "/" [] image-page))

(def app (wrap-file image-handler (.getPath picture-resource)))

(defonce server (run-jetty #'app {:port 10000 :join? false}))
