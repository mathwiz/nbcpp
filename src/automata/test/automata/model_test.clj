(ns automata.model-test
  (:require [clojure.test :refer :all]
            [automata.model :refer :all]))


(deftest random-count-test
  (testing "check that recycle creates the right length."
           (let [elems (random-list 8 ())]
             (is (= 8 (count elems))))))


(deftest recycle-test
  (testing "check that recycle creates the right list."
           (let [elems (recycle-list 8 () '(1 0 1))]
             (is (= '(1 0 1 1 0 1 1 0) elems))
             (is (= 8 (count elems))))))


(deftest make-test
  (testing "create an automaton."
           (let [auto (make-automaton 8 R0 '(1 0 1))]
             (is (= '(1 0 1 1 0 1 1 0) (get auto :cells))))))


(deftest rule-eval-test
  (testing "rule matching."
           (let [rule R30]
             (is (= 1 (rule 1 0 0)))
             (is (= 1 (rule 0 1 1)))
             (is (= 1 (rule 0 1 0)))
             (is (= 1 (rule 0 0 1)))
             (is (= 0 (rule 0 0 0)))
             (is (= 0 (rule 1 1 1)))
             (is (= 0 (rule 1 0 1)))
             (is (= 0 (rule 1 1 0))))))
