# はじめに:
 このプログラムの使用は自己責任でお願いします。  
 Github上のソースにはファイル上限のため、BGMがありません。  
 なのでsrcから自分でビルドしたい場合はReleaseからBGMをダウンロードしてください  

 このプログラムを起動するにはJavaが必要です。  

 Javaの導入方法はGoogle等で検索して調べてください。  

# 準備:
 access.txtファイルに入力する内容は  
 1行目: oauthパス(oauth:から始まるパスワード)  
         (Botにしたいアカウントのoauthパスワード  
          配信のアカウントを乗っ取られないために別アカウントを用意することをおすすめします)  
 2行目: twitch名(Shinjiさんだったらshinjifromjapanxd(小文字))  
         (oauthのアカウントとは別  
          shinjifromjapanxdの配信でクイズを行いたい場合はshinjifromjapanxd)  
 
 例:  
  oauth:abcdefg123456789  
  shinjifromjapanxd  
 
 access.txtが存在しなかったり正しく入力されてなかった場合は  
 Oekaki Quizが起動しません(パスワードが違ったりすると強制的にOekaki Quizが落とされます)  

 odaiフォルダのテキストはクイズに出されるお題を作れます  
 最初の文字がtrueだった場合、  
  ひらがな:表示名  
  例:  
   true  
   りんご:リンゴ  
   おれんじ:オレンジ  
 最初の文字がfalseだった場合、  
  ひらがな  
  例:  
   false  
   りんご  
   おれんじ  

 timer.txtは1ゲームの制限時間を設定できます  
  300 → 300秒  

# 注意(重要):
 コード内でoauthのパスワードのログを非表示にしていますが
 念のため、cmdは別モニターに移動しておいてください
 oauthのパスワードは人に見せてはいけません

 また、access.txtにoauthのパスワードがあるので配信中に開かないでください
  (配信でoauthのコードが映ってしまうと悪用されてしまう可能性があります！)
 
 もし、万が一配信で映ってしまった場合はhttps://www.twitch.tv/settings/connections から
 Twitch Chat OAuth Token Generatorのリンクを解除してください
  (Twitchにはあまり詳しくないのでこの対処法で合っているかはわかりません)

 ※これが、守れない場合は使用しないことをおすすめします

# 使い方:
 1. run.batというバッチファイルから起動してください
     (UTF-8で開いています(直接jarから起動すると文字化けしてしまいます))

 2. [クリア]ボタンはキャンバスを真っ白にしてくれる機能です

 3. 左の[パレット]から色を選択できます。

 4. 右の[サイズ]からペンの太さを設定できます

 5. 右の[ゲーム数]からプレイするゲーム回数を選択できます(最大100)

 6. [開始]ボタンを押すとゲームを開始できます
    その後、プレイヤーに!drawと打ってもらうと参加できます

 7. [締め切る]ボタンを押すとプレイヤーの参加を締め切ることができます
    ここで!drawと打っていないプレイヤーは参加できてないことになります。

 8. 右のフィールドにお題が表示されるのでそのイラストを
　　キャンバスに描いてください。

 9. ここでプレイヤーはひらがなでお題の名前をチャット欄に書いてください

 10. [強制終了]ボタンで終了できますがタイムが切れたり、
　　 参加者が答えてくれると次のゲームに進みます。

 11. 左側のウィンドウは答えたプレイヤーのランキングが表示されます。

 12. [ゲーム数]で指定した回数をプレイすると自動でゲームが終わります。

# 使用ライブラリ:
 - Pircbot 1.5.0

# BGM作成者:
 beco (騒音のない世界)

# 開発者:
 Win10さん
