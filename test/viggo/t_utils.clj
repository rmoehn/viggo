(ns viggo.t-utils
  (:require [viggo.utils :refer [normal-files-under]]
            [viggo.pictures :refer [pic-dir test-pics]]
            [midje.sweet :refer :all]))

(fact "`normal-files-under` returns exactly all normal files in a dir"
  (map #(.getName %) (normal-files-under pic-dir))
      => (just (map :filename test-pics) :in-any-order))
