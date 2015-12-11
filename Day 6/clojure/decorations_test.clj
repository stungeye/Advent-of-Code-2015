(ns decorations-test
  (:require [clojure.test :refer :all]))

(load-file "decorations.clj")

(deftest tokenize-command
  (is (= ["turn on" 489 959 759 964] (decorations/tokenize-command "turn on 489,959 through 759,964"))))

(deftest tokenize-from-file
  (is (= [["turn on" 489 959 759 964] ["turn off" 820 516 871 914]]
         (decorations/tokenize-file "test_commands.txt"))))

(def nine-lights-on #{[0 0] [0 1] [0 2] [1 0] [1 1] [1 2] [2 0] [2 1] [2 2]})

(deftest next-light-set-1
  (is (= 9 (count (decorations/next-light-set #{} ["turn on" 0 0 2 2])))))

(deftest next-light-set-2
  (is (= 5 (count (decorations/next-light-set nine-lights-on ["turn off" 0 0 1 1])))))

(deftest next-light-set-3
  (is (= 11 (count (decorations/next-light-set nine-lights-on ["toggle" 2 2 3 3])))))

(deftest turn-lights-on-1
  (is (= 9 (count (decorations/turn-lights-on #{} (range 0 3) (range 0 3))))))

(deftest turn-lights-on-2
  (is (= nine-lights-on
         (decorations/turn-lights-on #{} (range 0 3) (range 0 3)))))

(deftest turn-lights-off-1
  (is (= 5 (count (decorations/turn-lights-off nine-lights-on (range 0 2) (range 0 2))))))

(deftest toggle-lights-1
  (is (= 0 (count (decorations/toggle-lights nine-lights-on (range 0 3) (range 0 3))))))
  
(deftest toggle-lights-2
  (is (= 9 (count (decorations/toggle-lights #{} (range 0 3) (range 0 3))))))
  
(deftest toggle-lights-3
  (is (= 11 (count (decorations/toggle-lights nine-lights-on (range 2 4) (range 2 4))))))

(deftest apply-command-1
  (is (= nine-lights-on (decorations/apply-light-commands [["turn on" 0 0 2 2]]))))

(deftest apply-command-2
  (is (= 5 (count (decorations/apply-light-commands [["turn on" 0 0 2 2] ["turn off" 0 0 1 1]])))))

; Commented out because it take 30+ seconds. 
;(deftest challenge-1
;  (is (= 569999 (count (decorations/apply-light-commands (decorations/tokenize-file "commands.txt"))))))

(deftest commands->range-commands-1
  (is (= [["turn off" 0 0] ["turn off" 0 1] ["turn off" 1 0] ["turn off" 1 1]] (decorations/commands->range-commands [["turn off" 0 0 1 1]]))))

(deftest commands->range-commands-1
  (is (= [["turn off" 0 0] ["turn on" 1 1]] (decorations/commands->range-commands [["turn off" 0 0 0 0] ["turn on" 1 1 1 1]]))))
  
(deftest next-light-hash-1
  (is (= {[0 0] 1} (decorations/next-light-hash {[0 0] 0} ["turn on" 0 0]))))

(deftest next-light-hash-2
  (is (= {[0 0] 1} (decorations/next-light-hash {} ["turn on" 0 0]))))

(deftest next-light-hash-3
  (is (= {[0 0] 0} (decorations/next-light-hash {[0 0] 1} ["turn off" 0 0]))))

(deftest total-brightness-1
  (is (= 4 (decorations/total-brightness {[0 0] 1 [0 1] 0 [1 0] 3}))))

(deftest challenge-2-pre
  (is (= 1626 (decorations/total-brightness (decorations/apply-light-commands-2 (decorations/tokenize-file "test_commands.txt"))))))

(deftest challenge-2
  (is (= 17836115 (decorations/total-brightness (decorations/apply-light-commands-2 (decorations/tokenize-file "commands.txt"))))))