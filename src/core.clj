(ns core
  (:require
    [clojure.tools.cli :refer [parse-opts]]
    [deconz]
    [clojure.string]
    [jmdns])

  (:gen-class))

(def cli-options
  [["-j" "--jmdns DOMAIN" "Domain to discover"]
   ["-d" "--deconz DECONZ_HOST:PASS" "Host and pass for Deconz server"]])

(defn -main [& args]
  (let [opts (parse-opts args cli-options)
        jmdns-domain (get-in opts [:options :jmdns])
        dc (get-in opts [:options :deconz])]
    (println "Running...")
    (when jmdns-domain
      (println "== Starting jmdns: " jmdns-domain)
      (prn (jmdns/discover jmdns-domain)))
    (when dc
      (let [[d-host d-pass] (clojure.string/split dc #":")]
        (println "== Starting deconz: " d-host)
        (deconz/get-config d-host d-pass)))))
  
