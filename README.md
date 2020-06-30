# はじめに:
&nbsp;このプログラムの使用は自己責任でお願いします。
   
&nbsp;Github上のソースにはファイル上限があるため、BGMがありません。  
&nbsp;なのでsrcから自分でビルドしたい場合はReleaseからBGMをダウンロードしてください  
  
&nbsp;このプログラムを起動するにはJavaが必要です。  
  
&nbsp;Javaの導入方法はGoogle等で検索して調べてください。  
  
# 準備:
&nbsp;access.txtファイルに入力する内容は  
&nbsp;1行目: oauthパス(oauth:から始まるパスワード)  
&nbsp;&nbsp;&nbsp;***(Botにしたいアカウントのoauthパスワード***  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;***配信のアカウントを乗っ取られないために別アカウントを用意することをおすすめします)***  
&nbsp;2行目: twitch名(Shinjiさんだったらshinjifromjapanxd(小文字))  
&nbsp;&nbsp;&nbsp;(oauthのアカウントとは別  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;shinjifromjapanxdの配信でクイズを行いたい場合はshinjifromjapanxd)  
   
&nbsp;例:  
&nbsp;&nbsp;oauth:abcdefg123456789  
&nbsp;&nbsp;shinjifromjapanxd  
   
&nbsp;access.txtが存在しなかったり正しく入力されてなかった場合は  
&nbsp;Oekaki Quizが起動しません(パスワードが違ったりすると強制的にOekaki Quizが落とされます)  
  
&nbsp;odaiフォルダのテキストはクイズに出されるお題を作れます  
&nbsp;最初の文字がtrueだった場合、  
&nbsp;ひらがな:表示名  
&nbsp;例:  
&nbsp;&nbsp;true  
&nbsp;&nbsp;りんご:リンゴ  
&nbsp;&nbsp;おれんじ:オレンジ  
  
&nbsp;最初の文字がfalseだった場合、  
&nbsp;ひらがな  
&nbsp;例:  
&nbsp;&nbsp;false  
&nbsp;&nbsp;りんご  
&nbsp;&nbsp;おれんじ  
  
 timer.txtは1ゲームの制限時間を設定できます  
  300 → 300秒  
  
# 注意(重要):
&nbsp;コード内でoauthのパスワードのログを非表示にしていますが  
&nbsp;念のため、cmdは別モニターに移動しておいてください  
&nbsp;oauthのパスワードは人に見せてはいけません  

&nbsp;また、access.txtにoauthのパスワードがあるので配信中に開かないでください  
&nbsp;&nbsp;&nbsp;(配信でoauthのコードが映ってしまうと悪用されてしまう可能性があります！)  
 
&nbsp;もし、万が一配信で映ってしまった場合はhttps://www.twitch.tv/settings/connections から  
&nbsp;Twitch Chat OAuth Token Generatorのリンクを解除してください  
&nbsp;&nbsp;&nbsp;(Twitchにはあまり詳しくないのでこの対処法で合っているかはわかりません)  

&nbsp;***※これが、守れない場合は使用しないことをおすすめします***  

# 使い方:
1. run.batというバッチファイルから起動してください  
&nbsp;&nbsp;&nbsp;(UTF-8で開いています(直接jarから起動すると文字化けしてしまいます))  
  
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
 - beco (騒音のない世界)  
  
# 開発者:
 - Win10さん  
