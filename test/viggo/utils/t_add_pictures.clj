(ns viggo.utils.t-add-pictures
  (:require [clojure.java.io :as io]
            [viggo.utils.add-pictures :refer :all]
            [midje.sweet :refer :all]))

(def pic-dir (io/resource "test/testpics"))
(def skeleton-file (io/resource "test/skeleton.clj"))

(fact "`normal-files-under` returns exactly all normal files in a dir"
  (map #(.getName %) (normal-files-under pic-dir))
      => (just ["pic01.svg" "pic02.svg" "pic03.svg"] :in-any-order))

(fact "`generate-skeleton` returns a list of nearly empty Pictures."
  (generate-skeleton pic-dir) => (just { :filename "pic01.svg"
                                         :description ""
                                         :categories []
                                         :note "" }
                                       { :filename "pic02.svg"
                                         :description ""
                                         :categories []
                                         :note "" }
                                       { :filename "pic03.svg"
                                         :description ""
                                         :categories []
                                         :note "" }))

(let [skeleton-file (java.io.File/createTempFile "skel" ".clj")
      skeleton (generate-skeleton pic-dir)]
  (fact "When I `write-skeleton` and `read-skeleton` again, I get the same."
    (do (write-skeleton skeleton skeleton-file)
        (read-skeleton skeleton-file))
    => skeleton)
  (.delete skeleton-file))

(fact "`read-skeleton` gives me the data I wrote in the file"
  (read-skeleton skeleton-file) => (just { :filename "pic01.svg"
                                           :description "An A"
                                           :categories ["Vowels" "Letters"]
                                           :note "Drawn with Inkscape" }
                                         { :filename "pic02.svg"
                                           :description "A B"
                                           :categories ["Consonants" "Letters"]
                                           :note "Very quickly" }
                                         { :filename "pic03.svg"
                                           :description "A C"
                                           :categories ["Consonants" "Letters"]
                                           :note "CDC!" }))
