(ns ryuuseijin.consistent-hashing-test
  (:require [clojure.test :refer :all]
            [ryuuseijin.consistent-hashing :refer :all]))

(deftest test-ring
  (testing "creating an empty ring"
    (is (= (ring 1) {}))))

(deftest test-insert
  (testing "inserting into a ring"
    (is (= (-> (ring 4)
               (insert "node-a" :a)
               (insert "node-b" :b)
               count)
           8))))

(deftest test-delete
  (testing "deleting from a ring deletes all replicated entries"
    (is (= (-> (ring 4)
               (insert "node-a" :a)
               (insert "node-b" :b)
               (delete "node-a")
               count)
           4))))

(deftest test-lookup
  (testing "looking up a key with the default-hash-fn returns a node"
    (is (= (-> (ring 1)
               (insert "node-a" :a)
               (insert "node-b" :b)
               (lookup "some-key"))
           [(default-hash-fn "0-node-a") :a])))

  (testing "looking up a key returns the next node"
    (is (= (-> (ring 1 identity)
               (insert "b" :b)
               (insert "d" :d)
               (lookup "0-a"))
           ["0-b" :b]))
    (is (= (-> (ring 1 identity)
               (insert "b" :b)
               (insert "d" :d)
               (lookup "0-c"))
           ["0-d" :d]))
    (testing "or the first node if there is no next node"
      (is (= (-> (ring 1 identity)
                 (insert "b" :b)
                 (insert "d" :d)
                 (lookup "0-e"))
             ["0-b" :b])))))

