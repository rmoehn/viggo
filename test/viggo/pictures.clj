(ns viggo.pictures
  (:require [viggo.picture :refer [map->Picture]]))

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
