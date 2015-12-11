(ns wrapping (:require [clojure.string :as str]))

(defn dimensions-to-ints [box-dimensions]
  (map #(Integer/parseInt %) (str/split box-dimensions #"x")))

(defn required-paper [box-dimensions]
  (let [[w h l] (dimensions-to-ints box-dimensions)
        side-a (* l w)
        side-b (* w h)
        side-c (* h l)]
    (+ (* 2 side-a)
       (* 2 side-b)
       (* 2 side-c)
       (min side-a side-b side-c))))

(defn required-ribbon [box-dimensions]
  (let [[w h l] (dimensions-to-ints box-dimensions)
        perimeter-a (+ l l w w)
        perimeter-b (+ w w h h)
        perimeter-c (+ h h l l)]
    (+ (min perimeter-a perimeter-b perimeter-c)
       (* w h l))))

(defn total-required-ribbon [many-dimensions]
  (reduce + (map required-ribbon many-dimensions)))

(defn total-required-paper [many-dimensions]
  (reduce + (map required-paper many-dimensions)))

(defn read-dimensions [filename]
  (str/split (slurp filename) #"\r\n"))