(ns viggo.pic-data.t-map-store
  (:require [viggo.config :refer [config]]
            [viggo.pic-data.map-store :as map-store]
            [viggo.pic-data :refer :all]
            [viggo.picture  :refer [map->Picture]]
            [viggo.pictures :refer [test-pics]]
            [midje.sweet :refer :all]))

(def empty-pic-data map-store/empty-store)
(def save-pic-data map-store/save-store)
(def read-pic-data map-store/read-store)

(fact "An empty picture data store contains no pictures."
  (get-all-pics (empty-pic-data)) => [])

(fact "I can add one Picture to an empty data store and retrieve it again."
  (get-all-pics (add-pic (empty-pic-data) (nth test-pics 0)))
  => (contains (nth test-pics 0)))

(defn test-store []
  (-> (empty-pic-data)
      (add-pic (nth test-pics 0))
      (add-pic (nth test-pics 1))
      (add-pic (nth test-pics 2))))

(fact "A store can't have two Pictures with the same filename."
  (add-pic (test-store) (map->Picture {:filename "pic02.svg"}))
  => (throws AssertionError))

(fact "When I add Pictures to a picture data store, I can retrieve them again."
  (get-all-pics (test-store)) => (contains test-pics :in-any-order))

(facts "I can retrieve pictures by filename."
  (get-pic-for-filename (test-store) "pic03.svg") => (nth test-pics 2)
  (get-pic-for-filename (test-store) "piccy.svg") => nil)

(let [ts  (test-store)
      tp1 (nth test-pics 1)]
  (facts "After deleting pictures, they're not there anymore"
     (get-all-pics ts)                      => (contains tp1)
     (get-all-pics (delete-pic ts tp1)) =not=> (contains tp1)))

(facts "I can retrieve pictures by category."
  (get-pics-for-category (test-store) "Consonants")
  => (just [(nth test-pics 1) (nth test-pics 2)] :in-any-order)
  (get-pics-for-category (test-store) "EdElghac4")
  => [])

(let [written-store (test-store)
      read-store (do
                   (save-pic-data written-store (:pic-data-file config))
                   (read-pic-data (:pic-data-file config)))]
  (facts "I can make the store permanent."
    (get-all-pics read-store) => (get-all-pics written-store)))
