(ns telegraph-clj.closure-telegram
  (:require [clojure.string :as s]
            [telegraph-clj.utils :as ut :refer [not-too-long? format-words
                                                string->word-list]]))

(def lorem-ipsum  (slurp "./lorem-ipsum.txt"))
(def answer (slurp "./answer.txt"))


(def closure-tele
  (let [line (atom nil)]
    (fn [word]
      (cond (nil? word) @line
            (not-too-long? @line word) (do
                                         (swap! line #(format-words %1 word))
                                         nil)
            :else (first
                   (reset-vals! line word))))))

(def word-list (string->word-list lorem-ipsum) )

(def final (->> word-list
                (map closure-tele)
                (filter (complement nil?))
                (s/join "\n")))

(assert (= (s/trim final)
           (s/trim answer) ))
