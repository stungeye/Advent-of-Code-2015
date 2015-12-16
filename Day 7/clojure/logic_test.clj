(ns logic-test
  (:require [clojure.test :refer :all]))

(load-file "logic.clj")

(deftest tokenize-command-1
  (is (= ["kh" {:operator bit-or :operands ["kg" "kf"]}] (logic/tokenize-command "kg OR kf -> kh"))))

(deftest tokenize-command-2
  (is (= ["dr" {:operator bit-not :operands ["dq"]}] (logic/tokenize-command "NOT dq -> dr"))))

(deftest tokenize-command-3
  (is (= ["b" {:value "44430"}] (logic/tokenize-command "44430 -> b"))))
  
(deftest tokenize-command-4
  (is (= ["y" {:operator bit-shift-left :operands["x" "2"]}] (logic/tokenize-command "x LSHIFT 2 -> y"))))

(def processed-test-commands (logic/tokenize-commands (logic/file->commands "test_commands.txt")))

(deftest tokenize-file
  (is (= processed-test-commands
         {"dr" {:operator bit-not :operands ["dq"]}
          "kh" {:operator bit-or :operands ["kg" "kf"]}
          "b"  {:value "44430"}})))

(deftest override-signal-1
  (is (= (logic/override-signal "555 -> dr" processed-test-commands)
         {"dr" {:value "555"}
          "kh" {:operator bit-or :operands ["kg" "kf"]}
          "b"  {:value "44430"}})))

(def test-commands ["123 -> x" "456 -> y" "x AND y -> d" "x OR y -> e" "x LSHIFT 2 -> f" "y RSHIFT 2 -> g" "NOT x -> h" "NOT y -> i" "x -> a"])

(deftest solving-for-1
  (is (= 123 (logic/solve-for "x" (logic/tokenize-commands test-commands)))))

(deftest solving-for-2
  (is (= 456 (logic/solve-for "y" (logic/tokenize-commands test-commands)))))
  
(deftest solving-for-3
  (is (= 72 (logic/solve-for "d" (logic/tokenize-commands test-commands)))))
  
(deftest solving-for-4
  (is (= 507 (logic/solve-for "e" (logic/tokenize-commands test-commands)))))
  
(deftest solving-for-5
  (is (= 114 (logic/solve-for "g" (logic/tokenize-commands test-commands)))))
  
(deftest solving-for-6
  (is (= 65412 (logic/solve-for "h" (logic/tokenize-commands test-commands)))))
  
(deftest solving-for-7
  (is (= 65079 (logic/solve-for "i" (logic/tokenize-commands test-commands)))))
  
(deftest solving-for-8
  (is (= 492 (logic/solve-for "f" (logic/tokenize-commands test-commands)))))

(deftest solving-for-9
  (is (= 123 (logic/solve-for "a" (logic/tokenize-commands test-commands)))))

(def challenge-commands (logic/tokenize-commands (logic/file->commands "commands.txt")))

(deftest challenge-1
  (is (= 3176 (logic/solve-for "a" challenge-commands))))

(deftest challenge-2
  (is (= 3176 (logic/solve-for "a" (logic/override-signal "3176 -> b" challenge-commands)))))