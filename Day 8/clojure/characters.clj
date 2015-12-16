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
  
(defn re-encoded-count [string]
  (-> string
    (clojure.string/replace #"^\"(.*)\"$" "___$1___")
    (clojure.string/replace #"\\" "__")
    (clojure.string/replace #"\"" "__")
    count))

(defn char-count-encoded-diff [string]
  (- (re-encoded-count string) (count string)))

(defn char-count-encoded-diff-collection [strings]
  (reduce + (map char-count-encoded-diff strings)))
