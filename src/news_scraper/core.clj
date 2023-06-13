(ns news-scraper.core
  (:gen-class)
  (:require [net.cgrand.enlive-html :as html])
  (:require [clojure.pprint]))

(defn -main [& args]
  (println (str "Hello World\n"))
  ; we are scraping news headlines from 'scroll' news website 
  (def news-page-url "https://scroll.in/latest/")
  ; scrap the web and bind it to page
  (defn fetch-url [url]
    (html/html-resource (java.net.URL. news-page-url)))
  ; print the data structure containing scraped html 
  ; (clojure.pprint/pprint page)  
  ; (clojure.pprint/pprint (map html/text (html/select (fetch-url news-page-url)  [:div.row-story-meta]))) 
  ; TRY TO MAKE PAIRS FOR HEADLINE AND DESCRIPTION 
  ; (clojure.pprint/pprint (map html/text (html/select (fetch-url news-page-url)  [(html/attr= :itemprop "description")]))) 
  ; (clojure.pprint/pprint (map html/text (html/select (fetch-url news-page-url)  [(html/attr= :itemprop "headline about")])))

  (println "\n"))

