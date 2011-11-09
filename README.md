# aa-maven-plugin

A maven plugin to show ascii-art, or indeed the contents of any file, with a message of your choosing

## ...aka...

playing with java interop to write a maven plugin in clojure

## show me how!

```xml
	<build>
		<plugins>
			<plugin>
				<groupId>com.github.mjg123</groupId>
				<artifactId>aa-maven-plugin</artifactId>
				<version>0.0.1-SNAPSHOT</version>
				<executions>
					<execution>
						<phase>validate</phase>
						<goals>
							<goal>beautify</goal>
						</goals>
					</execution>
				</executions>
				<configuration>

					<!-- whatever message you like, defaults to something sensible -->
					<message>this could be the start of a beautiful build</message>

					<!-- this can also be a url if you like, and is optional -->
					<show-file>plain.txt</show-file>

				</configuration>
			</plugin>
		</plugins>
	</build>
```

that ought to do it, though it's not in maven central or anything so you need this too:

<pluginRepositories>
    <pluginRepository>
        <id>mjg-repo</id>
        <url>https://raw.github.com/mjg123/mjg-repo/master/repo</url>
    </pluginRepository>
</pluginRepositories>


### Success

```
                             \
                              \
                               \\
                                \\
                                 >\/7
                             _.-(6'  \
                            (=___._/` \
                                 )  \ |
                                /   / |
                               /    > /
                              j    < _\
                          _.-' :      ``.
                          \ r=._\        `.
                         <`\\_  \         .`-.
                          \ r-7  `-. ._  ' .  `\
                           \`,      `-.`7  7)   )
                            \/         \|  \'  / `-._
                                       ||    .'
                                        \\  (
                                         >\  >
 this could be the                   ,.-' >.'
 start of a                         <.'_.''
 beautiful build                      <'
```

### Failure

```
[ERROR] 

      _,-''`''-~`)
   (`~           \
    |     a   a   \
    ;        o     ; ___  _,,,,_     _.-~'.
     \      `^`    /`_.-'~      `~-;`      \
      \_      _  .'                 `,     |
        |`-                           \'__/
       /                      ,_       \  `'-.
      /    .-''~~--.            `'-,   ;_    /
     |              \               \  | `''`
      \__.--'`'-.   /_               |'
                 `'`  `~~~---..,     |
                                \ _.-'`-.
       Sorry dude, I couldn't    \       \
       find the file you wanted   '.     /
                                    `'~'`

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```
