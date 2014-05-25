# Java UML To Template

Javaのインターフェースを元にテンプレートを生成します。

※ドラフト段階です。まだ使える状態ではありません。

## USAGE

1. [Javaファイルを用意します](src/main/java)
2. [Javaファイルのインターフェースを変換するための定義を用意します](src/main/groovy/net/ichigotake/java/javaumltotemplate/sandbox)
3. Gradleのタスクを実行します `./gradlew run`
4. [定義したテンプレートを元にファイルが生成されます](output)

## TODO

- Gradleのタスクにする
- いい感じの命名
- 名前空間の整理
- 詳解ドキュメント

## LICENSE

[Apache License v2](LICENSE)
