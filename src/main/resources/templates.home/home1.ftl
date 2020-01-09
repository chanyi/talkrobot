<html lang="zh">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>lilei</title>
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<style>
  .main {
    text-align: center;
    margin:0 auto
  }
</style>
<body lang="zh">
<div class="main">
  <div class="label">
    <br>
    <hr style="height:3px;border:none;border-top:3px double grey ;width:60%;" />
    <label>欧呦~~你来了啊！！来就来呗~~带什么礼物啊—*_*—</label>
    <hr style="height:3px;border:none;border-top:3px double grey ;width:60%;" />
    <br>
  </div>
  <div>
    <br>
    <br>
    <br>
    <input type="button" name="sendButton" value="这里啥也没有" onclick="nothing();"  style="text-align: center">
  </div>
  <div>
    <br>
    <input type="button" name="sendButton" value="去找小犀牛玩" onclick="send();"  style="text-align: center">
  </div>
</div>

<script type="text/javascript">
  function send() {
    window.self.location.href = "/talkrobot/start"
  }
  function nothing() {
    alert("都说了啥都没有，还点—_—...")
  }

</script>
</body>
</html>