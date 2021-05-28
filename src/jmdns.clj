(ns jmdns


  (:import
    [java.io IOException];
    [java.net InetAddress UnknownHostException];
    [javax.jmdns JmDNS ServiceEvent ServiceListener]));



;https://www.javadoc.io/doc/org.jmdns/jmdns/latest/index.html
(defn to-map
  [info]
  (let [pn (map str (enumeration-seq (.getPropertyNames info)))
        props (zipmap (map keyword pn)
                      (map #(.getPropertyString info %) pn))]
    {:name (.getName info)
     :application (.getApplication info)
     :domain (.getDomain info)
     :HostAdresses (map str (.getHostAddresses info))
     :Port (.getPort info)
     :Priority (.getPriority info)
     :Properties props 
     :Protocol (.getProtocol info)
     :Server (.getServer info)
     :Subtype (.getSubtype info)
     :Type (.getType info)
     :Urls (map str (.getURLs info))}))

   ; :Inet4Addresses (map str (.getInet4Addresses info))
   ; :Inet6Addresses (map str (.getInet6Addresses info))})
   ; :NiceTextString (.getNiceTextString info)})


(def p (proxy [ServiceListener] []
          (serviceAdded [event])
            ; (prn "service added" (.getInfo event)))
          (serviceRemoved [event])
            ; (prn "service removed" (.getInfo event)))
          (serviceResolved [event]
            (prn "service resolved" (to-map (.getInfo event))))))

(defn discover
  [domain]
  (let [
        jmdns (JmDNS/create (InetAddress/getLocalHost))]

    (.addServiceListener jmdns domain p);
    jmdns))
