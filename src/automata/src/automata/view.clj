(ns automata.view)

(use '[clojure.string :only (join split)])


(defn show [row]
  (do (println row)))

(defn make-string [elems]
  (println elems)
  (println (type (first elems)))
  (join elems))