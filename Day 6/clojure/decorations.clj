(ns decorations (:use clojure.set))

(def tokenizer #"(turn on|turn off|toggle) (\d{1,3}),(\d{1,3}) through (\d{1,3}),(\d{1,3})")

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

(defn toggle-lights [lights x-range y-range]
  (let [to-toggle (into #{} (for [x x-range y y-range] [x y]))
        switch-on (clojure.set/difference to-toggle lights)
        switch-off (clojure.set/difference to-toggle switch-on)]
    (clojure.set/union switch-on (clojure.set/difference lights switch-off))))

(defn next-light-set [lights command]
  (let [[action x1 y1 x2 y2] command
        x-range (range x1 (inc x2))
        y-range (range y1 (inc y2))]
    (case action
      "turn on" (turn-lights-on lights x-range y-range)
      "turn off" (turn-lights-off lights x-range y-range)
      "toggle" (toggle-lights lights x-range y-range))))
