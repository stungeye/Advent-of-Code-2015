(ns shortest-test
  (:require [clojure.test :refer :all]))

(load-file "shortest.clj")

(def test-distances (shortest/file->strings "test_distances.txt"))

(deftest test-test
  (is (= 2 2)))
