(ns coins-test
  (:require [clojure.test :refer :all]))

(load-file "coins.clj")

(deftest valid-hash
  (is (= true (coins/valid-hash "000001dbbfa"))))

(deftest invalid-hash
  (is (= false (coins/valid-hash "000021dbbfa"))))

(deftest valid-secret
  (is (= true (coins/valid-secret "abcdef609043"))))

(deftest invalid-secret
  (is (= false (coins/valid-secret "Kbcdef609043"))))

(deftest find-secret-a
  (is (= 609043 (coins/find-secret-number "abcdef"))))
  
(deftest find-secret-b
  (is (= 1048970 (coins/find-secret-number "pqrstuv"))))

(deftest challenge-1
  (is (= 254575 (coins/find-secret-number "bgvyzdsv"))))