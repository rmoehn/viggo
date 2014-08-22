(ns viggo.config
  (:require [clojure.java.io :as io]))

(def ^:private cur-dir (System/getProperty "user.dir"))
(def ^:private home (io/file cur-dir "test-viggo"))
(def config {:home          home
             :pic-dir       (io/file home "pics")
             :pic-data-file (io/file home "pic-data")})
