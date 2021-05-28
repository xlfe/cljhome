(ns core
  (:require
    [clojure.tools.cli :refer [parse-opts]]
    [deconz]
    [jmdns])

  (:gen-class))


(def cli-options
  ;; An option with a required argument
  [["-j" "--jmdns DOMAIN" "Domain to discover"
    :default "_googlecast._tcp.local."] 

   ["-d" "--deconz DECONZ_HOST:PASS" "Host and pass for Deconz server"]
    
   ["-h" "--help"]])




(defn -main [& args]
  (let [opts (parse-opts args cli-options)
        jmdns-domain (get-in opts [:options :jmdns])
        dc (get-in opts [:options :deconz])]
    (println opts)
    (println "Running...")
    (when jmdns-domain
      (println "jmdns")
      (prn (jmdns/discover jmdns-domain)))
    (when dc
      (println "deconz")
      (let [[d-host d-pass] (clojure.string/split dc #":")]
        (prn (deconz/get-config d-host d-pass))))))
  
