(ns coins (:require [digest]))

(defn valid-hash [hash]
  (= (subs hash 0 5) "00000"))

(defn valid-secret [secret]
  (valid-hash (digest/md5 secret)))
  
(defn find-secret-number [key]
  (some #(when (valid-secret (str key %)) %) (range)))