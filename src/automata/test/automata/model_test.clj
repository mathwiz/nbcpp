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


(deftest evolve-cell-test
  (testing "the next step of an individual cell."
           (let [cells '(0 0 0 0 1 0 0 1)
                 rule  R30]
             (is (= 1 (evolve-cell cells 0 rule)))
             (is (= 0 (evolve-cell cells 1 rule)))
             (is (= 0 (evolve-cell cells 2 rule)))
             (is (= 1 (evolve-cell cells 3 rule)))
             (is (= 1 (evolve-cell cells 4 rule)))
             (is (= 1 (evolve-cell cells 5 rule)))
             (is (= 1 (evolve-cell cells 6 rule)))
             (is (= 1 (evolve-cell cells 7 rule))))))


(deftest evolve-cell-test2
  (testing "the next step of an individual cell."
           (let [cells '(1 0 0 1 1 0 0 0)
                 rule  R30]
             (is (= 1 (evolve-cell cells 0 rule)))
             (is (= 1 (evolve-cell cells 1 rule)))
             (is (= 1 (evolve-cell cells 2 rule)))
             (is (= 1 (evolve-cell cells 3 rule)))
             (is (= 0 (evolve-cell cells 4 rule)))
             (is (= 1 (evolve-cell cells 5 rule)))
             (is (= 0 (evolve-cell cells 6 rule)))
             (is (= 1 (evolve-cell cells 7 rule))))))


(deftest evolve-cells-test
  (testing "the resulting list from evolving all cells."
           (let [rule  R30]
             (is (= '(1 0 0 1 1 1 1 1) (evolve-cells '(0 0 0 0 1 0 0 1) rule 0 [])))
             (is (= '(0 0 0 1 1 1 0 0) (evolve-cells '(0 0 0 0 1 0 0 0) rule 0 [])))
             (is (= '(1 1 1 1 0 1 0 1) (evolve-cells '(1 0 0 1 1 0 0 0) rule 0 [])))
             (is (= '(0 1 0 1 0 1 0 0) (evolve-cells '(0 1 0 1 0 1 1 1) rule 0 []))))))


(deftest evolve-test
  (testing "the next step of an automaton."
           (let [auto (make-automaton 8 R30 '(1 0 0 1 1 0 0 0))]
             (is (= '(1 0 0 1 1 0 0 0) (get auto :cells)))
             (is (= '(1 1 1 1 0 1 0 1) (get (evolve auto) :cells))))))


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
