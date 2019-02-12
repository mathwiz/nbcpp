(ns automata.view)

(use '[clojure.string :only (join split)])


(defn show [row]
  (do (println row)))

(defn make-string [elems]
  (join elems))