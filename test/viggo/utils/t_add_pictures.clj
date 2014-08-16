(ns viggo.utils.t-add-pictures
  (:require [clojure.java.io :as io]
            [viggo.utils.add-pictures :refer :all]
            [midje.sweet :refer :all]))

(def pic-dir (io/as-file (io/resource "test/testpics")))

(fact "`dirless-file-seq` returns exactly all normal files in a dir"
  (map #(.getName %) (dirless-file-seq pic-dir))
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
