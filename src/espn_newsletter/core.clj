(ns espn-newsletter.core
  (:require [net.cgrand.enlive-html :as html]
            [clojure.string :as str]
            [hiccup.core :as hiccup]
            [uswitch.lambada.core :refer [deflambdafn]])
  (:import (org.apache.commons.mail HtmlEmail))
  (:gen-class))

(defn fetch-url
  "Fetches a url and converts into an HTML page"
  [url]
  (html/html-resource (java.net.URL. url)))

(defn get-page-info
  "Takes a URL and returns a list of items from the page (as strings)"
  [url]
  (html/select (fetch-url url) [:item html/text-node])) ;; ESPN uses the 'item' tag for all relevant information

(defn build-newsletter-item
  "Takes in a list of lines and builds a newsletter item consisting of a header (link) and a paragraph underneath."
  [coll]
  (let [v (into [] coll)]
    (str
     (hiccup/html [:h2 [:a {:href (get v 2)} (get v 0)]]) ;; Get article headline and turn it into a link
     (hiccup/html [:p (get v 1)]))))

(defn now
  "Return the current date in 'Sun, Jan 1, 1900' format."
  []
  (.format (java.text.SimpleDateFormat. "EEE, MMM d, yyyy") (new java.util.Date)))

(defn send-newsletter
  "Takes a formatted HTML string and sends it as an email to recipients. Email parameters defined in environment variables."
  [body]
  (doto (HtmlEmail.)
    (.setHostName "smtp.gmail.com")
    (.setAuthentication (System/getenv "SMTP_EMAIL") (System/getenv "SMTP_PASSWORD"))
    (.setSmtpPort 587)
    (.setTLS true)
    (.setFrom (System/getenv "FROM_EMAIL"))
    (.addTo (System/getenv "TO_EMAIL"))
    (.setSubject (str "ESPN Newsletter - " (now)))
    (.setHtmlMsg body)
    (.send)))

(deflambdafn espn_newsletter.core.lambdaHandler
  [in out ctx]
  (let [page (get-page-info "https://www.espn.com/espn/rss/news")]
    (send-newsletter (str/join (map build-newsletter-item (partition 5 page))))))

(defn -main [& args]
  (let [page (get-page-info "https://www.espn.com/espn/rss/news")]
    (send-newsletter (str/join (map build-newsletter-item (partition 5 page))))))