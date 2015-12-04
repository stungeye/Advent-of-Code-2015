(ns coins-test
  (:require [clojure.test :refer :all]))

(load-file "coins.clj")

(deftest valid-hash-a
  (is (= true (coins/valid-hash "000001dbbfa" 5))))

(deftest valid-hash-b
  (is (= true (coins/valid-hash "000000dbbfa" 6))))
  
(deftest valid-hash-c
  (is (= true (coins/valid-hash "000021dbbfa" 4))))
  
(deftest invalid-hash-a
  (is (= false (coins/valid-hash "000021dbbfa" 5))))
  
(deftest invalid-hash-b
  (is (= false (coins/valid-hash "000001dbbfa" 6))))

(deftest valid-secret
  (is (= true (coins/valid-secret "abcdef609043" 5))))

(deftest invalid-secret
  (is (= false (coins/valid-secret "Kbcdef609043" 5))))

(deftest find-secret-a
  (is (= 609043 (coins/find-secret-number "abcdef" 5))))
  
(deftest find-secret-b
  (is (= 1048970 (coins/find-secret-number "pqrstuv" 5))))

(deftest challenge-1
  (is (= 254575 (coins/find-secret-number "bgvyzdsv" 5))))
  
(deftest challenge-2
  (is (= 254575 (coins/find-secret-number "bgvyzdsv" 6))))