(ns mvn-aa-plugin.aa
  (:import org.apache.commons.lang.WordUtils)
  (:use [clojure.string :only (split-lines)]))

(def error-message
  "

      _,-''`''-~`)
   (`~           \\
    |     a   a   \\
    ;        o     ; ___  _,,,,_     _.-~'.
     \\      `^`    /`_.-'~      `~-;`      \\
      \\_      _  .'                 `,     |
        |`-                           \\'__/
       /                      ,_       \\  `'-.
      /    .-''~~--.            `'-,   ;_    /
     |              \\               \\  | `''`
      \\__.--'`'-.   /_               |'
                 `'`  `~~~---..,     |
                                \\ _.-'`-.
       Sorry dude, I couldn't    \\       \\
       find the file you wanted   '.     /
                                    `'~'`
")

(def default-message
  "
  
                             \\
                              \\
                               \\\\
                                \\\\
                                 >\\/7
                             _.-(6'  \\
                            (=___._/` \\
                                 )  \\ |
                                /   / |
                               /    > /
                              j    < _\\
                          _.-' :      ``.
                          \\ r=._\\        `.
                         <`\\\\_  \\         .`-.
                          \\ r-7  `-. ._  ' .  `\\
                           \\`,      `-.`7  7)   )
                            \\/         \\|  \\'  / `-._
                                       ||    .'
                                        \\\\  (
                                         >\\  >
                                     ,.-' >.'
                                    <.'_.''
                                      <'")



(defn get-ascii-art [file]
  (if (= file :not-set)
    default-message
    (slurp file)))
      

(defn- wrap [msg]
  (WordUtils/wrap msg 18))
  
  
(defn merge-art [ascii msg]
  (let [ascii-lines (count (split-lines ascii))
        msg (wrap msg)
        msg-lines (count (split-lines msg))
        non-overlap-lines (- ascii-lines msg-lines)]
        
    (apply str
      (interpose "\n"
        (map-indexed 
          #(if (<= non-overlap-lines %1)
            (let [msg-line ((split-lines msg) (- %1 non-overlap-lines))]
              (str " " msg-line " " (.substring %2 (+ 2 (count msg-line)))))
            (str %2)) 
          (split-lines ascii))))))
