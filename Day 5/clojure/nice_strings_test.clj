(ns nice-strings-test
  (:require [clojure.test :refer :all]))

(load-file "nice_strings.clj")

(def nice-string-1 "ugknbfddgicrmopn")
(def nice-string-2 "aaa")
(def naughty-string-1 "jchzalrnumimnmhp")
(def naughty-string-2 "haegwjzuvuyypxyu")
(def naughty-string-3 "dvszwmarrgswjxm")

(deftest correctly-identify-n-vowel-string-1
  (is (= true (nice-strings/contains-n-vowels? nice-string-1 2))))

(deftest correctly-identify-n-vowel-string-2
  (is (= true (nice-strings/contains-n-vowels? nice-string-1 3))))

(deftest correctly-identify-n-vowel-string-3
  (is (= false (nice-strings/contains-n-vowels? nice-string-1 4))))

(deftest correctly-identity-doubled-chars-1
  (is (= true (nice-strings/contains-doubled-chars? nice-string-1))))

(deftest correctly-identity-doubled-chars-2
  (is (= true (nice-strings/contains-doubled-chars? nice-string-1))))
  
(deftest correctly-identity-doubled-chars-3
  (is (= false (nice-strings/contains-doubled-chars? naughty-string-1))))
  
(deftest correctly-identity-strings-with-banned-subs-1
  (is (= true (nice-strings/contains-banned-subs? naughty-string-2))))

(deftest correctly-identity-strings-with-banned-subs-1
  (is (= false (nice-strings/contains-banned-subs? nice-string-1))))
  
(deftest validate-nice-string-1
  (is (= true (nice-strings/nice-string? nice-string-1))))

(deftest validate-nice-string-2
  (is (= true (nice-strings/nice-string? nice-string-2))))
  
(deftest invalidate-naughty-string-1
  (is (= false (nice-strings/nice-string? naughty-string-1))))
  
(deftest invalidate-naughty-string-2
  (is (= false (nice-strings/nice-string? naughty-string-2))))

(deftest invalidate-naughty-string-3
  (is (= false (nice-strings/nice-string? naughty-string-3))))

(deftest nice-string-count-1
  (is (= 1 (nice-strings/nice-string-count [nice-string-1 naughty-string-1]
                                           nice-strings/nice-string?))))

(deftest nice-string-count-2
  (is (= 0 (nice-strings/nice-string-count [naughty-string-1 naughty-string-2]
                                           nice-strings/nice-string?))))
  
(deftest nice-string-count-3
  (is (= 2 (nice-strings/nice-string-count [nice-string-1 naughty-string-1 nice-string-2]
                                           nice-strings/nice-string?))))

(deftest challenge-1
  (is (= 238 (nice-strings/nice-string-count (nice-strings/read-strings "strings.txt")
                                             nice-strings/nice-string?))))

(def new-nice-1 "qjhvhtzxzqqjkmpb")
(def new-nice-2 "xxyxx")
(def new-naughty-1 "uurcxstgmygtbstg")
(def new-naughty-2 "ieodomkazucvgmuy")

(deftest valid-new-nice-1
  (is (= true (nice-strings/new-nice-string? new-nice-1))))

(deftest valid-new-nice-2
  (is (= true (nice-strings/new-nice-string? new-nice-2))))

(deftest ivalid-new-naughty-1
  (is (= false (nice-strings/new-nice-string? new-naughty-1))))

(deftest invalid-new-naughty-2
  (is (= false (nice-strings/new-nice-string? new-naughty-2))))

(deftest challenge-1
  (is (= 69 (nice-strings/nice-string-count (nice-strings/read-strings "strings.txt")
                                             nice-strings/new-nice-string?))))