(ns characters)

(defn file->strings [filename]
  (clojure.string/split (slurp filename) #"\r\n"))