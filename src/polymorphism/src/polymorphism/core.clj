(ns polymorphism.core
    (:gen-class))

(use 'polymorphism.controller)

(defn -main
  "start the app here"
  [& args]
  (println
   (let [command (first args) finished (str " * " command " finished")]
     (cond (= command "hello") "Hello, World!"
           (= command "composite") (do (composite-example1 (rest args)) finished)
           :else                    (str command " not found")))))
