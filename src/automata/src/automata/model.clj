(ns automata.model)


(defn random-list [len acc]
  (cond (= len 0) acc
    :else         (recur (dec len) (cons 0 acc))))


(defn recycle-list [len acc seed]
  (cond (<= len 0)             acc
    (< (- len (count seed)) 0) (concat acc (take len seed))
    :else                      (recur (- len (count seed)) (concat acc seed) seed)))


(defn make-automaton
  ([len] (random-list len ()))
  ([len init] (recycle-list len () init)))


(defn evolve [automaton]
  "Computes the next iteration of automaton"
  automaton)