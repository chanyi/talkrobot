<html lang="zh">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>小犀牛人</title>
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
    <label>妙语连珠--小犀牛</label>
    <hr style="height:3px;border:none;border-top:3px double grey ;width:60%;" />
    <br>
  </div>
  <div class="histroy">
    <#list list as history>
      <br>
      <label>${history}</label>
      <br>
    </#list>
  </div>
  <div>
    <br>
    <input type="text" id = "sendMessage" name="sendMessage" style="text-align: center">
  </div>
  <div>
    <br>
    <input type="button" name="sendButton" value="发送" onclick="send()"  style="text-align: center">
  </div>
</div>

<script type="text/javascript">
  function send() {
    $.ajax({
      url : "/talkrobot/sendMessage?text="+document.getElementById("sendMessage").value,
      type : 'GET',
      success : function(data) {
        window.self.location.href = "/talkrobot/start"
      },
      error : function(data) {
        alert("请求失败");
      }
    })
  }
  $(document).ready(function() {
    setTimeout(function () {
      var onFocus = document.querySelector('#sendMessage');
      onFocus.focus();
    },500)

    $("#sendMessage").keydown(function(event) {
      if (event.keyCode == 13) {
        send()
      }
    })
  });

</script>
</body>
</html>