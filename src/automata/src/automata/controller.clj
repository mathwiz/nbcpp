(ns automata.controller)

(use
 'automata.model
 'automata.view)


(defn compute-iteration [automaton iterations]
  (cond (= iterations 0) nil
    :else                (do (show (make-string automaton))
                           (recur (evolve automaton) (dec iterations)))))


(defn compute-automaton [args]
  (let* [size (Integer/parseInt (first args))
         rows (Integer/parseInt (second args))
         rule (Integer/parseInt (nth args 2))
         init (nth args 3)]
    (do (compute-iteration (make-automaton size init) rows))))
