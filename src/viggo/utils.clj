(ns viggo.utils
  (:require [clojure.java.io :as io]))

(defn normal-files-under
  "Like file-seq, but only returns normal files and works with URLs as well as
   Files."
  [dir]
  (filter #(.isFile %) (file-seq (io/as-file dir))))
