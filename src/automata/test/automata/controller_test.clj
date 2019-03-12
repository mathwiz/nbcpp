(ns automata.controller-test
  (:require [clojure.test :refer :all]
            [automata.controller :refer :all]))


(deftest arg-test
  (testing "check that args for init are parsed correctly"
    (is (do-random? "r"))
    (is (do-single? "s"))
    (is (not (do-single? "1")))))


(deftest init-test
  (testing "check that a list of numbers is created."
           (let [elems (parse-init "10")]
             (is (= '(1 0) elems)))))


(deftest init-blank-test
  (testing "check that an empty list is created."
           (let [elems (parse-init "")]
             (is (= () elems)))))


(deftest explode-string-test
  (testing "turn a string into individual character strings."
           (let [elems (explode-string "101")]
             (is (= '("1" "0" "1") elems)))))


(deftest convert-long-test
  (testing "turn strings into numbers."
           (let [elems (convert-to-long '("1" "0" "1"))]
             (is (= '(1 0 1) elems)))))


(deftest single-init-test
  (testing "turn into init string for a single on cell in the middle of the field."
    (is (= "0000000000001" (single-init 25)))
    (is (= "0001" (single-init 7)))
    (is (= "001" (single-init 5)))))
