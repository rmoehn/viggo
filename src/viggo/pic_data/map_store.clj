(ns viggo.pic-data.map-store
  (:require [viggo.pic-data :refer :all]))

(defn assoc-without-overwriting
  "Like assoc on maps, but throws an exception if the key already exists."
  [m k v]
  {:pre [(not (contains? m k))]}
  (assoc m k v))

(deftype MapStore [picmap]
  PicData
  (add-pic [this pic]
    (MapStore. (assoc-without-overwriting (.picmap this)
                                          (:filename pic)
                                          pic)))
  (get-all-pics [this]
    (or (vals (.picmap this))
        []))
  (get-pic-for-filename [this filename]
    (get (.picmap this) filename))
  (get-pics-for-category [this cat]
    (filter #(contains? (:categories %) cat)
            (get-all-pics this)))
  (delete-pic [this pic]
    (MapStore. (dissoc (.picmap this) (:filename pic)))))

(defn empty-store []
  "Returns an empty PicData store"
  (MapStore. {}))
