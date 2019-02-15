(ns automata.model)


(defn random-list [len acc]
  (cond (= len 0) acc
    :else         (recur (dec len) (cons (if (< (rand) 0.5) 0 1) acc))))


(defn recycle-list [len acc seed]
  (cond (<= len 0)             acc
    (< (- len (count seed)) 0) (concat acc (take len seed))
    :else                      (recur (- len (count seed)) (concat acc seed) seed)))


(defn automaton-new [rule cells]
  (hash-map :rule rule :cells cells))


(defn make-automaton
  ([len rule] (automaton-new rule (random-list len ())))
  ([len rule init] (automaton-new rule (recycle-list len () init))))


(defn evolve-cell [cells index rule]
  (let [left   (if (= index 0) (nth cells (- (count cells) 1)) (nth cells (dec index)))
        center (nth cells index)
        right  (if (= index (- (count cells) 1)) 0 (nth cells (inc index)))]
    (rule left center right)))


(defn evolve-cells [cells rule index acc]
  (cond (= it (count cells)) acc
    :else                    (recur cells rule (inc index) (cons (evolve-cell cells index rule) acc))))


(defn evolve [automaton]
  "Computes the next iteration of automaton"
  (automaton-new (get automaton :rule)
                 (get automaton :cells)))


(defn R0 [l c r]
  false)


(defn R30 [l c r]
  (if (or (and (= l 1) (= c 0) (= r 0)))
    1
    0))


(defn R90 [l c r]
  (if (or (and (= l 1) (= c 0) (= r 0)))
    1
    0))


(defn make-rule [num]
  (cond (= num 30) R30
    (= num 90)     R90
    :else          R0))