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
(defn total-houses-visited [houses]
  (let [house-seq (seq houses)
        house-set (set (house-visit-coordinates house-seq))]
    (count house-set)))