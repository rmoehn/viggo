(ns viggo.core
  (:require [clojure.java.io :as io]))

(def html-doc-start
  (str "<!DOCTYPE html>"
       "<html lang=\"en\">"
       "<head>"
       "  <meta charset=\"utf-8\"/>"
       "</head>"
       "<body>"))

(def html-doc-end
  (str "</body>"
       "</html>"))

(defn gen-img-directive
  "Takes an image file and returns a HTML directive for including that image
  in a web page."
  [file]
  (str "<img src=\"" (io/as-url file) "\">" (.getName file) "</img>"))

(defn gen-paragraph
  "Returns arbitrary html wrapped in paragraph tags."
  [html-str]
  (str "<p>" html-str "</p>\n\n"))

(def add-newline
  #(str % "\n"))

(defn gen-image-list
  [image-files]
  (map #(gen-paragraph
          (add-newline
            (gen-img-directive %)))
       image-files))

(def image-files
  (file-seq
    (io/file
      (io/resource "pictures"))))

(def image-page
  (str html

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
