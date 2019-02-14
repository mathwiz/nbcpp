(ns automata.controller-test
  (:require [clojure.test :refer :all]
            [automata.controller :refer :all]))


(deftest init-test
         (testing "check that a list of numbers is created."
                  (let [elems (parse-init "10")]
                                            (is ( = '(1 0) elems)))))


