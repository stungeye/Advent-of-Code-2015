(ns decorations-test
  (:require [clojure.test :refer :all]))

(load-file "decorations.clj")

(deftest correctly-builds-initial-grid
  (is (= 1000000 (count decorations/initial-grid))))

(deftest converts-points-to-positions-1
  (is (= 0 (decorations/point-to-position 0 0 10 10))))

(deftest converts-points-to-positions-2
  (is (= 1 (decorations/point-to-position 1 0 10 10))))

(deftest converts-points-to-positions-3
  (is (= 10 (decorations/point-to-position 0 1 10 10))))

(deftest converts-points-to-positions-4
  (is (= 11 (decorations/point-to-position 1 1 10 10))))

(deftest converts-points-to-positions-5
  (is (= 99 (decorations/point-to-position 9 9 10 10))))
  
(deftest tokenize-command
  (is (= ["turn on" 489 959 759 964] (decorations/tokenize-command "turn on 489,959 through 759,964"))))

(deftest tokenize-from-file
  (is (= [["turn on" 489 959 759 964] ["turn off" 820 516 871 914]]
         (decorations/tokenize-file "test_commands.txt"))))

(def nine-lights-on #{[0 0] [0 1] [0 2] [1 0] [1 1] [1 2] [2 0] [2 1] [2 2]})

(deftest next-light-set-1
  (is (= 9 (count (decorations/next-light-set #{} ["turn on" 0 0 2 2])))))

(deftest turn-lights-on-1
  (is (= 9 (count (decorations/turn-lights-on #{} (range 0 3) (range 0 3))))))

(deftest turn-lights-on-2
  (is (= nine-lights-on
         (decorations/turn-lights-on #{} (range 0 3) (range 0 3)))))

(deftest turn-lights-off-1
  (is (= 5 (count (decorations/turn-lights-off nine-lights-on (range 0 2) (range 0 2))))))