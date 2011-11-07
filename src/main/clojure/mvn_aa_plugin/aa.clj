(ns mvn-aa-plugin.aa)

(def error-message
  (str "

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
"))

(def default-message
  (str "
  
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
                                      <'  
  
"))



(defn get-ascii-art [this file]
 
  (try
  	(if (= file :not-set)
      default-message
      (slurp file))
      
    (catch java.io.FileNotFoundException e
      (.error (.getLog this) error-message) 
      (throw (org.apache.maven.plugin.MojoExecutionException. "arrrgh" e)))))

