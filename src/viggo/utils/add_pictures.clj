(ns viggo.utils.add-pictures
  (:require [viggo.picture :refer :all]
            [viggo.utils :refer [normal-files-under]]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.pprint :as pp :refer [pprint *print-right-margin*]]))

(defn generate-skeleton
  "Returns a skeleton datastructure to hold the information for the pictures
   in the given directory."
  [dir]
  (map (fn [pic-file]
         (map->Picture { :filename (.getName pic-file)
                         :description ""
                         :categories #{}
                         :note "" }))
       (sort-by #(.getName %)
                (normal-files-under dir))))

(defn write-skeleton
  "Pretty-prints the skeleton to the provided file, so that picture
   information can easily be filled in."
  [skeleton file]
  (binding [*print-right-margin* 60
            *print-dup* true] ; Write map entries on one line each.
    (pprint skeleton (io/writer file))))

(defn read-pic-data
  "Reads picture data from a filled-in skeleton file."
  [file]
  ;; TODO: Make `write-skeleton` write type information like `print`, so that
  ;;       we don't have to map maps to Pictures after reading.
  (map map->Picture (edn/read-string (slurp file))))
