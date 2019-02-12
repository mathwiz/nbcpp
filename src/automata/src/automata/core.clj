(ns automata.core
  (:gen-class))

(use
 'automata.controller)


(defn -main
  "Entry point for Automata"
  [& args]
  (cond (empty? args) (do (println "Please provide as arguments: size iterations rule initial-set"))
    (>= (count args) 2) (compute-automaton args)
    :else             (do (println "Bad command arguments.") nil)))
