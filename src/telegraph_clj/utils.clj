(ns telegraph-clj.utils
  (:require [clojure.string :as s]))

(defn not-too-long?[line, word-to-maybe-append]
  "Is this line to big if i add the next word?"
  (>= 80 (count (format "%s %s" line word-to-maybe-append))))

(defn format-words[current-line word]
  (if (nil? current-line)
    word
    (format "%s %s" current-line word)))

(defn string->word-list[s]
  "I put a nil on the end so we can figure out when to stop."
  (conj (s/split s #" ")
        nil))

