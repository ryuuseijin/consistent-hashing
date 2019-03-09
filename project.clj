(defproject simple-consistent-hashing "0.1.0-SNAPSHOT"
  :description "A simple consistent hashing implementation."
  :url "https://github.com/ryuuseijin/simple-consistent-hashing"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [digest "1.4.8"]]
  :repl-options {:init-ns simple-consistent-hashing.core})
