(ns coins (:require [digest]))

(defn valid-hash [hash size]
  (= (subs hash 0 size)
     (apply str (repeat size "0"))))

(defn valid-secret [secret size]
  (valid-hash (digest/md5 secret) size))
  
(defn find-secret-number [key size]
  (some #(when (valid-secret (str key %) size) %) (range)))