(ns automata.view)

(use
  '[clojure.string :only (join split)])


(defn show [row]
  (do (println row)))


(defn replace-print-chars [x]
  (cond (= x 0) " "
    (= x 1)     "*"
    :else       (str x)))


(defn make-string [elems]
  (join (map replace-print-chars elems)))