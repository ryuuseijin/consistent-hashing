(defproject ryuuseijin/consistent-hashing "1.0.1-SNAPSHOT"
  :description "A consistent hashing implementation."
  :url "https://github.com/ryuuseijin/consistent-hashing"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :repositories [["releases" {:url "https://repo.clojars.org"}]]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [digest "1.4.8"]]
  :repl-options {:init-ns ryuuseijin.consistent-hashing})
