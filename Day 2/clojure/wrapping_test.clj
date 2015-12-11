(ns wrapping-test
  (:require [clojure.test :refer :all]))

(load-file "wrapping.clj")

(deftest fifty-eight-square-feet-one-box-wrapping
  (is (= 58 (wrapping/required-paper "2x3x4"))))

(deftest forty-three-square-feet-one-box-wrapping
  (is (= 43 (wrapping/required-paper "1x1x10"))))
  
(deftest one-hundred-one-square-feet-two-boxes-wrapping
  (is (= 101 (wrapping/total-required-paper ["1x1x10" "2x3x4"]))))
  
(deftest challenge-1
  (is (= 1598415
         (wrapping/total-required-paper (wrapping/read-dimensions "packages.txt")))))

(deftest fifty-eight-square-feet-one-box-ribbon
  (is (= 34 (wrapping/required-ribbon "2x3x4"))))

(deftest forty-three-square-feet-one-box-ribbon
  (is (= 14 (wrapping/required-ribbon "1x1x10"))))
  
(deftest one-hundred-one-square-feet-two-boxes-ribbon
  (is (= 48 (wrapping/total-required-ribbon ["1x1x10" "2x3x4"]))))

(deftest challenge-2
  (is (= 1598415 (wrapping/total-required-ribbon (wrapping/read-dimensions "packages.txt")))))

(run-tests)