(ns viggo.pic-data)

(defprotocol PicData
  "Access to a store for data about pictures as Pictures"
  (add-pic [this pic])
  (get-all-pics [this])
  (get-pic-for-filename [this filename])
  (get-pics-for-category [this category])
  (delete-pic [this pic]))
