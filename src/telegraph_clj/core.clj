(ns telegraph-clj.core
  (:require [clojure.string :as s]
            [telegraph-clj.utils :as ut :refer [not-too-long? format-words
                                                string->word-list]]))

(def lorem-ipsum  (slurp "./lorem-ipsum.txt"))
(def answer (slurp "./answer.txt"))

