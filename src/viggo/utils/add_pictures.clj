(ns viggo.utils.add-pictures
  (:require [viggo.picture :refer :all]
            [clojure.java.io :as io]
            [clojure.pprint :as pp :refer [pprint *print-right-margin*]]))

(defn dirless-file-seq
  "Like file-seq, but only returns normal files."
  [dir]
  (filter #(.isFile %) (file-seq dir)))

(defn generate-skeleton
  "Returns a skeleton datastructure to hold the information for the pictures
   in the given directory."
  [dir]
  (map (fn [pic-file]
         (map->Picture { :filename (.getName pic-file)
                         :description ""
                         :categories []
                         :note "" }))
       (sort-by #(.getName %)
                (dirless-file-seq dir))))

(defn write-skeleton
  "Pretty-prints the skeleton to the provided file, so that picture
   information can easily be filled in."
  [skeleton file]
  (binding [*print-right-margin* 60
            *print-dup* true] ; Write map entries on one line each.
    (pprint skeleton (io/writer file))))

(defn read-skeleton
  "Reads a skeleton from a file."
  [file]
  ;; TODO: Make `write-skeleton` write type information like `print`, so that
  ;;       we don't have to map maps to Pictures after reading.
  (map map->Picture (read-string (slurp file))))
