(ns viggo.utils.add-pictures)

(defn generate-skeleton
  "Returns a skeleton datastructure to hold the information for the pictures
   in the given directory."
  [dir]
  (map (fn [pic-file]
         (map->Picture {:filename (.getName pic-file)}))
       (file-seq dir)))
