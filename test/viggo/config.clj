(ns viggo.config
  (:require [clojure.java.io :as io]))

(defn get-config
  "Returns a configuration hash with Viggo's files under the specified base
   directory."
  [base-dir]
  {:home          base-dir
   :pic-dir       (io/file base-dir "pics")
   :pic-data-file (io/file base-dir "pic-data")})
