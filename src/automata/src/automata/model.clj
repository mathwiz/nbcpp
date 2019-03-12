(ns automata.model)


(defn random-list [len acc]
  (cond (= len 0) acc
        :else         (recur (dec len)
                             (cons (if (< (rand) 0.5) 0 1) acc))))


(defn recycle-list [len acc seed]
  (cond (<= len 0)             acc (< (- len (count seed)) 0)
        (concat acc (take len seed))
        :else                      (recur (- len (count seed))
                                          (concat acc seed) seed)))


(defn automaton-new [rule cells]
  (hash-map :rule rule
            :cells cells))


(defn make-automaton ([len rule]
                      (automaton-new rule (random-list len ())))
  ([len rule init]
   (automaton-new rule (recycle-list len () init))))


(defn evolve-cell [cells index rule]
  (let [left   (if (= index 0)
                 (nth cells (dec (count cells)))
                 (nth cells (dec index)))
        center (nth cells index)
        right  (if (= index (dec (count cells)))
                 (nth cells 0)
                 (nth cells (inc index)))]
    (rule left center right)))


(defn evolve-cells [cells rule index acc]
  (cond (= index (count cells)) acc
        :else                       (recur cells rule (inc index)
                                           (conj acc (evolve-cell cells index rule)))))


(defn evolve [automaton]
  "Computes the next iteration of automaton"
  (automaton-new (get automaton 
                      :rule) 
                 (evolve-cells (get automaton 
                                    :cells) 
                               (get automaton 
                                    :rule) 0 [])))


(defn eval-triplet [trip pat]
  (and (= (first trip)
          (first pat))
       (= (second trip)
          (second pat))
       (= (last trip)
          (last pat))))


(defn eval-rule [cells patterns]
  (cond (empty? patterns) false
        :else                 (or (eval-triplet cells (first patterns))
                                  (recur cells (rest patterns)))))


(defn meta-rule [cells result]
  "All rules will use the meta rule for now. In the future a rule could return another value based on input and test."
  (if result 1 0))


(defn R0 [l c r] 0)

(defn R255 [l c r] 1)


(defn rule-patterns [n]
  (letfn [(iter [num acc]
            (cond (>= num 128)
                  (iter (- num 128)
                        (cons [1 1 1] acc))
                  (>= num 64)
                  (iter (- num 64)
                        (cons [1 1 0] acc))
                  (>= num 32)
                  (iter (- num 32)
                        (cons [1 0 1] acc))
                  (>= num 16)
                  (iter (- num 16)
                        (cons [1 0 0] acc))
                  (>= num 8)
                  (iter (- num 8)
                        (cons [0 1 1] acc))
                  (>= num 4)
                  (iter (- num 4)
                        (cons [0 1 0] acc))
                  (>= num 2)
                  (iter (- num 2)
                        (cons [0 0 1] acc))
                  (>= num 1)
                  (iter (- num 1)
                        (cons [0 0 0] acc))
                  :else acc))]
    (reverse (iter n []))))


(defn rule-from-num [n]
  (fn [l c r] (meta-rule [l c r] (eval-rule [l c r] (rule-patterns n)))))


(def R30
  (rule-from-num 30))


(defn make-rule [num]
  (cond (= num 0) R0 (= num 255)     R255
        :else          (rule-from-num num)))
