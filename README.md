# consistent-hashing

A consistent hashing implementation using sorted-map as the core datastructure for logarithmic time lookups.

    [ryuuseijin/consistent-hashing "1.0.0"]

## Usage

    user> (require '[ryuuseijin.consistent-hashing :as ch])
    nil
    user> (def r (-> (ch/ring 1)
                     (ch/insert "a" :a)
                     (ch/insert "b" :b)
                     (ch/insert "c" :c)))
    #'user/r
    user> r
    {"1bb556efd38c5a70c450d4afeaa5d4f528d095d85723f1037dddc1a5e59c6476" :b,
     "7c7fa2ffc73ed8a92f69f9fd7e4363b970d57b08c1e811a0868e7332510764ff" :c,
     "d00146933186bebfd751d39228f587dee72346fb910e0ff2bfe66d3ce4211086" :a}
    user> (ch/lookup r "key1")
    ["d00146933186bebfd751d39228f587dee72346fb910e0ff2bfe66d3ce4211086" :a]
    user> (ch/lookup r "key3")
    ["1bb556efd38c5a70c450d4afeaa5d4f528d095d85723f1037dddc1a5e59c6476" :b]

## License

Copyright © 2019

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
