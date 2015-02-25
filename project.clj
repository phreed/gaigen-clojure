(defproject geometric-algebra "0.1.0-SNAPSHOT"
  :description "Based on the book 'Geometric Algebra for Computer Science"
  :url "http://www"
  :license {:name "Eclipse Public License"
            :url "http://www"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/math.combinatorics "0.0.8"]]
  :main geometric-algebra.core
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java/"]
  :resource-paths ["resource"]
  :target-path "target/%s"
  :profiles
  { :dev
    {:aot :all
     :test-paths ["test"]
     :dependencies [[midje "1.6.3"]]
     :plugins [[lein-autoexpect "1.2.2"]
               [lein-ancient "0.5.5"]
               [lein-midje "3.1.1"]
               [lein-gorilla "0.3.4"] ] }

    :uberjar
    {:aot :all} })
