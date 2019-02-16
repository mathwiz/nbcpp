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
  (let [left   (if (= index 0) (nth cells (dec (count cells))) (nth cells (dec index)))
        center (nth cells index)
        right  (if (= index (dec (count cells))) (nth cells 0) (nth cells (inc index)))]
    (rule left center right)))


(defn evolve-cells [cells rule index acc]
  (cond (= index (count cells)) acc
    :else                       (recur cells rule (inc index) (conj acc (evolve-cell cells index rule)))))


(defn evolve [automaton]
  "Computes the next iteration of automaton"
  (automaton-new (get automaton :rule)
                 (evolve-cells (get automaton :cells) (get automaton :rule) 0 [])))


(defn eval-triplet [trip pat]
  (and (= (first trip) (first pat))
       (= (second trip) (second pat))
       (= (last trip) (last pat))))


(defn eval-rule [cells patterns]
  (cond (empty? patterns) false
    :else                 (or (eval-triplet cells (first patterns))
                              (recur cells (rest patterns)))))


(defn meta-rule [cells result]
  "All rules will use the meta rule for now. In the future a rule could return another value based on input and test."
  (if result 1 0))


(defn R0 [l c r]
  (meta-rule [l c r] (eval-rule [l c r] [])))


(defn R1 [l c r]
  (meta-rule [l c r] (eval-rule [l c r] [[0 1 0], [1 1 0], [0 1 1], [1 1 1]])))


(defn R30 [l c r]
  (meta-rule [l c r] (eval-rule [l c r] [[1 0 0], [0 1 1], [0 1 0], [0 0 1]])))


(defn R90 [l c r]
  (meta-rule [l c r] (eval-rule [l c r] [[1 1 0], [1 0 0], [0 1 1], [0 0 1]])))


(defn make-rule [num]
  (cond (= num 30) R30
    (= num 90)     R90
    (= num 1)      R1
    :else          R0))
