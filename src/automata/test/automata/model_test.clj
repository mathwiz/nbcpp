(ns automata.model-test
  (:require [clojure.test :refer :all]
            [automata.model :refer :all]))


(deftest recycle-count-test
         (testing "check that recycle creates the right length."
                  (let [elems (recycle-list 8 () '(1 0 1))]
                                            (is ( = 8(count elems) )))))


(deftest recycle-test
  (testing "check that recycle creates the right list."
           (let [elems (recycle-list 8 () '(1 0 1))]
             (is ( = '(1 0 1 1 0 1 1 0) elems)))))
