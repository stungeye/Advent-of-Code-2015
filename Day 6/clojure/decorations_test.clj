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