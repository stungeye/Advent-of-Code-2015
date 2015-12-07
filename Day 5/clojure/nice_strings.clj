(ns nice-strings (:require [digest]))

(defn contains-n-vowels? [string n]
  (< (dec n) (count (re-seq #"[aeiou]" string))))

(defn contains-doubled-chars? [string]
  (some? (re-find #"([a-z])\1" string)))

(defn contains-banned-subs? [string]
  (some? (re-find #"ab|cd|pq|xy" string)))

(defn contains-repeated-pair? [string]
  (some? (re-find #"([a-z][a-z]).*\1" string)))

(defn contains-middled-pair? [string]
  (some? (re-find #"([a-z])[a-z]\1" string)))

(defn nice-string? [string]
  (and (contains-n-vowels? string 3)
       (contains-doubled-chars? string)
       (not (contains-banned-subs? string))))

(defn new-nice-string? [string]
  (and (contains-repeated-pair? string)
       (contains-middled-pair? string)))

(defn nice-string-count [strings pred]
  (count (filter pred strings)))
  
(defn read-strings [filename]
  (clojure.string/split (slurp filename) #"\r\n"))