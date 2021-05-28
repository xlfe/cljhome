(ns deconz
  (:require
    [clj-http.lite.client :as http]
    [cheshire.core :refer [parse-string]]
    [gniazdo.core :as ws]))




(defn msg-received
  [msg]
  (prn (parse-string msg true)))

(defn get-config
  [hostname api-key]
  (let [{:keys [headers status body]} (http/get (str "http://" hostname "/api/" api-key "/config"))
        {:keys [websocketport]} (parse-string body true)]
    (ws/connect
      (str "ws://" hostname ":" websocketport)
      :on-receive msg-received)))


