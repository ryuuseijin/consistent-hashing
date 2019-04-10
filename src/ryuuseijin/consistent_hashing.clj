(ns ryuuseijin.consistent-hashing
  "A consistent hashing implementation using sorted-map as the core datastructure for logarithmic time lookups."
  (:require [digest]))

(def default-hash-fn digest/sha-256)

(defn ring
  "Creates a consistent hashing ring

  replication-factor - specifies the number of times a node will be
  repeated in the ring.

  A larger number will ensure that inserting or deleting a node will
  rebalance load evenly across all other nodes in the cluster. If the
  number is too large `insert`/`delete` are slower (but not `lookup`).

  A good rule of thumb is to choose a multiple of the maximum number of
  nodes in the cluster (e.g. max-cluster-size * 4).

  `hash-fn` - a function to hash string keys (defaults to `digest/sha-256`)"
  ([replication-factor] (ring replication-factor default-hash-fn))
  ([replication-factor hash-fn]
   (assert (<= 1 replication-factor))
   (-> (sorted-map)
       (vary-meta assoc
                  ::hash-fn hash-fn
                  ::replication-factor replication-factor))))

(defn replica-hashes [ring key]
  (let [{::keys [hash-fn replication-factor]} (meta ring)]
    (->> (range replication-factor)
         (map #(hash-fn (str % \- key))))))

(defn insert
  "Inserts `replication-factor` number of nodes into the ring.
  `key` - a string key to identify this node
  `node` - a value representing a node in the cluster"
  [ring key node]
  (->> (map vector (replica-hashes ring key) (repeat node))
       (into ring)))

(defn delete
  "Deletes all nodes inserted by `insert` from the ring.
  `key` - a string key to identify the node to delete"
  [ring key]
  (apply dissoc ring (replica-hashes ring key)))

(defn lookup
  "Chooses a node in the cluster to be responsible for the given key.

  This function runs in logarithmic time as it is using `subseq` on
  a `sorted-map`.

  `key` - an arbitrary string key"
  [ring key]
  (or (first (subseq ring >= ((-> ring meta ::hash-fn) key)))
      (first ring)))
