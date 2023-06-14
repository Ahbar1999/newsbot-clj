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
  ; (clojure.pprint/pprint (map html/text (html/select (fetch-url news-page-url)  [:div.row-story-meta]))) 
  
  ; TRY TO MAKE PAIRS FOR HEADLINE AND DESCRIPTION 
  ; WE NEED A WAY TO GROUP TOGETHER SELECTED ELEMENTS THAT COME UNDER SAME div 
  ; (pprint (map html/text (html/select (fetch-url news-page-url)  [(html/attr= :itemprop "headline about")])))
  
  (defn get-news-items []
    ; (map html/text (html/select (fetch-url news-page-url)  [:div.row-story-meta (html/attr= :itemprop "headline about") (html/attr= :itemprop "description")])))
    (map html/text (html/select (fetch-url news-page-url)  #{[:div.row-story-meta :> :h1] [:div.row-story-meta :> :h2]})))
 
  (pprint (get-news-items))
  " 
  (doseq [news-item (get-news-items)]
    (println news-item "\n")) 
  " 
  (println "\n"))

