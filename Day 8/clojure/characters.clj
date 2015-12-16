(ns characters)

(defn file->strings [filename]
  (clojure.string/split (slurp filename) #"\r\n"))

(defn in-memory-count [string]
  (-> string
      (clojure.string/replace #"^\"(.*)\"$" "$1")
      (clojure.string/replace #"\\\"" "_")
      (clojure.string/replace #"\\\\" "_")
      (clojure.string/replace #"\\x.." "_")
      count))

(defn char-count-diff [string]
  (- (count string) (in-memory-count string)))

(defn char-count-diff-collection [strings]
  (reduce + (map char-count-diff strings)))