(ns decorations (:use [clojure.set :as set]))

(def tokenizer #"(turn on|turn off|toggle) (\d{1,3}),(\d{1,3}) through (\d{1,3}),(\d{1,3})")

(defn tokenize-command [string]
  (let [[_ command & coords] (re-matches tokenizer string)]
    (concat [command] (map read-string coords))))

(defn tokenize-file [filename]
  (let [raw-commands (clojure.string/split (slurp filename) #"\r\n")]
    (map tokenize-command raw-commands)))

(defn range-into-set [set x-range y-range]
  (into set (for [x x-range y y-range] [x y])))

(defn turn-lights-on [lights x-range y-range]
  (range-into-set lights x-range y-range))

(defn turn-lights-off [lights x-range y-range]
  (let [switch-off (range-into-set #{} x-range y-range)]
    (set/difference lights switch-off)))

(defn toggle-lights [lights x-range y-range]
  (let [switch-on (range-into-set #{} x-range y-range)
        switch-off (set/intersection lights switch-on)]
    (set/difference (set/union lights switch-on) switch-off)))

(defn next-light-set [lights command]
  (let [[action x1 y1 x2 y2] command
        x-range (range x1 (inc x2))
        y-range (range y1 (inc y2))]
    (case action
      "turn on" (turn-lights-on lights x-range y-range)
      "turn off" (turn-lights-off lights x-range y-range)
      "toggle" (toggle-lights lights x-range y-range))))

(defn apply-light-commands [commands]
  (reduce
    (fn [lights command] (next-light-set lights command))
    #{}
    commands))

(defn apply-light-commands-recur [commands]
  (loop [lights #{}
         remaining-commands commands]
      (if remaining-commands
        (recur (next-light-set lights (first remaining-commands))
               (next remaining-commands))
        lights)))