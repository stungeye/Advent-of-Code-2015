(ns logic)

(defn tokenize-command [command]
  (let [[a b c d e] (clojure.string/split command #" ")]
  (cond
    (= b "->")     [c {:value a}]
    (= a "NOT")    [d {:operator bit-not                  :operands [b]}]
    (= b "AND")    [e {:operator bit-and                  :operands [a c]}]
    (= b "OR")     [e {:operator bit-or                   :operands [a c]}]
    (= b "LSHIFT") [e {:operator bit-shift-left           :operands [a c]}]
    (= b "RSHIFT") [e {:operator unsigned-bit-shift-right :operands [a c]}])))

(defn file->commands [filename]
  (clojure.string/split (slurp filename) #"\r\n"))
  
(defn tokenize-commands [raw-commands]
  (reduce (fn [parsed-commands command]
              (let [[wire wiring] (tokenize-command command)]
                (assoc parsed-commands wire wiring)))
          {}
          raw-commands))

(declare value-of-operand)

(defn solve-for [wire commands]
  (let [{:keys [operator operands value]} (commands wire)]
    (if (nil? operator)
      (value-of-operand value commands)
      (bit-and 0xffff (apply operator (map #(value-of-operand %1 commands) operands))))))

(defn value-of-operand [operand commands]
  (let [numeric-value (read-string operand)]
    (if (number? numeric-value) numeric-value (solve-for operand commands))))