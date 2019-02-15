(ns automata.controller)

(use
 'automata.model
 'automata.view)


(defn do-random? [string]
  (= "r" string))


(defn explode-string [string]
  (clojure.string/split string #""))


(defn convert-to-long [strings]
  (map #(Long/parseLong %) strings))


(defn parse-init [string]
  (cond (> (count string) 0) (convert-to-long (explode-string string))
    :else                    ()))


(defn compute-iteration [automaton iterations]
  (cond (= iterations 0) nil
    :else                (do (show (make-string (get automaton :cells)))
                           (recur (evolve automaton) (dec iterations)))))


(defn compute-automaton [args]
  (let* [size (Integer/parseInt (first args))
         rows (Integer/parseInt (second args))
         rule-num (Integer/parseInt (nth args 2))
         init (nth args 3)]
    (compute-iteration
     (if (do-random? init)
       (make-automaton size (make-rule rule-num))
       (make-automaton size (make-rule rule-num)(parse-init init)))
     rows)))
