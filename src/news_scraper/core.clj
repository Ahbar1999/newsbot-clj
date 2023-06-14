(ns news-scraper.core
  (:gen-class)
  (:require [net.cgrand.enlive-html :as html])
  ; no need to import whole (:require [clojure.pprint])), we just need pprint 
  (:require [clojure.pprint :refer [pprint]]))

(defn -main [& args]
  (println (str "Hello World\n"))
  ; we are scraping news headlines from 'scroll' news website 
  (def news-page-url "https://scroll.in/latest/")
  ; scrap the web and bind it to page
  (defn fetch-url [url]
    (html/html-resource (java.net.URL. url)))
  ; print the data structure containing scraped html 
  ; (clojure.pprint/pprint page)  
  
  ; get news items in nested hash map/set datastructure
  (defn get-news-items [] 
    (html/select-nodes* (fetch-url news-page-url)  [[:div #{:.row-story-meta}]]))
  
  ; EXTRACT headlines and descrioptions 
  ; loop over this and retrieve pairs of h1 and(if exists) h2
  ; m: map, l: list, v: vector  
  (def data (map (fn [v] [(first ((get v 0) :content)) (if (= ((get v 1) :tag) :h2) (first ((get v 1) :content)) "")]) 
    (map (fn [l] [(nth l 1) (nth l 3)]) 
               (map (fn [m] (m :content)) (get-news-items)))))
  
  (pprint data)
  ; (println (first data))
  (println "\n"))

