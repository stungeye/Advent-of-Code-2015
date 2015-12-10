(ns decorations (:use clojure.set))

(def tokenizer #"(turn on|turn off|toggle) (\d{1,3}),(\d{1,3}) through (\d{1,3}),(\d{1,3})")
(def width 1000)
(def height 1000)

(def initial-grid
  (vec (repeat (* width height) false)))

(defn point-to-position [x y width height]
  (+ x (* y width)))

(defn tokenize-command [string]
  (let [[_ command & coords] (re-matches tokenizer string)]
    (concat [command] (map read-string coords))))

(defn tokenize-file [filename]
  (let [raw-commands (clojure.string/split (slurp filename) #"\r\n")]
    (map tokenize-command raw-commands)))

(defn turn-lights-on [lights x-range y-range]
  (into lights (for [x x-range y y-range] [x y])))

(defn turn-lights-off [lights x-range y-range]
  (let [switch-off (into #{} (for [x x-range y y-range] [x y]))]
    (clojure.set/difference lights switch-off)))

(defn next-light-set [lights command]
  (let [[action x1 y1 x2 y2] command
        x-range (range x1 (inc x2))
        y-range (range y1 (inc y2))]
    (turn-lights-on lights x-range y-range)))
