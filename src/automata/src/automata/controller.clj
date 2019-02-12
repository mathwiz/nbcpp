(ns automata.controller)

(defn compute-automaton [args]
  (let* [size (Integer/parseInt (first args))
         rows (Integer/parseInt (second args))
         rule (Integer/parseInt (nth args 2))]
    (do (println (repeat size "1")))))
