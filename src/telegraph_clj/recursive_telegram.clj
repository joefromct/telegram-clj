(ns telegraph-clj.recursive-telegram
  (:require [clojure.string :as s]
            [telegraph-clj.utils :as ut :refer [not-too-long? format-words
                                                string->word-list]]))

;; recursion to cons up the lines.
(defn recursive-tele
  ([word-list] (recursive-tele word-list nil ))
  ([[word & rest-words]
    current-line]
   (if word
     (if (not-too-long? current-line word)
       (recursive-tele  rest-words (format-words current-line word)) ;; not too big so try to add another.
       (cons current-line
             (recursive-tele rest-words word)))    ;; this is the list aggregation/build up
     (cons current-line []))))

(def lorem-ipsum  (slurp "./lorem-ipsum.txt"))
(def answer       (slurp "./answer.txt"))


(def final (->> lorem-ipsum
                string->word-list
                recursive-tele
                (s/join "\n")))

(assert (= (s/trim final)
           (s/trim answer) ))
