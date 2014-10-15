(ns viggo.utils.t-add-pictures
  (:require [clojure.java.io :as io]
            [viggo.utils.add-pictures :refer :all]
            [viggo.pictures :refer [pic-dir test-pics]]
            [midje.sweet :refer :all]))

(def filled-in-file (io/resource "test/pic-data.edn"))

(fact "`generate-skeleton` returns a list of nearly empty Pictures."
  (generate-skeleton pic-dir) => (just { :filename "pic01.svg"
                                         :description ""
                                         :categories #{}
                                         :note "" }
                                       { :filename "pic02.svg"
                                         :description ""
                                         :categories #{}
                                         :note "" }
                                       { :filename "pic03.svg"
                                         :description ""
                                         :categories #{}
                                         :note "" }))

(let [skeleton-file (java.io.File/createTempFile "skel" ".edn")
      skeleton (generate-skeleton pic-dir)]
  (fact "When I `write-skeleton` and `read-skeleton` again, I get the same."
    (do (write-skeleton skeleton skeleton-file)
        (read-pic-data skeleton-file))
    => skeleton)
  (.delete skeleton-file))

(fact "`read-pic-data` gives me the data I wrote in the file"
  (read-pic-data filled-in-file) => test-pics)
