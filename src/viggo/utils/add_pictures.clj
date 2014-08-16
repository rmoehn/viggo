(ns viggo.utils.add-pictures
  (:require [viggo.picture :refer :all]))

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
