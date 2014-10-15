(ns viggo.pictures
  (:require [clojure.java.io :as io]
            [viggo.picture :refer [map->Picture]]))

(def pic-dir (io/resource "test/testpics"))

(def test-pics
  (map map->Picture [{ :filename "pic01.svg"
                       :description "An A"
                       :categories #{"Vowels" "Letters"}
                       :note "Drawn with Inkscape" }
                     { :filename "pic02.svg"
                       :description "A B"
                       :categories #{"Consonants" "Letters"}
                       :note "Very quickly" }
                     { :filename "pic03.svg"
                       :description "A C"
                       :categories #{"Consonants" "Letters"}
                       :note "CDC!" }]))
