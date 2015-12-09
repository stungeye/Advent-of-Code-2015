(ns decorations)

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
