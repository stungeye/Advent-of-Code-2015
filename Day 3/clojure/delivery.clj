(ns delivery)

;; Given a coordinate determine the next coordinate based on the direction.
;; ^ is up, v is down, > is right, < is left 
(defn next-coordinate [current-coordinate direction]
  (let [[x y] current-coordinate]
    (case direction
      \^ [x (inc y)]
      \v [x (dec y)]
      \> [(inc x) y]
      \< [(dec x) y])))

;; Builds a sequence of the x,y coordinates visted by following the provided directions.
(defn house-visit-coordinates [directions]
  (loop [house-coordinates ()                
         [x y]             [0 0]
         remaining-dirs    (seq directions)]
    (if remaining-dirs
      (recur (cons [x y] house-coordinates)
             (next-coordinate [x y] (first remaining-dirs))
             (next remaining-dirs))
      (cons [x y] house-coordinates))))

;; Count of unique visited coords returned by the above fnc.
(defn houses-visited [directions]
  (let [direction-seq (seq directions)
        house-set (set (house-visit-coordinates direction-seq))]
    (count house-set)))

;; Count of unique visited coords when santa and a robot split the list
(defn houses-visited-with-robot [directions]
  (let [robot-seq (take-nth 2 directions)
        santa-seq (take-nth 2 (rest directions))
        santa-visits (house-visit-coordinates santa-seq)
        robot-visits (house-visit-coordinates robot-seq)
        visit-set (set (concat santa-visits robot-visits))]
    (count visit-set)))
