(ns automata.controller)

(use
 'automata.model
 'automata.view)


(defn do-random? [string]
  (= "r" string))


(defn parse-init [string]
  (cond (> 0 (count string)) '(1 0 1)
    :else                    '(0 1)))


(defn compute-iteration [automaton iterations]
  (cond (= iterations 0) nil
    :else                (do (show (make-string automaton))
                           (recur (evolve automaton) (dec iterations)))))


(defn compute-automaton [args]
  (let* [size (Integer/parseInt (first args))
         rows (Integer/parseInt (second args))
         rule (Integer/parseInt (nth args 2))
         init (nth args 3)]
    (compute-iteration
     (if (do-random? init) (make-automaton size) (make-automaton size (parse-init init)))
     rows)))
