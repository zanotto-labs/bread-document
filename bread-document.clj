#!/usr/bin/env bb


(ns bin.bread-document
  (:require [clojure.java.io :as io]))


(defn add-crumb [message]
  (println "crumb added")
  (try
    (with-open [file (io/writer "document.md" :append true)]
      (.write file (str "\n- " message)))
    (println "Crumb added successfully!")
    (catch Exception e
      (println "Error adding crumb:" (.getMessage e))))
  )

(defn -main [& args]
  (cond
    (= (first args) "new")
    (cond
      (= (second args) "crumb")
      (apply add-crumb (rest (rest args)))
      :else
      (println "Unknown sub-command.")))
  )

(apply -main *command-line-args*)
