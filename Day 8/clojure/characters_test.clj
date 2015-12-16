(ns characters-test
  (:require [clojure.test :refer :all]))

(load-file "characters.clj")

(def test-strings (characters/file->strings "test_strings.txt"))

(deftest literal-count-empty-string
  (is (= 2 (count (test-strings 0)))))

(deftest literal-count-string
  (is (= 5 (count (test-strings 1)))))

(deftest literal-count-string-with-escaped-quote
  (is (= 10 (count (test-strings 2)))))

(deftest literal-count-string-with-hex-escape
  (is (= 6 (count (test-strings 3)))))

(deftest in-memory-count-empty-string
  (is (= 0 (characters/in-memory-count (test-strings 0)))))

(deftest in-memory-count-string
  (is (= 3 (characters/in-memory-count (test-strings 1)))))

(deftest in-memory-count-string-with-escaped-quote
  (is (= 7 (characters/in-memory-count (test-strings 2)))))

(deftest in-memory-count-string-with-hex-escape
  (is (= 1 (characters/in-memory-count (test-strings 3)))))

(deftest char-count-diff-1
  (is (= 5 (characters/char-count-diff (test-strings 3)))))

(deftest char-count-diff-collection-test
  (is (= 12 (characters/char-count-diff-collection test-strings))))

(def challenge-strings (characters/file->strings "strings.txt"))

(deftest challenge-1
  (is (= 1350 (characters/char-count-diff-collection challenge-strings))))