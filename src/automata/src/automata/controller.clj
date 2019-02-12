(ns automata.controller)

(use
 'automata.model
 'automata.view)


(defn compute-automaton [args]
  (let* [size (Integer/parseInt (first args))
         rows (Integer/parseInt (second args))
         rule (Integer/parseInt (nth args 2))
         init (nth args 3)]
    (do (show (make-string (make-row size init))))))

